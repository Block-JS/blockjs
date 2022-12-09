package com.violeth.blockjs.blockjs.binds;

import org.bukkit.util.BlockVector;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** Optional WorldEdit block selection support
 * TODO: It was just a prototype */
public class SelectionBind {
    public BlockVector[] selection;

    public List<File> scripts;

    public SelectionBind(BlockVector[] selection, File script) {
        this.selection = selection;

        this.scripts = new ArrayList<>();

        this.scripts.add(script);
    }
}
