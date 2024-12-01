package com.etarruella;

import com.etarruella.config.ConfigManager;
import com.etarruella.core.GameManager;
import com.etarruella.listeners.OnPlayerJoinListener;
import com.etarruella.listeners.OnPlayerLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

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
    }

    private void registerListeners() {
        List<Listener> listeners = new ArrayList<>();
        listeners.add(new OnPlayerJoinListener());
        listeners.add(new OnPlayerLeaveListener());

        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

}