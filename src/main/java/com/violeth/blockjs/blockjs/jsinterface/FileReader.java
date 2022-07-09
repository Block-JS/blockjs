package com.violeth.blockjs.blockjs.jsinterface;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileReader {

    public List<File> getListOfFiles(String path) {
        File file = new File(path);

        List<File> files = new ArrayList<>();

        if (file.listFiles() == null) {
            return files;
        }

        for (File iterfile : Objects.requireNonNull(file.listFiles())) {
            if (iterfile.isFile()) {
                files.add(iterfile.getAbsoluteFile());
            } else if (iterfile.isDirectory()) {
                List<File> subfiles = getListOfFiles(iterfile.getAbsolutePath());
                if (subfiles.size() > 0) {
                    files.addAll(subfiles);
                }
            }
        }

        return files;
    }
}
