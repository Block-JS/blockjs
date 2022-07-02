package com.violeth.blockjs.blockjs.jsinterface.mcinterface.entitys;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Players {
    public Player getPlayer(String name) {
        return Bukkit.getPlayer(name);
    }
}
