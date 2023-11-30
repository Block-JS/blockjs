package io.github.blockjs.plugin

import com.caoccao.javet.exceptions.JavetException
import com.caoccao.javet.interop.V8Host
import io.github.blockjs.plugin.binds.Binds
import io.github.blockjs.plugin.js.runtime.JSMinecraftRuntimeManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main: JavaPlugin() {
    companion object {
        var instance: Main? = null
        var scriptsFolder: File? = null
        var pluginMessagePrefix: Component? = null
    }

    var binds: Binds = Binds()

    private fun makeSureScriptsFolderExists() {
        if (!dataFolder.exists()) {
            dataFolder.mkdir()
        }

        val fsEntry = File(dataFolder, "scripts")

        if (!fsEntry.exists()) {
            val success = fsEntry.mkdir()

            if (!success) {
                componentLogger.error(
                    pluginMessagePrefix!!.append(
                        Component.text("Failed to create scripts folder inside plugins directory! Make sure the folder permissions are set correctly!")
                    )
                )

                Bukkit.getPluginManager().disablePlugin(this)
            }
        }
    }

    override fun onEnable() {
        // Plugin startup logic
        instance = this
        scriptsFolder = File(instance!!.dataFolder, "scripts")
        pluginMessagePrefix = Component.text("[")
            .append(Component.text(instance!!.name).color(TextColor.color(0x00BBFF)))
            .append(Component.text("] "))

        this.saveDefaultConfig()

        /** TODO: Fix config values when set incorrectly */
//        this.config.getInt("maxRunningScriptContainers")
//            .coerceAtLeast(1)
//            .coerceAtMost(JavetEngineConfig.MAX_POOL_SIZE)

        makeSureScriptsFolderExists()

        JSMinecraftRuntimeManager.initialize()

        this.getCommand("blockjs")?.setExecutor(CommandInterface())

        //FIXME Update event handlers to use Javet
        server.pluginManager.registerEvents(object : Listener {
            @EventHandler
            fun onBlockDamage(event: BlockDamageEvent) {
                val block = event.block

                val blockX = block.x
                val blockY = block.y
                val blockZ = block.z

                for (blockBinds in binds.onBlockDamage) {
                    if (blockBinds.x == blockX && blockBinds.y == blockY && blockBinds.z == blockZ) {
                        val blockBindsEntrySet = blockBinds.map.entries
                        val blockBindIterator  = blockBindsEntrySet.iterator()

                        while (blockBindIterator.hasNext()) {
                            val blockBind = blockBindIterator.next()

                            try {
                                blockBind.value.callback.use { callback ->
                                    val callbackRuntime = callback.v8Runtime

                                    callback.callVoid(
                                        callbackRuntime.createV8ValueObject(),
                                        callbackRuntime.createV8ValueObject().set("player", event.player)
                                    )
                                }
                            } catch (e: JavetException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }

            @EventHandler
            fun onBlockBreak(event: BlockBreakEvent) {
                val block = event.block
                val blockX = block.x
                val blockY = block.y
                val blockZ = block.z

                for (blockBinds in binds.onBlockBreak) {
                    if (blockBinds.x == blockX && blockBinds.y == blockY && blockBinds.z == blockZ) {
                        val blockBindsEntrySet = blockBinds.map.entries
                        val blockBindIterator = blockBindsEntrySet.iterator()

                        while (blockBindIterator.hasNext()) {
                            val blockBind = blockBindIterator.next()
                            try {
                                blockBind.value.callback.use { callback ->
                                    val callbackRuntime = callback.v8Runtime

                                    callback.callVoid(
                                        callbackRuntime.createV8ValueObject(),
                                        callbackRuntime.createV8ValueObject().set("player", event.player)
                                    )
                                }
                            } catch (e: JavetException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }, this)

        /** Main runtime schedule task */
        server.scheduler.scheduleSyncRepeatingTask(this, {
            val runtimeIt = JSMinecraftRuntimeManager.runtimes.iterator()

            while(runtimeIt.hasNext()) {
                val runtimeWrapper = runtimeIt.next()

//                if(!runtimeWrapper.runtime.isClosed) {
//
//                }

                if(runtimeWrapper.runtime.isClosed) {
                    /** Give a hint to the runtime about GC; V8Runtime.lowMemoryNotification() might not be required */
                    runtimeWrapper.runtime.await()
                } else {
                    runtimeWrapper.heartBeat!!.resolve(runtimeWrapper.runtime.createV8ValueNull())

                    if (runtimeWrapper.intervals.isEmpty() && runtimeWrapper.timeouts.isEmpty()) {
                        /** Note: Manual de-referencing might be needed as well when assigning objects. */
//                        runtimeWrapper.runtime.lowMemoryNotification()

                        runtimeWrapper.runtime.close()
//                        runtimeWrapper.runtime.resetContext()
//                        runtimeWrapper.engine.close()

                        /** Might be not required */
//                        JSMinecraftRuntimeManager.pool!!.releaseEngine(runtimeWrapper.engine)

                        runtimeIt.remove()
                    }

                    val currentTime = System.currentTimeMillis()

                    if(runtimeWrapper.intervals.isNotEmpty()) {
                        val intervalIt = runtimeWrapper.intervals.iterator()

                        while(intervalIt.hasNext()) {
                            val interval = intervalIt.next().value

                            val timeDifference = currentTime - interval.lastTimeExecuted

                            if(timeDifference > interval.time) {
                                interval.function.callVoid(
                                    runtimeWrapper.runtime.createV8ValueObject(),
                                    Unit
                                )

                                interval.lastTimeExecuted = currentTime
                            }
                        }
                    }

                    if(runtimeWrapper.timeouts.isNotEmpty()) {
                        val timeoutIt = runtimeWrapper.timeouts.iterator()

                        while(timeoutIt.hasNext()) {
                            val timeout = timeoutIt.next().value

                            if(currentTime > timeout.executionTime) {
                                timeout.function.callVoid(
                                    runtimeWrapper.runtime.createV8ValueObject(),
                                    Unit
                                )

                                /** Make sure it's going to be cleaned */
                                timeout.function.clearWeak()
//                                timeout.function.close()

                                timeoutIt.remove()
                            }
                        }
                    }
                }
            }
        }, 0L, this.config.getLong("tickDelay"))
    }

    override fun onDisable() {
        // Plugin shutdown logic
//        JSMinecraftRuntimeManager.pool!!.close()

        V8Host.getV8Instance().unloadLibrary()
    }
}
