package com.violeth.blockjs.blockjs.jsinterface.mcinterface.entitys;

import com.caoccao.javet.annotations.V8Function;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Players {
    @V8Function
    public Player javaGetPlayer(String name) {
        return Bukkit.getPlayer(name);
    }

    @V8Function
    public Collection<? extends Player> javaGetOnlinePlayers() {
        return Bukkit.getOnlinePlayers();
    }

    @V8Function
    public void damage(String name, double damage) {
        Player player = javaGetPlayer(name);

        if (player != null) {
            player.damage(damage);
        }
    }

    @V8Function
    public void heal(String name, double heal) {
        Player player = javaGetPlayer(name);

        if (player != null) {
            player.setHealth(player.getHealth() + heal);
        }
    }
}
