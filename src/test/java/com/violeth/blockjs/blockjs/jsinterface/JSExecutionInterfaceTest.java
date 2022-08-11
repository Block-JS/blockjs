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

    @Test
    @DisplayName("Can run JS")
    void runJS() {
        runner = new JSExecutionInterface(getResourceFile("jsTests/basicTest.js"));
        runner.registerAndRun();
    }

    @Test
    @DisplayName("Can run JS with callbacks")
    void runJSWithCallbacks() {
        runner = new JSExecutionInterface(getResourceFile("jsTests/callbackTest.js"));
        assertDoesNotThrow(() -> runner.registerAndRun());
    }

    @Test
    @DisplayName("Can run JS with imports")
    void runJSWithImports() {
        runner = new JSExecutionInterface(getResourceFile("jsTests/importTest.js"));
        assertDoesNotThrow(() -> runner.registerAndRun());
    }

    @Test
    @DisplayName("Can run JS with node modules")
    void runJSWithNodeModules() {
        runner = new JSExecutionInterface(getResourceFile("jsTests/nodeModuleTest.js"));
        assertDoesNotThrow(() -> runner.registerAndRun());
    }
}