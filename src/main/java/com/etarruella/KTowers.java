package com.etarruella;

import com.etarruella.config.ConfigManager;
import com.etarruella.config.MapConfig;
import com.etarruella.config.MapMetadata;
import com.etarruella.config.options.OptionManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class KTowers extends JavaPlugin implements Listener {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() { // Main entry point
        plugin = this;

        ConfigManager cm = new ConfigManager(plugin);

        plugin.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // Location generalLobby = MapMetadata.LOBBY_TEAM_B.getValue();
        // e.getPlayer().teleport(generalLobby);
    }

}