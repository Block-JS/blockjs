package io.github.blockjs.plugin.js.runtime

import com.caoccao.javet.values.reference.V8ValueFunction

typealias TimeoutId = Long

class Timeout(
    var id: TimeoutId,
    var function: V8ValueFunction,
    var executionTime: Long
)