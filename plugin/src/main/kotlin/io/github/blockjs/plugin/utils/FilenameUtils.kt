package io.github.blockjs.plugin.utils

object FilenameUtils {
    fun getParts(name: String, separator: String): List<String> {
        return name.split(separator)
    }

    fun getParts(name: String): List<String> {
        return getParts(name, ".")
    }
}