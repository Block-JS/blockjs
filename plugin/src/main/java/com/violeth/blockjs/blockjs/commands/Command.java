package com.violeth.blockjs.blockjs.commands;

import org.bukkit.command.CommandSender;

abstract public class Command {
    public String key;

    public Command(String key) {
        this.key = key;
    }

    abstract public void handle(CommandSender sender, String[] args);
}
