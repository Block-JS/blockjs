package com.violeth.blockjs.blockjs.commands;

import com.violeth.blockjs.blockjs.jsinterface.JSRunner;
import org.bukkit.command.CommandSender;

public class TestCommand extends Command {
    {
        key = "test";
    }
    @Override
    public void handle(CommandSender sender, String[] args) {
        sender.sendMessage("test");

        JSRunner runner = new JSRunner("blockjs/scripts");

        runner.getAndRunJS();
    }
}
