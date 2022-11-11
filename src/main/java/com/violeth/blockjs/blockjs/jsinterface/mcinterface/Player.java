package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.caoccao.javet.annotations.V8Function;
import org.bukkit.Bukkit;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.violeth.blockjs.blockjs.BlockJS;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import java.util.Collection;
import java.util.UUID;

public class Player {
    @V8Function
    public void setGameMode(String playerUUID, int gameMode) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));
        var gameModeEnumValue = GameMode.getByValue(gameMode);

        if (player != null && gameModeEnumValue != null) {
            player.setGameMode(gameModeEnumValue);
        }
    }

    @V8Function
    public void setWalkSpeed(String playerUUID, float speed) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setWalkSpeed(speed);
        }
    }

    @V8Function
    public void setFlySpeed(String playerUUID, float speed) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setFlySpeed(speed);
        }
    }

    @V8Function
    public void setFlying(String playerUUID, boolean state) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setFlying(state);
        }
    }

    @V8Function
    public void setSneaking(String playerUUID, boolean state) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setSneaking(state);
        }
    }

    @V8Function
    public void setSprinting(String playerUUID, boolean state) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setSprinting(state);
        }
    }

    @V8Function
    public void setHealth(String playerUUID, double health) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setHealth(health);
        }
    }

    @V8Function
    public void setExhaustion(String playerUUID, float exhaustion) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setExhaustion(exhaustion);
        }
    }

    @V8Function
    public void setPosition(String playerUUID, double x, double y, double z) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            var location = player.getLocation();

            location.setX(x);
            location.setY(y);
            location.setZ(z);
        }
    }

    @V8Function
    public void setVelocity(String playerUUID, double x, double y, double z) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            var velocity = player.getVelocity();

            velocity.setX(x);
            velocity.setY(y);
            velocity.setZ(z);
        }
    }

    @V8Function
    public String getOnlinePlayerUUIDByName(String name) {
        var player = Bukkit.getPlayer(name);

        if(player != null) {
            return player.getUniqueId().toString();
        }

        return null;
    }

    @V8Function
    public String getOfflinePlayerUUIDByName(String name) {
        var player = Bukkit.getOfflinePlayer(name);

        if(player != null) {
            return player.getUniqueId().toString();
        }

        return null;
    }

    @V8Function
    public Collection<? extends org.bukkit.entity.Player> getOnlinePlayersUUIDs() {
        return org.bukkit.Bukkit.getOnlinePlayers();
    }

    @V8Function
    public String getPlayerNameByUUID(UUID uuid) {
        var player = Bukkit.getPlayer(uuid);

        if(player != null) {
            return player.getName();
        }

        return null;
    }

    @V8Function
    public void doDamage(String uuid, double damage) {
        var player = Bukkit.getPlayer(UUID.fromString(uuid));

        if (player != null) {
            player.damage(damage);
        }
    }

    @V8Function
    public void heal(String uuid, double heal) {
        var player = Bukkit.getPlayer(UUID.fromString(uuid));

        if (player != null) {
            player.setHealth(player.getHealth() + heal);
        }
    }
}
