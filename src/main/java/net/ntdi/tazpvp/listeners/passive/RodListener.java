package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class RodListener implements Listener {

    @EventHandler
    public void onPlayerFish(org.bukkit.event.player.PlayerFishEvent event) {
        if (!event.getPlayer().hasPermission("op")) {
            if (TazPvP.punishmentManager.isBanned(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRodShoot(PlayerFishEvent event) {
        if (!event.getPlayer().hasPermission("op") && event.getPlayer().hasPermission("is.banned")) {
            event.setCancelled(true);
        }
    }
}
