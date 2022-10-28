package com.violeth.blockjs.blockjs.commands;

import com.violeth.blockjs.blockjs.BlockJS;
import com.violeth.blockjs.blockjs.JSRuntimeManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.File;

public class RunCommand extends Command {
    public RunCommand() {
        super("run");
    }
    @Override
    public void handle(CommandSender sender, String[] args) {
        if(args.length > 1) {
            var rawScriptPath = args[1];

            var scriptPath = new File(BlockJS.getScriptsFolder(), rawScriptPath);

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

            var node = JSRuntimeManager.createNodeJS();

            node.exec(scriptPath);

            Bukkit.getScheduler().runTaskTimer(BlockJS.instance, (task) -> {
                if(!node.isRunning()) {
                    task.cancel();

                    JSRuntimeManager.runtimes.remove(node);
                    node.release();
                } else {
                    node.handleMessage();
                }
            }, 0, 1);
        } else {
            sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                Component.text().append(
                    Component.text("You have to provide a file!")
                ).color(TextColor.color(0xFF5555))
            ));
        }
    }
}
