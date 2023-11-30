package io.github.blockjs.plugin.binds

import org.bukkit.util.BlockVector
import java.io.File

/** Optional WorldEdit block selection support
 * TODO: It was just a prototype  */
class SelectionBind(selection: Array<BlockVector>, script: File) {
    var selection: Array<BlockVector>
    var scripts: MutableList<File>

    init {
        this.selection = selection
        scripts = mutableListOf()
        scripts.add(script)
    }
}
