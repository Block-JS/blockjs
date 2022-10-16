package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.eclipsesource.v8.V8Function;
import com.violeth.blockjs.blockjs.BlockJS;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.UUID;

public class Player {
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

    public Collection<? extends org.bukkit.entity.Player> getOnlinePlayersUUIDs() {
        return org.bukkit.Bukkit.getOnlinePlayers();
    }

    public String getPlayerNameByUUID(String playerUUID) {
        var player = Bukkit.getPlayer(playerUUID);

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
