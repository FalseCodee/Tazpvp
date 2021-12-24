package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(Bukkit.getWorld("spawn"), 0.5, 40, 0.5, 180, 0));
    }
}
