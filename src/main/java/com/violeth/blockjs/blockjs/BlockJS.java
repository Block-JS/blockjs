package com.violeth.blockjs.blockjs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BlockJS extends JavaPlugin {
    static public BlockJS instance;

    static public Component getPluginMessagePrefix() {
        return Component.text("[")
                .append(Component.text(BlockJS.instance.getName()).color(TextColor.color(0x00BBFF)))
                .append(Component.text("] "));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Objects.requireNonNull(this.getCommand("blockjs")).setExecutor(new CommandInterface());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
