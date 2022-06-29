package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Chat {
    public static Object broadcast(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(message);
        });
        return null;
    }

    public static void whisper(String message, @NotNull Player player) {
        player.sendMessage(Component.text(message));
    }
}
