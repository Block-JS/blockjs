package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.eclipsesource.v8.V8Function;
import com.violeth.blockjs.blockjs.BlockJS;

public class World {
    public int addBlockDamageListener(int x, int y, int z, V8Function callback) {
        return BlockJS.instance.binds.addBlockDamageEventListener(x, y, z, callback.twin());
    }
    public void removeBlockDamageListener(int id) {
        BlockJS.instance.binds.removeBlockDamageEventListener(id);
    }

    public int addBlockBreakListener(int x, int y, int z, V8Function callback) {
        return BlockJS.instance.binds.addBlockBreakEventListener(x, y, z, callback.twin());
    }
    public void removeBlockBreakListener(int id) {
        BlockJS.instance.binds.removeBlockBreakEventListener(id);
    }

    public int addBlockInteractListener(int x, int y, int z, V8Function callback) {
        return BlockJS.instance.binds.addBlockInteractEventListener(x, y, z, callback.twin());
    }
    public void removeBlockInteractListener(int id) {
        BlockJS.instance.binds.removeBlockInteractEventListener(id);
    }
}
