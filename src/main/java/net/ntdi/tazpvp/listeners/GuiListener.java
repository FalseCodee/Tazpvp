package net.ntdi.tazpvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GuiListener implements Listener {

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        InventoryView inventoryName = event.getView();
//        if (inventoryName.getTitle().equals("Egg Vault")) {
//            if (clicked.getType() == Material.BLACK_STAINED_GLASS_PANE) {
//                event.setCancelled(true);
//            }
//            if (clicked.getType() == Material.LIME_STAINED_GLASS_PANE) {
//                event.setCancelled(true);
//            }
//            if (clicked.getType() == Material.GREEN_STAINED_GLASS_PANE) {
//                event.setCancelled(true);
//            }
//            if (clicked.getType() == Material.EGG) {
//                event.setCancelled(true);
//                ItemStack eggg = new ItemStack(Material.EGG, 16);
//                ItemMeta meta = eggg.getItemMeta();
//                meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Explosive Egg");
//                meta.setUnbreakable(true);
//                ArrayList<String> lore = new ArrayList<>();
//                lore.add(ChatColor.RED + "Explosive Egg");
//                lore.add(ChatColor.RED + "Rightclick to throw");
//                meta.setLore(lore);
//                eggg.setItemMeta(meta);
//
//                player.getInventory().addItem(eggg);
//
//            }
//        }
    }
}
