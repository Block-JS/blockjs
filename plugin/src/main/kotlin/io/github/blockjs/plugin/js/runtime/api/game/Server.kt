package io.github.blockjs.plugin.js.runtime.api.game

import com.caoccao.javet.annotations.V8Function
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

@Suppress("unused")
object Server {
    @V8Function
    fun getOnlinePlayerUUIDByName(name: String): String? {
        val player = Bukkit.getPlayer(name)

        if (player != null) {
            return player.uniqueId.toString()
        } else {
            return null
        }
    }

    @V8Function
    fun getOfflinePlayerUUIDByName(name: String): String? {
        val player = Bukkit.getOfflinePlayer(name)

        if (player != null) {
            return player.uniqueId.toString()
        } else {
            return null
        }
    }

    @V8Function
    fun getOnlinePlayersUUIDs(): MutableCollection<out Player> {
        return Bukkit.getOnlinePlayers()
    }

    @V8Function
    fun getPlayerNameByUUID(uuid: UUID): String? {
        val player = Bukkit.getPlayer(uuid)

        if (player != null) {
            return player.name
        } else {
            return null
        }
    }
}
