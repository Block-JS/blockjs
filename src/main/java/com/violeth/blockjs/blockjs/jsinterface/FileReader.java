package com.violeth.blockjs.blockjs.jsinterface;

import java.io.File;

public class FileReader {
    public String path;

    public FileReader(String path) {
        this.path = path;
    }

    public File[] getListOfFiles() {
        File file = new File(path);
        if (file.isDirectory()) {
            return file.listFiles();
        } else {
            throw new RuntimeException("ENOENT: no such file or directory");
        }
    }
}
