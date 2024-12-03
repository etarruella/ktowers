package com.etarruella;

import com.etarruella.commands.EditorCommandExecutor;
import com.etarruella.commands.KTowersCommand;
import com.etarruella.commands.KTowersCommandExecutor;
import com.etarruella.config.ConfigManager;
import com.etarruella.core.GameManager;
import com.etarruella.listeners.OnPlayerJoinListener;
import com.etarruella.listeners.OnPlayerLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KTowers extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() { // Main entry point
        plugin = this;

        ConfigManager configManager = new ConfigManager(plugin);

        GameManager gameManager = new GameManager(this, configManager);

        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        List<Listener> listeners = new ArrayList<>();
        listeners.add(new OnPlayerJoinListener());
        listeners.add(new OnPlayerLeaveListener());

        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    private void registerCommands() {
        Map<String, KTowersCommand> commands = new HashMap<>();
        commands.put("editor", new EditorCommandExecutor());
        commands.put("ktowers", new KTowersCommandExecutor());

        for(Map.Entry<String, KTowersCommand> command : commands.entrySet()) {
            getCommand(command.getKey()).setExecutor(command.getValue());
            getCommand(command.getKey()).setTabCompleter(command.getValue());
        }

    }

}