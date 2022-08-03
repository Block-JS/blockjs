package com.violeth.blockjs.blockjs.jsinterface;

import com.caoccao.javet.enums.JSRuntimeType;
import com.caoccao.javet.exceptions.JavetCompilationException;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.exceptions.JavetExecutionException;
import com.caoccao.javet.interception.logging.JavetStandardConsoleInterceptor;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.engine.JavetEngineConfig;
import com.caoccao.javet.interop.engine.JavetEnginePool;
import com.violeth.blockjs.blockjs.BlockJS;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Chat;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Player;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.World;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class JSExecutionInterface {
    public File file;
    public NodeRuntime runtime;

    public JSExecutionInterface(File file) {
        this.file = file;
    }

    NodeRuntime createRuntime(File file) throws JavetException {
        JavetEngineConfig config = new JavetEngineConfig();
        config.setJSRuntimeType(JSRuntimeType.Node);

        JavetEnginePool<NodeRuntime> pool = new JavetEnginePool<NodeRuntime>(config);

        NodeRuntime runtime = pool.getEngine().getV8Runtime();

        runtime.setV8ModuleResolver(((v8Runtime, resourceName, iv8Module) -> {
            // Matches the file extension
            Pattern pattern = Pattern.compile("((?<=\\w)\\.[^.]+)$");
            Matcher matcher = pattern.matcher(resourceName);

            String ext = matcher.find() ? matcher.group(1) : "";

            String trueResourceName;

            if (ext.equals("")) {
                trueResourceName = resourceName + ".js";
            } else {
                trueResourceName = resourceName;
            }

            File resourceFile = new File(file.getParentFile(), trueResourceName);

            if (!resourceFile.exists()) {
                // TODO: Resolve module from node_modules
                return null;
            }

            return runtime.getExecutor(resourceFile)
                    .setResourceName(resourceName)
                    .compileV8Module();
        }));

        return runtime;
    }

    public void registerAndRun() {
        try (NodeRuntime runtime = createRuntime(file)) {
            var consoleInterceptor = new JavetStandardConsoleInterceptor(runtime);
            consoleInterceptor.register(runtime.getGlobalObject());

            {
                try (var valueObject = runtime.createV8ValueObject()) {
                    valueObject.bind(new Chat());
                    runtime.getGlobalObject().set("JavaChat", valueObject);
                }

                try (var valueObject = runtime.createV8ValueObject()) {
                    valueObject.bind(new World());
                    runtime.getGlobalObject().set("JavaWorld", valueObject);
                }

                try (var valueObject = runtime.createV8ValueObject()) {
                    valueObject.bind(new Player());
                    runtime.getGlobalObject().set("JavaPlayer", valueObject);
                }
            }

            try {
                var fileContent = Files.readString(file.toPath());

                try (var returnedValue = runtime.getExecutor(file.toPath())
                        .setModule(true)
                        .execute()) {
                    /** TODO: handle returned value */
                } catch(JavetCompilationException | JavetExecutionException e) {
                    BlockJS.instance.getLogger().log(Level.SEVERE, "There was an error with the script: " + file.getName());
                    BlockJS.instance.getLogger().log(Level.SEVERE, e.getMessage());
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                BlockJS.instance.getLogger().log(Level.SEVERE, "There was an error with reading the file: " + file.getName());
                throw new RuntimeException(e);
            }

            /** Unregister stuff, clean up the global object, perform GC
             * (important when the context is reused, though here it doesn't make any difference as it is used only once) */
            runtime.getGlobalObject().delete("Player");
            runtime.getGlobalObject().delete("World");
            runtime.getGlobalObject().delete("Chat");

            consoleInterceptor.unregister(runtime.getGlobalObject());

            runtime.lowMemoryNotification();
        } catch(JavetException e) {
            throw new RuntimeException(e);
        }
    }
}
