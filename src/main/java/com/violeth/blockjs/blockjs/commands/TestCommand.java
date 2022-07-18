package com.violeth.blockjs.blockjs.commands;

import com.caoccao.javet.exceptions.JavetException;
import com.violeth.blockjs.blockjs.BlockJS;
import com.violeth.blockjs.blockjs.jsinterface.JSRunner;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.nio.file.Paths;

public class TestCommand extends Command {
    {
        key = "test";
    }
    @Override
    public void handle(CommandSender sender, String[] args) {
        sender.sendMessage("test");

        var jsThread = new Thread() {{
            var runner = new JSRunner(new File(BlockJS.instance.getDataFolder(), "scripts").getAbsoluteFile());

            runner.getAndRunJS();
        }};
    }
}
