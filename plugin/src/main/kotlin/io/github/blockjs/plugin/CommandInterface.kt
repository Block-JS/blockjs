package io.github.blockjs.plugin

import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CommandInterface: CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): Boolean {
        if (args.isNullOrEmpty()) {
            sender.sendMessage(
                Main.pluginMessagePrefix!!.append(
                    Component.text("Type `/blockjs help` for more info")
                )
            )
        } else {
            for (i in 0..< Commands.list.size) {
                val commandInst = Commands.list[i]

                if (args[0].equals(commandInst.key, ignoreCase = true)) {
                    commandInst.handle(sender, args)
                    return true
                }
            }

            sender.sendMessage(
                Main.pluginMessagePrefix!!.append(
                    Component.text("Unknown command. Type `/blockjs help` for more info")
                )
            )
        }

        return true
    }
}
