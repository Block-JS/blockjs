package com.violeth.blockjs.blockjs.jsinterface;

import com.caoccao.javet.exceptions.JavetException;
import com.violeth.blockjs.blockjs.BlockJS;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSRunner {
    File folder;

    public JSRunner(File folder) {
        this.folder = folder;
    }

    public void getAndRunJS() {
        var files = FileReader.getListOfFiles(folder).toArray(new File[0]);

        List<File> noNodeModules = new ArrayList<>();

        for (var file : files) {
            if (!file.getParent().equals("node_modules")) {
                noNodeModules.add(file);
            }
        }

        var interfaces = new JSExecutionInterface[files.length];

        for (var i = 0; i < files.length; i++) {
            var file = files[i];

            var ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);

            if (ext.equals("js")) {
                var jsInterface = new JSExecutionInterface(file.getAbsoluteFile());

                interfaces[i] = jsInterface;

                jsInterface.registerAndRun();
            }
        }
    }
}
