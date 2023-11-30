package io.github.blockjs.plugin.commands

import io.github.blockjs.plugin.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.CommandSender

class VersionCommand : Command("version") {
    override fun handle(sender: CommandSender, args: Array<out String>) {
        sender.sendMessage(
            Main.pluginMessagePrefix!!.append(
                Component.text("The current version: ").append(
                    Component.text(Main.instance!!.description.version).color(TextColor.color(0xDDDDDD))
                        .decorate(TextDecoration.ITALIC)
                )
            )
        )
    }
}
