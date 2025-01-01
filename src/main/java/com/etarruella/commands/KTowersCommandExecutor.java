package com.etarruella.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KTowersCommandExecutor implements KTowersCommand {

    enum SubCommands {
        EDIT("edit"),
        SAVE("save");

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
                // List of maps
                break;
        }
        return List.of();
    }

}
