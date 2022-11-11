package com.violeth.blockjs.blockjs.binds;

import com.caoccao.javet.values.reference.V8ValueFunction;

public class BlockBind {
    public int id;
    public V8ValueFunction callback;
    public BlockBind(int id, V8ValueFunction callback) {
        this.id = id;
        this.callback = callback;
    }
}
