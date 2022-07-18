package com.violeth.blockjs.blockjs.commands;

import com.violeth.blockjs.blockjs.BlockJS;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class HelpCommand extends Command {
    {
        key = "help";
    }
    @Override
    public void handle(CommandSender sender, String[] args) {
        sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
            Component.text("Available commands: help, test, version")
        ));
    }
}
