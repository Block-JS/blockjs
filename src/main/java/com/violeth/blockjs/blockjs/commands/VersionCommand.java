package com.violeth.blockjs.blockjs.commands;

import com.violeth.blockjs.blockjs.BlockJS;
import com.violeth.blockjs.blockjs.jsinterface.JSRunner;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;

public class VersionCommand extends Command {
    public VersionCommand() {
        super("version");
    }
    @Override
    public void handle(CommandSender sender, String[] args) {
        sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
            Component.text("The current version: ").append(
                Component.text(BlockJS.instance.getDescription().getVersion()).color(TextColor.color(0xDDDDDD)).decorate(TextDecoration.ITALIC)
            )
        ));
    }
}
