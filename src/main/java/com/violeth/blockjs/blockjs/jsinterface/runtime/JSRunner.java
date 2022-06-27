package com.violeth.blockjs.blockjs.jsinterface.runtime;

import com.eclipsesource.v8.V8;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Scanner;

public class JSRunner {
    private static final V8 runner = V8.createV8Runtime();

    public static File file;

    public JSRunner(File file) {
        JSRunner.file = file;
    }

    public static @NotNull String getFileText() {
        try {
            Scanner scanner = new Scanner(file);

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

    public static void main(String[] args) {
        String fileText = getFileText();

        Object result = runner.executeScript(fileText);
    }
}
