package com.violeth.blockjs.blockjs.binds;

import java.util.HashMap;

public class BlockBinds {
    public int x;
    public int y;
    public int z;
    public HashMap<Integer, BlockBind> map = new HashMap<>();
    public BlockBinds(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public BlockBinds(int x, int y, int z, BlockBind bind) {
        this(x, y, z);
        this.map.put(bind.id, bind);
    }
}
