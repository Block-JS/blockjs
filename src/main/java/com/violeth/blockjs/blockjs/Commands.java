package com.violeth.blockjs.blockjs;

import java.util.Arrays;
import java.util.List;

import com.violeth.blockjs.blockjs.commands.*;

public class Commands {
    static public List<Command> list = Arrays.asList(
        new HelpCommand(),
        new VersionCommand(),
        new RunCommand(),
        new BindCommand()
    );
}
