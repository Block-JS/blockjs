package com.violeth.blockjs.blockjs.jsinterface.mcinterface.entitys;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Players {
    public Player getPlayer(String name) {
        return Bukkit.getPlayer(name);
    }

    public Collection<? extends Player> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers();
    }

    public void damage(String name, double damage) {
        Player player = getPlayer(name);

        if (player != null) {
            player.damage(damage);
        }
    }

    public void heal(String name, double heal) {
        Player player = getPlayer(name);

        if (player != null) {
            player.setHealth(player.getHealth() + heal);
        }
    }
}
