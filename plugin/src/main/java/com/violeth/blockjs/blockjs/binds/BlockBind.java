package com.violeth.blockjs.blockjs.binds;

import com.eclipsesource.v8.V8Function;

public class BlockBind {
    public int id;
    public V8Function callback;
    public BlockBind(int id, V8Function callback) {
        this.id = id;
        this.callback = callback;
    }
}
