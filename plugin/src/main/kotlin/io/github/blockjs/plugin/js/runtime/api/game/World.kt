package io.github.blockjs.plugin.js.runtime.api.game

import com.caoccao.javet.annotations.V8Function
import com.caoccao.javet.exceptions.JavetException
import com.caoccao.javet.values.reference.V8ValueFunction
import io.github.blockjs.plugin.Main
import org.bukkit.Bukkit

@Suppress("unused")
object World {
    @V8Function
    fun getPlayerCount(): Int {
        return Bukkit.getOnlinePlayers().size
    }

    @V8Function
    fun addBlockDamageListener(x: Int, y: Int, z: Int, callback: V8ValueFunction): Int {
        try {
            callback.setWeak()

            return Main.instance!!.binds.addBlockDamageEventListener(x, y, z, callback)
        } catch (e: JavetException) {
            throw RuntimeException(e)
        }
    }

    @V8Function
    fun removeBlockDamageListener(id: Int) {
        Main.instance!!.binds.removeBlockDamageEventListener(id)
    }

    @V8Function
    fun addBlockBreakListener(x: Int, y: Int, z: Int, callback: V8ValueFunction): Int {
        try {
            callback.setWeak()

            return Main.instance!!.binds.addBlockBreakEventListener(x, y, z, callback.toClone())
        } catch (e: JavetException) {
            throw RuntimeException(e)
        }
    }

    @V8Function
    fun removeBlockBreakListener(id: Int) {
        Main.instance!!.binds.removeBlockBreakEventListener(id)
    }
}
