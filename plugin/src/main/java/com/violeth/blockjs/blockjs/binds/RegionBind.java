package com.violeth.blockjs.blockjs.binds;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** Optional WorldGuard region support
 * TODO: It was just a prototype */
public class RegionBind {
    public String regionName;

    public List<File> scripts;

    public RegionBind(String regionName, File script) {
        this.regionName = regionName;

        this.scripts = new ArrayList<>();

        this.scripts.add(script);
    }
}
