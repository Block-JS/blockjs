package io.github.blockjs.plugin.js.runtime.api.node

import com.caoccao.javet.annotations.V8Function
import io.github.blockjs.plugin.Main

@Suppress("unused")
object Console {
    @V8Function
    fun log(vararg messages: Any) {
        Main.instance!!.logger.info(messages.joinToString(" "))
    }
    @V8Function
    fun error(vararg messages: Any) {
        Main.instance!!.logger.severe(messages.joinToString(" "))
    }
    @V8Function
    fun warn(vararg messages: Any) {
        Main.instance!!.logger.warning(messages.joinToString(" "))
    }
}
