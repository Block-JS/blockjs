package com.violeth.blockjs.blockjs;

import com.violeth.blockjs.blockjs.jsinterface.JSRunner;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.NotNull;

public class CommandInterface implements CommandExecutor {
    @Override
    public boolean onCommand(
        org.bukkit.command.@NotNull CommandSender sender,
        org.bukkit.command.@NotNull Command command,
        @NotNull String label, String[] args
    ) {
        if(args.length > 0) {
            for(var i = 0; i < Commands.list.size(); i++) {
                var commandInst = Commands.list.get(i);
                if(args[0].equalsIgnoreCase(commandInst.key)) {
                    commandInst.handle(sender, args);

                    return true;
                }
            }

            sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                    Component.text("Unknown command. Type `/blockjs help` for more info")
            ));
        } else {
            sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                Component.text("Type `/blockjs help` for more info")
            ));
        }

        return true;
    }
}
