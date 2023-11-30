package io.github.blockjs.plugin.commands

import io.github.blockjs.plugin.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File
import java.util.logging.Level

/** TODO: Deprecate event binding command and use run command on script with World.add[...]Listener instead?  */
class BindCommand : Command("bind") {
    override fun handle(sender: CommandSender, args: Array<out String>) {
        if (sender is Player) {
            if (args.size == 3) {
                val rawScriptPath = args[2]
                val scriptPath = File(Main.scriptsFolder, rawScriptPath)
                if (!scriptPath.isFile()) {
                    sender.sendMessage(
                        Main.pluginMessagePrefix!!.append(
                            Component.text().append(
                                Component.text("There's no "),
                                Component.text(rawScriptPath).color(TextColor.color(0xFFFF22)),
                                Component.text(" script!")
                            ).color(TextColor.color(0xFF5555))
                        )
                    )
                    return
                }

                val player = sender
                val bindType = args[1]

                when (bindType) {
                    "block" -> {
                        val targetBlock = player.getTargetBlock(null, 5)
                        if (targetBlock.type === Material.AIR) {
                            sender.sendMessage(
                                Main.pluginMessagePrefix!!.append(
                                    Component.text("You are not currently looking at any block!")
                                        .color(TextColor.color(0xFFFF55))
                                )
                            )
                            return
                        }
                        val targetBlockX = targetBlock.x
                        val targetBlockY = targetBlock.y
                        val targetBlockZ = targetBlock.z

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
                        sender.sendMessage(
                            Main.pluginMessagePrefix!!.append(
                                Component.text("Script bound to block successfully!")
                            )
                        )
                    }

                    else -> {
                        sender.sendMessage(
                            Main.pluginMessagePrefix!!.append(
                                Component.text("Unknown bind type, existing are: ")
                                    .append(Component.text("block").color(TextColor.color(0x55FFFF)))
                            )
                        )
                    }
                }
            } else {
                sender.sendMessage(
                    Main.pluginMessagePrefix!!.append(
                        Component.text("Incorrect usage! (should be: /blockjs bind <binding type> <script path>)")
                    )
                )
            }
        } else {
            Main.instance!!.logger.log(Level.INFO, "You have to be a player to use this command!")
        }
    }
}
