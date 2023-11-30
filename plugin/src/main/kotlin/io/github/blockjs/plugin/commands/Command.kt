package io.github.blockjs.plugin.commands

import org.bukkit.command.CommandSender

abstract class Command(var key: String) {
    abstract fun handle(sender: CommandSender, args: Array<out String>)
}
