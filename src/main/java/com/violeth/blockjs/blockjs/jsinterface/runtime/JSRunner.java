package com.violeth.blockjs.blockjs.jsinterface.runtime;

import com.eclipsesource.v8.V8;

import java.io.File;

public class JSRunner {
    private static final V8 runner = V8.createV8Runtime();

    public File file;

    public JSRunner(File file) {
        this.file = file;
    }

    public static void main(String[] args) {
        Object result = runner.executeScript("'Hello World!'");
        System.out.println(result);
    }
}
