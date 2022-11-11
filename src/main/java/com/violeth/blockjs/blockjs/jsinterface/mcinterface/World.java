package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.caoccao.javet.annotations.V8Function;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.values.reference.V8ValueFunction;
import com.violeth.blockjs.blockjs.BlockJS;

public class World {

    @V8Function
    public static int addBlockDamageListener(int x, int y, int z, V8ValueFunction callback) {
        try {
            return BlockJS.instance.binds.addBlockDamageEventListener(x, y, z, callback.toClone());
        } catch (JavetException e) {
            throw new RuntimeException(e);
        }
    }

    @V8Function
    public static void removeBlockDamageListener(int id) {
        BlockJS.instance.binds.removeBlockDamageEventListener(id);
    }

    @V8Function
    public static int addBlockBreakListener(int x, int y, int z, V8ValueFunction callback) {
        try {
            return BlockJS.instance.binds.addBlockBreakEventListener(x, y, z, callback.toClone());
        } catch (JavetException e) {
            throw new RuntimeException(e);
        }
    }

    @V8Function
    public static void removeBlockBreakListener(int id) {
        BlockJS.instance.binds.removeBlockBreakEventListener(id);
    }
}
