package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (!event.getWhoClicked().hasPermission("is.op")) {
            if (event.getWhoClicked() instanceof Player) {
                event.getWhoClicked().sendMessage(ChatColor.RED + "You are not allowed to craft items!");
            }
            event.setCancelled(true);
        }
    }
}
