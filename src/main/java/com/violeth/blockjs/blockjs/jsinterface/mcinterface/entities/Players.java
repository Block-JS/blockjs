package com.violeth.blockjs.blockjs.jsinterface.mcinterface.entities;

import com.caoccao.javet.annotations.V8Function;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Players {
    @V8Function
    public Player getPlayer(String name) {
        return Bukkit.getPlayer(name);
    }

    @V8Function
    public Collection<? extends Player> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers();
    }

    @V8Function
    public void damage(String name, double damage) {
        Player player = getPlayer(name);

        if (player != null) {
            player.damage(damage);
        }
    }

    @V8Function
    public void heal(String name, double heal) {
        Player player = getPlayer(name);

        if (player != null) {
            player.setHealth(player.getHealth() + heal);
        }
    }
}
