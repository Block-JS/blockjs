package io.github.blockjs.plugin.js.runtime.utils

import java.io.File
import java.util.Objects

object FileReader {
    fun getListOfFiles(folder: File): MutableList<File> {
        val files = mutableListOf<File>()

        if (folder.listFiles() == null) {
            return files
        }

        for (fsEntry in Objects.requireNonNull(folder.listFiles())) {
            if (fsEntry.isFile()) {
                files.add(fsEntry.getAbsoluteFile())
            } else if (fsEntry.isDirectory()) {
                if (fsEntry.getName().equals("node_modules")) {
                    continue
                }

                val subFiles = getListOfFiles(fsEntry)

                if (subFiles.size > 0) {
                    files.addAll(subFiles)
                }
            }
        }
        return files
    }
}
