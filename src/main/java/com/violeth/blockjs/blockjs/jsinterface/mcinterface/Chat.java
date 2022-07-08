package com.violeth.blockjs.blockjs.jsinterface.mcinterface;

import com.caoccao.javet.annotations.V8Function;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Chat {
    @V8Function
    public static Object broadcast(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(message);
        });

        return null;
    }

    @V8Function
    public static void whisper(String message, @NotNull Player player) {
        player.sendMessage(Component.text(message));
    }
}
