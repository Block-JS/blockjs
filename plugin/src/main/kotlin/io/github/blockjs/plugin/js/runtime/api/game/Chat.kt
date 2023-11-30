package io.github.blockjs.plugin.js.runtime.api.game

import com.caoccao.javet.annotations.V8Function
import org.bukkit.Bukkit
import net.kyori.adventure.text.Component
import java.util.UUID

@Suppress("unused")
object Chat {
    @V8Function
    fun broadcast(message: String) {
        Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(message) }
    }

    @V8Function
    fun whisper(uuid: String, message: String) {
        val player = Bukkit.getPlayer(UUID.fromString(uuid))

        if (player != null) {
            player.sendMessage(Component.text(message))
        }
    }
}
