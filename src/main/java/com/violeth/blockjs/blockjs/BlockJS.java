package com.violeth.blockjs.blockjs;

import com.caoccao.javet.exceptions.JavetException;
import com.violeth.blockjs.blockjs.binds.Binds;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.executors.IV8Executor;
import com.caoccao.javet.values.reference.V8ValueObject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
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

        //FIXME Update event handlers to use Javet

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

                            try (var callback = blockBind.getValue().callback) {
                                var callbackRuntime = callback.getV8Runtime();

                                callback.callVoid(callbackRuntime.createV8ValueObject(), callbackRuntime.createV8ValueObject().set("player", event.getPlayer()));
                            } catch (JavetException e) {
                                e.printStackTrace();
                            }

                            var callback = blockBind.getValue().callback;
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

                            try (var callback = blockBind.getValue().callback) {
                                var callbackRuntime = callback.getV8Runtime();

                                callback.callVoid(callbackRuntime.createV8ValueObject(), callbackRuntime.createV8ValueObject().set("player", event.getPlayer()));
                            } catch (JavetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        V8Host.getNodeInstance().unloadLibrary();
        /** FIXME: plugin reloading isn't working properly because of the Javet JNI filesystem lock that occurs after loading (produces an error when the server tries to read/write a file for ex. "Failed to save level"), tested only on Windows  */
    }
}
