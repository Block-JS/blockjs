package com.violeth.blockjs.blockjs.commands;

import com.violeth.blockjs.blockjs.BlockJS;
import com.violeth.blockjs.blockjs.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }
    @Override
    public void handle(CommandSender sender, String[] args) {
        var message = Component.text("Available commands: ");

        for(var i = 0; i < Commands.list.size(); ++i) {
            var command = Commands.list.get(i);

            if(i == Commands.list.size() - 1) {
                message = message.append(Component.text(command.key).color(TextColor.color(0xFFFFFF)));
            } else {
                message = message.append(Component.text(command.key + ", ").color(TextColor.color(0xFFFFFF)));
            }
        }

        sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
            message
        ));
    }
}
