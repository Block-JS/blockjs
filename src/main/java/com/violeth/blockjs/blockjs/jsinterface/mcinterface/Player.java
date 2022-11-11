package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.violeth.blockjs.blockjs.BlockJS;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import java.util.Collection;
import java.util.UUID;

public class Player {
    V8 v8;
    public Player(V8 v8) {
        this.v8 = v8;
    }
    public void setGameMode(String playerUUID, int gameMode) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));
        var gameModeEnumValue = GameMode.getByValue(gameMode);

        if (player != null && gameModeEnumValue != null) {
            player.setGameMode(gameModeEnumValue);
        }
    }
    public void setWalkSpeed(String playerUUID, float speed) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setWalkSpeed(speed);
        }
    }
    public void setFlySpeed(String playerUUID, float speed) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setFlySpeed(speed);
        }
    }
    public void setFlying(String playerUUID, boolean state) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setFlying(state);
        }
    }
    public void setSneaking(String playerUUID, boolean state) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setSneaking(state);
        }
    }
    public void setSprinting(String playerUUID, boolean state) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setSprinting(state);
        }
    }
    public void setHealth(String playerUUID, double health) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setHealth(health);
        }
    }
    public void setExhaustion(String playerUUID, float exhaustion) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setExhaustion(exhaustion);
        }
    }
    public void setPosition(String playerUUID, double x, double y, double z) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            var location = player.getLocation();

            location.setX(x);
            location.setY(y);
            location.setZ(z);
        }
    }
    public void setVelocity(String playerUUID, double x, double y, double z) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            var velocity = player.getVelocity();

            velocity.setX(x);
            velocity.setY(y);
            velocity.setZ(z);
        }
    }
    public String getOnlinePlayerUUIDByName(String name) {
        var player = Bukkit.getPlayer(name);

        if(player != null) {
            return player.getUniqueId().toString();
        }

        return null;
    }

    public String getOfflinePlayerUUIDByName(String name) {
        var player = Bukkit.getOfflinePlayer(name);

        if(player != null) {
            return player.getUniqueId().toString();
        }

        return null;
    }

    public V8Array getOnlinePlayersUUIDs() {
        var players = org.bukkit.Bukkit.getOnlinePlayers();
        var uuids = new V8Array(v8);

        for(var playersIt = players.iterator(); playersIt.hasNext();) {
            uuids.push(playersIt.next().getUniqueId().toString());
        }

        return uuids;
    }

    public String getPlayerNameByUUID(String playerUUID) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if(player != null) {
            return player.getName();
        }

        return null;
    }

    public void doDamage(String playerUUID, double damage) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.damage(damage);
        }
    }

    public void heal(String playerUUID, double heal) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setHealth(player.getHealth() + heal);
        }
    }
}
