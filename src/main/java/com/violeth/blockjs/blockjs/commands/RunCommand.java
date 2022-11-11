package com.violeth.blockjs.blockjs.commands;

import com.caoccao.javet.exceptions.JavetException;
import com.violeth.blockjs.blockjs.BlockJS;
import com.violeth.blockjs.blockjs.JSRuntimeManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.logging.Level;

public class RunCommand extends Command {
    public RunCommand() {
        super("run");
    }
    @Override
    public void handle(CommandSender sender, String[] args) {
        if(args.length > 1) {
            var rawScriptPath = args[1];

            var scriptPath = new File(BlockJS.getScriptsFolder().getAbsolutePath(), rawScriptPath);

            /** If file doesn't exist */
            if (!scriptPath.isFile()) {
                sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                        Component.text().append(
                                Component.text("There's no "),
                                Component.text(rawScriptPath).color(TextColor.color(0xFFFF22)),
                                Component.text(" script!")
                        ).color(TextColor.color(0xFF5555))
                ));

                return;
            }

            try (var node = JSRuntimeManager.createNodeJS()) {
                node.getExecutor(scriptPath).execute();

                        /** TODO: Make sure every listener is released, then release the actual runtime
                         *      This probably gonna need rewriting of the event system so each runtime has list of its own event listeners */
            } catch (JavetException e) {
                Bukkit.getLogger().log(Level.SEVERE, "Error while running script", e);
            }
        } else {
            sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                Component.text().append(
                    Component.text("You have to provide a file!")
                ).color(TextColor.color(0xFF5555))
            ));
        }
    }
}
