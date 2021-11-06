package net.ntdi.tazpvp.listeners.function;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShootListener implements Listener {

    @EventHandler
    public void onShoot(org.bukkit.event.entity.EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(player.hasPermission("is.banned") && !player.hasPermission("op")) {
                event.setCancelled(true);
            }
        }
    }
}
