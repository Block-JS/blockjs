package io.github.blockjs.plugin.commands

import io.github.blockjs.plugin.Main
import io.github.blockjs.plugin.Commands
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.command.CommandSender

class HelpCommand : Command("help") {
    override fun handle(sender: CommandSender, args: Array<out String>) {
        var message = Component.text("Available commands: ")

        for (i in 0..< Commands.list.size) {
            val command = Commands.list[i]

            if(i == Commands.list.size - 1) {
                message = message.append(Component.text(command.key).color(TextColor.color(0xFFFFFF)))
            } else {
                message = message.append(Component.text(command.key + ", ").color(TextColor.color(0xFFFFFF)))
            }
        }

        sender.sendMessage(
            Main.pluginMessagePrefix!!.append(
                message
            )
        )
    }
}
