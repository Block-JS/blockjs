package com.violeth.blockjs.blockjs;

import java.util.Arrays;
import java.util.List;

import com.violeth.blockjs.blockjs.commands.Command;

import com.violeth.blockjs.blockjs.commands.VersionCommand;
import com.violeth.blockjs.blockjs.commands.TestCommand;

public class Commands {
    static public List<Command> list = Arrays.asList(
        new VersionCommand(),
        new TestCommand()
    );
}
