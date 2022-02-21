package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class RodListener implements Listener {
    @EventHandler
    public void onRodShoot(PlayerFishEvent event) {
        if (!event.getPlayer().hasPermission("op") && event.getPlayer().hasPermission("is.banned")) {
            Player p = event.getPlayer();
            ItemStack item = p.getItemInHand();
            p.getInventory().remove(item);
        }
    }
}
