package com.etarruella.listeners;

import com.etarruella.core.GameManager;
import com.etarruella.core.PlayerManager;
import com.etarruella.core.states.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class OnPlayerLeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        GameState gameState = GameManager.getGameManager().getGameState();

        if (Objects.requireNonNull(gameState) == GameState.WAITING) {
            PlayerManager.deletePlayer(e.getPlayer());
        }
    }

}
