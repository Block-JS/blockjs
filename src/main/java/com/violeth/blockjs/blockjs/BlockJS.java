package com.violeth.blockjs.blockjs;

import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.executors.IV8Executor;
import com.caoccao.javet.values.reference.V8ValueObject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class BlockJS extends JavaPlugin {
    static public BlockJS instance;

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

        V8Host.getNodeInstance().loadLibrary();

        BlockJS.makeSureScriptsFolderExists();

        Objects.requireNonNull(this.getCommand("blockjs")).setExecutor(new CommandInterface());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        V8Host.getNodeInstance().unloadLibrary();
        /** FIXME: plugin reloading isn't working properly because of the Javet JNI filesystem lock that occurs after loading (produces an error when the server tries to read/write a file for ex. "Failed to save level"), tested only on Windows  */
    }
}
