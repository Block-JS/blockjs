package io.github.blockjs.plugin.js.runtime

import com.caoccao.javet.interop.V8Host
import com.caoccao.javet.interop.V8Runtime
import com.caoccao.javet.interop.engine.IJavetEngine
import com.caoccao.javet.values.reference.V8ValuePromise
import java.io.File

class JSMinecraftRuntimeWrapper(
    var engine: V8Host,
    var runtime: V8Runtime,
    var heartBeat: V8ValuePromise? = null,
    /** Currently run script by the runtime. */
    var currentlyRunScript: File? = null,

    /** Next interval id to be assigned. */
    var nextIntervalId: IntervalId = 0,
    /** Next timeout id to be assigned. */
    var nextTimeoutId: TimeoutId = 0,

    val intervals: MutableMap<IntervalId, Interval> = mutableMapOf(),
    val timeouts: MutableMap<IntervalId, Timeout> = mutableMapOf(),
)