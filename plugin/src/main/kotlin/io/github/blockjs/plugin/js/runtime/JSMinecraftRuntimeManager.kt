package io.github.blockjs.plugin.js.runtime

import com.caoccao.javet.annotations.V8Function
import com.caoccao.javet.enums.JSRuntimeType
import com.caoccao.javet.exceptions.JavetException
import com.caoccao.javet.interop.V8Host
import com.caoccao.javet.interop.V8Runtime
import com.caoccao.javet.interop.callback.JavetCallbackContext
import com.caoccao.javet.interop.converters.JavetProxyConverter
import com.caoccao.javet.interop.engine.JavetEngineConfig
import com.caoccao.javet.interop.engine.JavetEnginePool
import com.caoccao.javet.values.reference.V8ValueFunction
import io.github.blockjs.plugin.Main
import io.github.blockjs.plugin.js.runtime.api.game.Chat
import io.github.blockjs.plugin.js.runtime.api.game.Player
import io.github.blockjs.plugin.js.runtime.api.game.Server
import io.github.blockjs.plugin.js.runtime.api.game.World
import io.github.blockjs.plugin.js.runtime.api.node.Console
import io.github.blockjs.plugin.utils.FilenameUtils
import java.io.File

enum class GetFreeRuntimeFailedResultStatus {
    NO_AVAILABLE_RUNTIMES
}

open class TryToGetRuntimeResult

class TryToGetRuntimeSuccessResult(
    val runtimeWrapper: JSMinecraftRuntimeWrapper
): TryToGetRuntimeResult()

class TryToGetRuntimeFailedResult(
    val status: GetFreeRuntimeFailedResultStatus
): TryToGetRuntimeResult()

object JSMinecraftRuntimeManager {
    var pool: JavetEnginePool<V8Runtime>? = null
    var runtimes = mutableListOf<JSMinecraftRuntimeWrapper>()
    val proxyConverter = JavetProxyConverter()
    fun initialize() {
        pool = JavetEnginePool<V8Runtime>(
            JavetEngineConfig()
                .setJSRuntimeType(JSRuntimeType.V8)
                .setPoolMaxSize(Main.instance!!.config.getInt("maxRunningScriptContainers")
                    /** TODO: Fix it when plugin loads */
                    .coerceAtLeast(1)
                    .coerceAtMost(JavetEngineConfig.MAX_POOL_SIZE))
        )
    }
    /** Might not make any difference when used */
//    fun initializeStartupRuntimes() {
//        val maxRunningScripts = Main.instance!!.config.getInt("maxRunningScriptContainers")
//        var initiallyPreparedScriptContainers = Main.instance!!.config.getInt("initiallyPreparedScriptContainers")
//
//        if(initiallyPreparedScriptContainers == -1) {
//            initiallyPreparedScriptContainers = maxRunningScripts
//        } else {
//            initiallyPreparedScriptContainers = initiallyPreparedScriptContainers.coerceAtMost(maxRunningScripts)
//        }
//
//        for(i in 0 .. initiallyPreparedScriptContainers) {
//            val runtime = createRuntime()
//            val runtimeWrapper = JSMinecraftRuntimeWrapper(runtime)
//
//            runtimes.add(runtimeWrapper)
//        }
//    }
    fun tryToGetRuntime(): TryToGetRuntimeResult {
        /** Runtimes are anyway terminated on exit now, no need to re-use them */
//        for(runtime in runtimes) {
//            if(runtime.currentlyRunScript == null) {
//                return TryToGetRuntimeSuccessResult(runtime)
//            }
//        }

        if(runtimes.size < Main.instance!!.config.getInt("maxRunningScriptContainers")) {
            return TryToGetRuntimeSuccessResult(createRuntime())
        }

        return TryToGetRuntimeFailedResult(GetFreeRuntimeFailedResultStatus.NO_AVAILABLE_RUNTIMES)
    }
    @kotlin.Throws(RuntimeException::class)
    fun createRuntime(): JSMinecraftRuntimeWrapper {
        try {
            try {
                val engine = V8Host.getV8Instance()
                val runtime = engine.createV8Runtime<V8Runtime>()
                val runtimeWrapper = JSMinecraftRuntimeWrapper(engine, runtime)

                runtime.setConverter(proxyConverter)

                runtime.globalObject.set("console", Console)

                val global = object {
                    @V8Function fun setTimeout(func: V8ValueFunction, time: Long): TimeoutId {
                        val timeoutId = runtimeWrapper.nextTimeoutId++

                        /** Make sure it doesn't get cleaned by GC */
                        func.setWeak()

                        runtimeWrapper.timeouts[timeoutId] = Timeout(
                            timeoutId,
                            func,
                            System.currentTimeMillis() + time
                        )

                        return timeoutId
                    }
                    @V8Function fun clearTimeout(timeoutId: TimeoutId) {
                        val removedTimeout = runtimeWrapper.timeouts.remove(timeoutId)

                        removedTimeout?.function?.clearWeak()
                    }
                    @V8Function fun setInterval(func: V8ValueFunction, time: Long): IntervalId {
                        val intervalId = runtimeWrapper.nextIntervalId++

                        /** Make sure it doesn't get cleaned by GC */
                        func.setWeak()

                        runtimeWrapper.intervals[intervalId] = Interval(intervalId, func, time)

                        return intervalId
                    }
                    @V8Function fun clearInterval(intervalId: IntervalId) {
                        val removedInterval = runtimeWrapper.intervals.remove(intervalId)

                        removedInterval?.function?.clearWeak()
                    }
                }

                runtime.globalObject.bindFunction(
                    JavetCallbackContext(
                        "setTimeout",
                        global,
                        global.javaClass.getDeclaredMethod("setTimeout", V8ValueFunction::class.java, Long::class.java)
                    )
                )

                runtime.globalObject.bindFunction(
                    JavetCallbackContext(
                        "clearTimeout",
                        global,
                        global.javaClass.getDeclaredMethod("clearTimeout", TimeoutId::class.java)
                    )
                )

                runtime.globalObject.bindFunction(
                    JavetCallbackContext(
                        "setInterval",
                        global,
                        global.javaClass.getDeclaredMethod("setInterval", V8ValueFunction::class.java, Long::class.java)
                    )
                )

                runtime.globalObject.bindFunction(
                    JavetCallbackContext(
                        "clearInterval",
                        global,
                        global.javaClass.getDeclaredMethod("clearInterval", IntervalId::class.java)
                    )
                )

                runtime.globalObject.set("Player", Player)
                runtime.globalObject.set("World", World)
                runtime.globalObject.set("Chat", Chat)
                runtime.globalObject.set("Server", Server)

                runtime.setV8ModuleResolver { moduleRuntime, rawRequestedModulePath, moduleImporter ->
                    val rawRequestedModuleFile = File(rawRequestedModulePath)
                    val rawRequestedModuleFileNameParts = FilenameUtils.getParts(rawRequestedModuleFile.name)

                    for (fsEntry in Main.scriptsFolder!!.listFiles()!!) {
                        if(fsEntry.isFile()) {
                            val entryFileNameParts = FilenameUtils.getParts(fsEntry.name)

                            if (entryFileNameParts[entryFileNameParts.size - 1] == "js") {
                                if (entryFileNameParts[0] == rawRequestedModuleFileNameParts[0]) {
                                    return@setV8ModuleResolver moduleRuntime.getExecutor(fsEntry)
                                        .compileV8Module()
                                }
                            }
                        } else if(fsEntry.isDirectory) {
                            if(fsEntry.name == "node_modules") {
                                /** TODO: Resolve dependencies */
                            } else {
                                /** TODO */
                            }
                        }
                    }

                    return@setV8ModuleResolver null
                }

                /** Note: keeps runtime alive */
                runtimeWrapper.heartBeat = runtimeWrapper.runtime.createV8ValuePromise()

                runtimes.add(runtimeWrapper)

                return runtimeWrapper
            } catch (e: JavetException) {
                throw RuntimeException(e)
            }

        } catch (e: JavetException) {
            throw RuntimeException(e)
        }
    }
}
