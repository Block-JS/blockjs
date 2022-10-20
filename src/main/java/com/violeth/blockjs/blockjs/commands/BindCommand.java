package com.violeth.blockjs.blockjs.commands;

import com.violeth.blockjs.blockjs.BlockJS;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.logging.Level;

/** TODO: Deprecate event binding command and use run command on script with World.add[...]Listener instead? */
public class BindCommand extends Command {
    public BindCommand() {
        super("bind");
    }
    @Override
    public void handle(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            var player = (Player) sender;

            if (args.length == 3) {
                var rawScriptPath = args[2];

                var scriptPath = new File(BlockJS.getScriptsFolder(), rawScriptPath);

                if(!scriptPath.isFile()) {
                    sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                        Component.text().append(
                            Component.text("There's no "),
                            Component.text(rawScriptPath).color(TextColor.color(0xFFFF22)),
                            Component.text(" script!")
                        ).color(TextColor.color(0xFF5555))
                    ));

                    return;
                }

                var bindType = args[1];

                switch (bindType) {
                    case "block": {
                        var targetBlock = player.getTargetBlock(null, 5);

                        if (targetBlock.getType() == Material.AIR) {
                            sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                                Component.text("You are not currently looking at any block!").color(TextColor.color(0xFFFF55))
                            ));

                            return;
                        }

                        var targetBlockX = targetBlock.getX();
                        var targetBlockY = targetBlock.getY();
                        var targetBlockZ = targetBlock.getZ();

//                        for (var blockBinds : BlockJS.instance.binds.blocks) {
//                            if (blockBinds.x == targetBlockX && blockBinds.y == targetBlockY && blockBinds.z == targetBlockZ) {
//                                for(var binds: blockBinds.scripts) {
//                                    if(binds.getAbsolutePath().equals(scriptPath.getAbsolutePath())) {
//                                        sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
//                                            Component.text("This script has been already assigned to this block!").color(TextColor.color(0xFFFF55))
//                                        ));
//
//                                        return;
//                                    }
//                                }
//                            }
//                        }
//
//                        BlockJS.instance.binds.block.add(
//                            new BlockBind(targetBlockX, targetBlockY, targetBlockZ, scriptPath)
//                        );

                        break;
                    }
                    // case "region":
                    // case "selection":
                    default: {
                        sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                            Component.text("Unknown bind type, existing are: ")
                                .append(Component.text("block").color(TextColor.color(0x55FFFF)))
                        ));
                    }
                }
            } else {
                sender.sendMessage(BlockJS.getPluginMessagePrefix().append(
                    Component.text("Incorrect usage! (should be: /blockjs bind <binding type> <script path>)"))
                );
            }
        } else {
            BlockJS.instance.getLogger().log(Level.INFO, "You have to be a player to use this command!");
        }
    }
}
