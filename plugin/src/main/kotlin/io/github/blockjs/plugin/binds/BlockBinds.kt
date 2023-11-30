package io.github.blockjs.plugin.binds

class BlockBinds(var x: Int, var y: Int, var z: Int) {
    var map: HashMap<Int, BlockBind> = HashMap()

    constructor(x: Int, y: Int, z: Int, bind: BlockBind) : this(x, y, z) {
        map[bind.id] = bind
    }
}
