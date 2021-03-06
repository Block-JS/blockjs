package com.violeth.blockjs.blockjs.jsinterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JSRunner {
    String path;

    public JSRunner(String path) {
        this.path = path;
    }

    public void getAndRunJS() {
        FileReader reader = new FileReader(path);
        File[] files = reader.getListOfFiles();

        List<File> noNodeModules = new ArrayList<>();

        for (File file : files) {
            if (!file.getParent().equals("node_modules")) {
                noNodeModules.add(file);
            }
        }

        JSInterface[] interfaces = new JSInterface[noNodeModules.size()];

        for (int i = 0; i < noNodeModules.size(); i++) {
            String ext = noNodeModules.get(i).getName().substring(files[i].getName().lastIndexOf(".") + 1);

            if (ext.equals("js")) {
                interfaces[i] = new JSInterface(files[i]);
                interfaces[i].registerAndRun();
            }
        }
    }
}
