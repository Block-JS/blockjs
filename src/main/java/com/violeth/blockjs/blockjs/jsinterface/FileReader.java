package com.violeth.blockjs.blockjs.jsinterface;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileReader {

    public static List<File> getListOfFiles(File folder) {
        List<File> files = new ArrayList<>();

        if (folder.listFiles() == null) {
            return files;
        }

        for (var fsEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fsEntry.isFile()) {
                files.add(fsEntry.getAbsoluteFile());
            } else if (fsEntry.isDirectory()) {
                var subFiles = getListOfFiles(fsEntry);

                if (subFiles.size() > 0) {
                    files.addAll(subFiles);
                }
            }
        }

        return files;
    }
}
