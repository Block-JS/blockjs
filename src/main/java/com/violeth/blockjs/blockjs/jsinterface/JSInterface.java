package com.violeth.blockjs.blockjs.jsinterface;

import com.eclipsesource.v8.NodeJS;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Chat;

import java.io.File;

public final class JSInterface {
    private final NodeJS runner = NodeJS.createNodeJS();

    public File file;

    public JSInterface(File file) {
        this.file = file;
    }

    public void runJS() {
        runner.exec(file);

        while (runner.isRunning()) {
            runner.handleMessage();
        }

        runner.release();
    }

    public void registerCallbacks() {
        // Chat
        Chat chat = new Chat();

        runner.getRuntime().registerJavaMethod(chat, "broadcast", "broadcast",
                new Class[] { String.class });
    }

    public void registerAndRun() {
        registerCallbacks();
        runJS();
    }
}
