package io.github.blockjs.plugin.binds

import com.caoccao.javet.values.reference.V8ValueFunction

class BlockBind(var id: Int, callback: V8ValueFunction) {
    var callback: V8ValueFunction

    init {
        this.callback = callback
    }
}
