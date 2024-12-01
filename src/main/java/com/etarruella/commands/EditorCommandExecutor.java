package com.etarruella.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class EditorCommandExecutor implements CommandExecutor, TabCompleter {

    enum SubCommands {
        SET_LOBBY("set-lobby"),
        SET_TEAM_LOBBY("set-team-lobby"),
        SET_TEAM_SPAWN("set-team-spawn"),
        SET_TEAM_ARENA("set-team-arena"),
        SET_GENERATOR("set-generator"),
        SET_TEAM_BASKET("set-team-basket"),
        SET_MAP_CENTER("set-map-center");

        String name;

        SubCommands(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return List.of();
    }

}
