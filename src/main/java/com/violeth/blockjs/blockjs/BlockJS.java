package com.violeth.blockjs.blockjs;

import com.violeth.blockjs.blockjs.commands.CommandInterface;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BlockJS extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("blockjs")).setExecutor(new CommandInterface());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
