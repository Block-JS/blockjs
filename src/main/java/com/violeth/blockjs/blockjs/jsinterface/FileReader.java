package com.violeth.blockjs.blockjs.jsinterface;

import java.io.File;
import java.nio.file.Path;

public class FileReader {
    public String path;

    public FileReader(String path) {
        this.path = path;
    }

    public File[] getListOfFiles() {
        File file = new File(System.getProperty("user.dir"), "blockjs/scripts");

        if (file.isDirectory()) {
            return file.listFiles();
        } else {
            throw new RuntimeException("Not a directory");
        }
    }
}
