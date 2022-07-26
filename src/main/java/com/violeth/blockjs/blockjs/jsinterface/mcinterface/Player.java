package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.caoccao.javet.annotations.V8Function;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.UUID;

public class Player {
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
