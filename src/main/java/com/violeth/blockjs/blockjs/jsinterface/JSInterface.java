package com.violeth.blockjs.blockjs.jsinterface;

import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interception.logging.JavetStandardConsoleInterceptor;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.values.reference.V8ValueObject;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Chat;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.entities.Players;

import java.io.File;

public final class JSInterface {
    public File file;

    public JSInterface(File file) {
        this.file = file;
    }

    public void registerAndRun() throws JavetException {
        try (NodeRuntime runtime = V8Host.getNodeInstance().createV8Runtime()) {
            JavetStandardConsoleInterceptor consoleInterceptor = new JavetStandardConsoleInterceptor(runtime);
            consoleInterceptor.register(runtime.getGlobalObject());

            // Interceptors

            // Chat
            Chat chat = new Chat();

            try (V8ValueObject v8ValueObject = runtime.createV8ValueObject()) {
                runtime.getGlobalObject().set("javaChat", v8ValueObject);
                v8ValueObject.bind(chat);
            }

            // Players
            Players players = new Players();

            try (V8ValueObject v8ValueObject = runtime.createV8ValueObject()) {
                runtime.getGlobalObject().set("javaPlayers", v8ValueObject);
                v8ValueObject.bind(players);
            }

            runtime.getExecutor(file).execute();
        }
    }
}
