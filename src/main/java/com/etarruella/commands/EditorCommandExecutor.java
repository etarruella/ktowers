package com.etarruella.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        switch (args.length) {
            case 1:
                return Arrays.stream(SubCommands.values())
                            .map(SubCommands::toString)
                            .collect(Collectors.toList());
            case 2:
                if (args[1].contains("team")) {
                    List.of("True", "true");
                }
        }
        return List.of();
    }

}
