package com.violeth.blockjs.blockjs.jsinterface;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.NodeJS;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Chat;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Scanner;

public final class JSInterface {
    private final NodeJS runner = NodeJS.createNodeJS();

    public File file;

    public JSInterface(File file) {
        this.file = file;
    }

    public @NotNull String getFileText() {
        try {
            Scanner scanner = new Scanner(this.file);

            StringBuilder sb = new StringBuilder();

            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ENOENT: no such file or directory");
        }
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
}
