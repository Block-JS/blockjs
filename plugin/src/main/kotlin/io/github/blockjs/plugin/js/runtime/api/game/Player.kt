package io.github.blockjs.plugin.js.runtime.api.game

import com.caoccao.javet.annotations.V8Function
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player
import java.util.UUID

@Suppress("unused")
object Player {
    @V8Function
    fun setGameMode(playerUUID: String?, gameMode: Int) {
        val player  = Bukkit.getPlayer(UUID.fromString(playerUUID))
        val gameModeEnumValue = GameMode.getByValue(gameMode)

        if (player != null && gameModeEnumValue != null) {
            player.gameMode = gameModeEnumValue
        }
    }

    @V8Function
    fun setWalkSpeed(playerUUID: String, speed: Float) {
        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))

        if (player != null) {
            player.walkSpeed = speed
        }
    }

    @V8Function
    fun setFlySpeed(playerUUID: String, speed: Float) {
        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))

        if (player != null) {
            player.flySpeed = speed
        }
    }

    @V8Function
    fun setFlying(playerUUID: String?, state: Boolean) {
        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))

        if (player != null) {
            player.isFlying = state
        }
    }

    @V8Function
    fun setSneaking(playerUUID: String, state: Boolean) {
        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))

        if (player != null) {
            player.isSneaking = state
        }
    }

    @V8Function
    fun setSprinting(playerUUID: String, state: Boolean) {
        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))

        if (player != null) {
            player.isSprinting = state
        }
    }

    @V8Function
    fun setHealth(playerUUID: String, health: Double) {
        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))

        if (player != null) {
            player.health = health
        }
    }

    @V8Function
    fun setExhaustion(playerUUID: String, exhaustion: Float) {

        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))

        if (player != null) {
            player.exhaustion = exhaustion
        }
    }

    @V8Function
    fun setPosition(playerUUID: String, x: Double, y: Double, z: Double) {
        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))
        if (player != null) {
            val location = player.location

            location.x = x
            location.y = y
            location.z = z
        }
    }

    @V8Function
    fun setVelocity(playerUUID: String, x: Double, y: Double, z: Double) {
        val player = Bukkit.getPlayer(UUID.fromString(playerUUID))

        if (player != null) {
            val velocity = player.velocity

            velocity.x = x
            velocity.y = y
            velocity.z = z
        }
    }

    @V8Function
    fun doDamage(uuid: String, damage: Double) {
        val player = Bukkit.getPlayer(UUID.fromString(uuid))

        if (player != null) {
            player.damage(damage)
        }
    }

    @V8Function
    fun heal(uuid: String, heal: Double) {
        val player = Bukkit.getPlayer(UUID.fromString(uuid))

        if (player != null) {
            player.health += heal
        }
    }
}
