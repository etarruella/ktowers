package com.etarruella.core;

import com.etarruella.config.ConfigManager;
import com.etarruella.core.states.GameState;
import org.bukkit.plugin.Plugin;

public class GameManager {

    private static GameManager gameManager;

    private Plugin plugin;
    private ConfigManager configManager;
    private GameState gameState;

    public GameManager(Plugin plugin, ConfigManager configManager) {
        gameManager = this;

        this.plugin = plugin;
        this.configManager = configManager;

        gameState = GameState.WAITING;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public void startGame() {
        // Nothing
    }

    public void stopGame() {

    }

    public GameState getGameState() {
        return gameState;
    }

}
