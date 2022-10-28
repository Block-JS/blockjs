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
    public void setGameMode(String playerUUID, GameMode gameMode) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (player != null) {
            player.setGameMode(gameMode);
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
