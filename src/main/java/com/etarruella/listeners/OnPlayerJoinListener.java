package com.etarruella.listeners;

import com.etarruella.core.GameManager;
import com.etarruella.core.PlayerManager;
import com.etarruella.core.entities.KPlayer;
import com.etarruella.core.states.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class OnPlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        GameState gameState = GameManager.getGameManager().getGameState();

        switch (gameState) {
            case STARTING, PLAYING, DEATHMATCH, ENDED:
                if (e.getPlayer().isOp() || e.getPlayer().hasPermission("ktowers.operator") ||
                        e.getPlayer().hasPermission("ktowers.manager")) {
                    return;
                }

                if (!PlayerManager.isPlayerLogged(e.getPlayer())) {
                    e.getPlayer().kickPlayer("The game has started!");
                }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        KPlayer kPlayer;
        GameState gameState = GameManager.getGameManager().getGameState();

        switch (gameState) {
            case WAITING:
                kPlayer = PlayerManager.getKPlayer(e.getPlayer());
                PlayerManager.preparePlayer(kPlayer);
        }
    }

}
