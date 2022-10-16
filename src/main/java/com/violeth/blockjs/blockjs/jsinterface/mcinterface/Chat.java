package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.eclipsesource.v8.V8Function;
import com.violeth.blockjs.blockjs.BlockJS;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Chat {
    public void broadcast(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(message);
        });
    }
    public void whisper(String playerUUID, String message) {
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if(player != null) {
            player.sendMessage(Component.text(message));
        }
    }
}
