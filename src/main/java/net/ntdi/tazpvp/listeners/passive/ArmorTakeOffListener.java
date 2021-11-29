package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ArmorTakeOffListener implements Listener {

    @EventHandler
    public void onArmorTakeOff(InventoryClickEvent event) {
        if (!event.getWhoClicked().hasPermission("staff.armor")) {
            if (event.getCurrentItem().getType() == Material.LEATHER_BOOTS || event.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE || event.getCurrentItem().getType() == Material.LEATHER_HELMET || event.getCurrentItem().getType() == Material.LEATHER_LEGGINGS
                    || event.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS || event.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE || event.getCurrentItem().getType() == Material.CHAINMAIL_HELMET || event.getCurrentItem().getType() == Material.CHAINMAIL_LEGGINGS
                    || event.getCurrentItem().getType() == Material.IRON_BOOTS || event.getCurrentItem().getType() == Material.IRON_CHESTPLATE || event.getCurrentItem().getType() == Material.IRON_HELMET || event.getCurrentItem().getType() == Material.IRON_LEGGINGS
                    || event.getCurrentItem().getType() == Material.DIAMOND_BOOTS || event.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE || event.getCurrentItem().getType() == Material.DIAMOND_HELMET || event.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS
                    || event.getCurrentItem().getType() == Material.GOLD_BOOTS || event.getCurrentItem().getType() == Material.GOLD_CHESTPLATE || event.getCurrentItem().getType() == Material.GOLD_HELMET || event.getCurrentItem().getType() == Material.GOLD_LEGGINGS) {
                event.setCancelled(true);
            } else {
                return;
            }
        }
    }
}
