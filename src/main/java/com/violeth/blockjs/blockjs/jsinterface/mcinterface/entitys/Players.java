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
}
