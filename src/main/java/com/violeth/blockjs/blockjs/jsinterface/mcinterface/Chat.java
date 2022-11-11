package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.caoccao.javet.annotations.V8Function;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class Chat {
    @V8Function
    public static void broadcast(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(message);
        });
    }

    @V8Function
    public static void whisper(String uuid, String message) {
        var player = Bukkit.getPlayer(UUID.fromString(uuid));

        if(player != null) {
            player.sendMessage(Component.text(message));
        }
    }
}
