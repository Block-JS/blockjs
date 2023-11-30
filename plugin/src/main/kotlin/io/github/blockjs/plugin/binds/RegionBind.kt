package io.github.blockjs.plugin.binds

import java.io.File

/** Optional WorldGuard region support
 * TODO: It was just a prototype  */
class RegionBind(
    var regionName: String,
    script: File,
    scripts: MutableList<File> = mutableListOf()
) {
    init {
        scripts.add(script)
    }
}
