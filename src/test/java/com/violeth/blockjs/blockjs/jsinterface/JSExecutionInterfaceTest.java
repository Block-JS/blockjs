package com.violeth.blockjs.blockjs.jsinterface;

import be.seeseemelk.mockbukkit.MockBukkit;
import com.caoccao.javet.enums.JSRuntimeType;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.interop.engine.JavetEngineConfig;
import com.caoccao.javet.interop.engine.JavetEnginePool;
import com.caoccao.javet.node.modules.NodeModuleModule;
import com.violeth.blockjs.blockjs.BlockJS;
import org.bukkit.Server;
import org.junit.jupiter.api.*;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JSExecutionInterfaceTest {
    private JSExecutionInterface runner;

    private static Server server;
    private static BlockJS blockJS;

    @BeforeAll
    static void setup() {
        server = MockBukkit.mock();
        blockJS = MockBukkit.load(BlockJS.class);
    }

    @BeforeEach
    void setupEach() throws JavetException {
        setupRuntime();
    }

    @AfterAll
    static void teardown() {
        MockBukkit.unmock();
    }

    static File getResourceFile(String path) {
        if (path == null) {
            URL url = Thread.currentThread().getContextClassLoader().getResource(".");
            return new File(Objects.requireNonNull(url).getFile());
        }

        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        return new File(Objects.requireNonNull(url).getFile());
    }

    NodeRuntime setupRuntime() throws JavetException {
        JavetEngineConfig config = new JavetEngineConfig();

        config.setJSRuntimeType(JSRuntimeType.Node);

        JavetEnginePool<NodeRuntime> pool = new JavetEnginePool<NodeRuntime>(config);

        pool.getConfig().setJSRuntimeType(JSRuntimeType.Node);

        NodeRuntime runtime = pool.getEngine().getV8Runtime();

        runtime.getNodeModule(NodeModuleModule.class).setRequireRootDirectory(getResourceFile("jsTests").getAbsolutePath());

        return runtime;
    }

    @Test
    @DisplayName("Can run JS")
    void runJS() {
        try {
            runner = new JSExecutionInterface(getResourceFile("jsTests/basicTest.js"), setupRuntime());
            runner.runtime.await();
            runner.registerAndRun();
        } catch (JavetException e) {
            BlockJS.instance.getLogger().log(Level.SEVERE,
                    "There was an error creating the Javet runtime.");
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Can run JS with callbacks")
    void runJSWithCallbacks() {
        try (NodeRuntime runtime = setupRuntime()) {
            runner = new JSExecutionInterface(getResourceFile("jsTests/callbackTest.js"), runtime);
            runner.runtime.await();
            assertDoesNotThrow(() -> runner.registerAndRun());
        } catch (JavetException e) {
            BlockJS.instance.getLogger().log(Level.SEVERE,
                    "There was an error creating the Javet runtime.");
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Can run JS with imports")
    void runJSWithImports() {
        try (NodeRuntime runtime = setupRuntime()) {
            runtime.setV8ModuleResolver(((v8Runtime, resourceName, iv8Module) -> {
                if (resourceName.equals("./importTestModule.js")) {
                    return runtime.getExecutor(getResourceFile("jsTests/importTestModule.js"))
                            .setResourceName(resourceName)
                            .compileV8Module();
                } else {
                    return null;
                }
            }));

            runner = new JSExecutionInterface(getResourceFile("jsTests/importTest.js"), runtime);
            runner.runtime.await();
            assertDoesNotThrow(() -> runner.registerAndRun());
        } catch (JavetException e) {
            BlockJS.instance.getLogger().log(Level.SEVERE,
                    "There was an error creating the Javet runtime.");
            throw new RuntimeException(e);
        }
    }
}