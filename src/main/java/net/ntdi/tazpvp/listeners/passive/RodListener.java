package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RodListener implements Listener {

    @EventHandler
    public void onPlayerFish(org.bukkit.event.player.PlayerFishEvent event) {
        if (TazPvP.punishmentManager.isBanned(event.getPlayer())) {
            event.setCancelled(true);
        }

    }

}
