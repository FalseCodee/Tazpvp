package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIShop extends GUI {

    public GUIShop(Player player) {
        super(player, 45, "SHOP");
        player.openInventory(inventory);
    }

    public void addShopItem(int slot, ItemStack item, int cost, boolean points, String name, String lore) {
        setButtons(slot, event -> {
            event.setCancelled(true);
            if(points) {
                if(TazPvP.statsManager.getPoints(player) >= cost) {
                    TazPvP.statsManager.addPoints(player, -cost);
                    player.getInventory().addItem(item);
                } else {
                    player.sendMessage(ChatColor.RED + "Insufficient points");
                }
            } else {
                if(TazPvP.statsManager.getMoney(player) >= cost) {
                    TazPvP.statsManager.addMoney(player, -cost);
                    player.getInventory().addItem(item);
                } else {
                    player.sendMessage(ChatColor.RED + "Insufficient funds");
                }
            }
        });
    }

    ItemStack flower = new ItemStack(Material.RED_ROSE, 1, (short) 1);

    public void setItems() {
        addShopItem(11, new ItemStack(Material.GOLD_ORE, 1), 50, false, ChatColor.BLUE + "Butter", ChatColor.GRAY + "Health Boost\n" + ChatColor.WHITE + "$50");
        addShopItem(12, new ItemStack(Material.EYE_OF_ENDER, 1), 50, false, ChatColor.BLUE + "Agility", ChatColor.GRAY + "Speed Boost\n" + ChatColor.WHITE + "$50");
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
