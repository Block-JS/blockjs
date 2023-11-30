package io.github.blockjs.plugin

import io.github.blockjs.plugin.commands.*

object Commands {
    var list = arrayOf(
        HelpCommand(),
        VersionCommand(),
        RunCommand(),
        ListCommand(),
        BindCommand(),
    )
}