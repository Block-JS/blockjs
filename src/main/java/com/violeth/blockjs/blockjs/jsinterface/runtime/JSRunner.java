package com.violeth.blockjs.blockjs.jsinterface.runtime;

import com.eclipsesource.v8.V8;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Scanner;

public final class JSRunner {
    private final V8 runner = V8.createV8Runtime();

    public File file;

    public JSRunner(File file) {
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

    public Object runJS() {
        return runner.executeScript(getFileText());
    }
}
