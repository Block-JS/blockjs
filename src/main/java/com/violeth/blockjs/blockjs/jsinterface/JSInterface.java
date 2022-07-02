package com.violeth.blockjs.blockjs.jsinterface;

import com.eclipsesource.v8.NodeJS;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Chat;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.entitys.Players;

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

        runner.getRuntime().registerJavaMethod(chat, "broadcast", "javaBroadcast",
                new Class[] { String.class });

        // Players
        Players players = new Players();

        runner.getRuntime().registerJavaMethod(players, "getPlayer", "javaGetPlayer",
                new Class[] { String.class });
    }

    public void registerAndRun() {
        registerCallbacks();
        runJS();
    }
}
