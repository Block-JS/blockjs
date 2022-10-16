package com.violeth.blockjs.blockjs;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.violeth.blockjs.blockjs.binds.Binds;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.Objects;

public class BlockJS extends JavaPlugin {
    static public BlockJS instance;
    public Binds binds = new Binds();

    public BlockJS() {
        super();
    }

    protected BlockJS(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }
    static public File getScriptsFolder() {
        return new File(instance.getDataFolder(), "scripts");
    }
    static public Component getPluginMessagePrefix() {
        return Component.text("[")
                .append(Component.text(instance.getName()).color(TextColor.color(0x00BBFF)))
                .append(Component.text("] "));
    }
    static void makeSureScriptsFolderExists() {
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }

        var fsEntry = new File(instance.getDataFolder(), "scripts");

        if(!fsEntry.exists()) {
            fsEntry.mkdir();
        }
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        BlockJS.makeSureScriptsFolderExists();

        Objects.requireNonNull(this.getCommand("blockjs")).setExecutor(new CommandInterface());

        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onBlockDamage(BlockDamageEvent event) {
                var block = event.getBlock();

                var blockX = block.getX();
                var blockY = block.getY();
                var blockZ = block.getZ();

                for(var blockBinds: binds.onBlockDamage) {
                    if(blockBinds.x == blockX && blockBinds.y == blockY && blockBinds.z == blockZ) {
                        var blockBindsEntrySet = blockBinds.map.entrySet();

                        for(var blockBindIterator = blockBindsEntrySet.iterator(); blockBindIterator.hasNext();) {
                            var blockBind = blockBindIterator.next();

                            var callback = blockBind.getValue().callback;
                            var callbackRuntime = callback.getRuntime();

                            callback.call(null, new V8Array(callbackRuntime).push(
                                new V8Object(callbackRuntime)
                                    .add("player", event.getPlayer().getUniqueId().toString())
                            ));
                        }
                    }
                }
            }
            @EventHandler
            public void onBlockBreak(BlockBreakEvent event) {
                var block = event.getBlock();

                var blockX = block.getX();
                var blockY = block.getY();
                var blockZ = block.getZ();

                for(var blockBinds: binds.onBlockBreak) {
                    if(blockBinds.x == blockX && blockBinds.y == blockY && blockBinds.z == blockZ) {
                        var blockBindsEntrySet = blockBinds.map.entrySet();

                        for(var blockBindIterator = blockBindsEntrySet.iterator(); blockBindIterator.hasNext();) {
                            var blockBind = blockBindIterator.next();

                            var callback = blockBind.getValue().callback;
                            var callbackRuntime = callback.getRuntime();

                            callback.call(null, new V8Array(callbackRuntime).push(
                                new V8Object(callbackRuntime)
                                    .add("player", event.getPlayer().getUniqueId().toString())
                            ));
                        }
                    }
                }
            }
        }, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
