package io.github.blockjs.plugin.commands

import io.github.blockjs.plugin.Main
import io.github.blockjs.plugin.js.runtime.JSMinecraftRuntimeManager
import net.kyori.adventure.text.Component
import org.bukkit.command.CommandSender

class ListCommand : Command("list") {
    override fun handle(sender: CommandSender, args: Array<out String>) {
        if(args.size > 1) {
            when (args[1]) {
                "running" -> {
                    val currentlyRunningRuntimes = JSMinecraftRuntimeManager.runtimes.size
                    val maxRunningScripts = Main.instance!!.config.getInt("maxRunningScriptContainers")
                    val message = Main.pluginMessagePrefix!!.append(
                        Component.text("Currently running scripts ($currentlyRunningRuntimes/$maxRunningScripts)")
                    )

                    sender.sendMessage(message)
                }
                else -> {
                    sender.sendMessage(Main.pluginMessagePrefix!!.append(
                        Component.text("Incorrect usage! (should be: /blockjs list (running))")
                    ))
                }
            }
        } else {
            sender.sendMessage(Main.pluginMessagePrefix!!.append(
                Component.text("Incorrect usage! (should be: /blockjs list (running))")
            ))
        }
    }
}
