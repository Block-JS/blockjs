package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import org.bukkit.Bukkit;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Chat {
    public void broadcast(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Component.text(message));
        }
    }

    public void whisper(String message, @NotNull Player player) {
        player.sendMessage(Component.text(message));
    }
}
