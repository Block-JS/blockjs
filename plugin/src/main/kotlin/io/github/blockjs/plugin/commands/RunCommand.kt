package io.github.blockjs.plugin.commands

import com.caoccao.javet.exceptions.JavetException
import com.caoccao.javet.values.reference.V8Module
import io.github.blockjs.plugin.Main
import io.github.blockjs.plugin.js.runtime.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import java.io.File
import java.util.logging.Level

class RunCommand : Command("run") {
    override fun handle(sender: CommandSender, args: Array<out String>) {
        if (args.size > 1) {
            val rawScriptPath = args[1]
            val scriptPath = File(Main.scriptsFolder!!.absolutePath, rawScriptPath)

            /** If file doesn't exist */
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

            try {
                /** Try to get a free runtime. */
                var runtimeResult = JSMinecraftRuntimeManager.tryToGetRuntime()

                if(runtimeResult is TryToGetRuntimeFailedResult) {
                    when(runtimeResult.status) {
                        GetFreeRuntimeFailedResultStatus.NO_AVAILABLE_RUNTIMES -> {
                            sender.sendMessage(
                                Main.pluginMessagePrefix!!.append(
                                    Component.text().append(
                                        Component.text("You can't run more scripts! Script containers limit exceeded!")
                                    ).color(TextColor.color(0xFF5555))
                                )
                            )
                        }
                    }

                    return
                }

                /** Assume it's a success. */
                runtimeResult = runtimeResult as TryToGetRuntimeSuccessResult

                val runtimeWrapper = runtimeResult.runtimeWrapper

                runtimeWrapper.currentlyRunScript = scriptPath

                /** Execute. */
//                val thread = Thread {
                    /** Handle errors, log and send them back every online operator. */
                    runtimeWrapper.runtime.setPromiseRejectCallback { javetPromiseRejectEvent, v8ValuePromise, v8Value ->
                        val errorComponent = Component.text().append(
                            Component.text(v8Value.toString())
                        )
                            .color(TextColor.color(0xFF5555))
                            .asComponent()

                        Main.instance!!.componentLogger.error(errorComponent)

                        for(operator in Bukkit.getOperators()) {
                            if(operator.isOnline) {
                                operator.player!!.sendMessage(
                                    Main.pluginMessagePrefix!!.append(errorComponent)
                                )
                            }
                        }
                    }

                    try {
                        runtimeWrapper.runtime
                            .getExecutor(scriptPath)
                            .setModule(true)
                            .execute<V8Module>()

                        runtimeWrapper.runtime.await()
                    } catch (e: JavetException) {
                        Bukkit.getLogger().log(Level.SEVERE, "Error while running script", e)
                    }
//                }.start()
            } catch (e: JavetException) {
                Bukkit.getLogger().log(Level.SEVERE, "Couldn't create a new runtime", e)
            }
        } else {
            sender.sendMessage(
                Main.pluginMessagePrefix!!.append(
                    Component.text().append(
                        Component.text("You have to provide a file!")
                    ).color(TextColor.color(0xFF5555))
                )
            )
        }
    }
}
