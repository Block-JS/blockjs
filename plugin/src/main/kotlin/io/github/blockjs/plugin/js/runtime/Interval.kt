package io.github.blockjs.plugin.js.runtime

import com.caoccao.javet.values.reference.V8ValueFunction

typealias IntervalId = Long

class Interval(
    var id: IntervalId,
    var function: V8ValueFunction,
    var time: Long,

    var lastTimeExecuted: Long = System.currentTimeMillis()
)