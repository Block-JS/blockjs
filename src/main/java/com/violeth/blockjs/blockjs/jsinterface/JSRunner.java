package com.violeth.blockjs.blockjs.jsinterface;

import java.io.File;

public class JSRunner {
    String path;

    public JSRunner(String path) {
        this.path = path;
    }

    public void getAndRunJS() {
        FileReader reader = new FileReader(path);
        File[] files = reader.getListOfFiles();

        JSInterface[] interfaces = new JSInterface[files.length];

        for (int i = 0; i < files.length; i++) {
            String ext = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1);

            if (ext.equals("js")) {
                interfaces[i] = new JSInterface(files[i]);
                interfaces[i].registerAndRun();
            }
        }
    }
}
