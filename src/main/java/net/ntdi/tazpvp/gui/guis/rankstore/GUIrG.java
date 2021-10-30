package net.ntdi.tazpvp.gui.guis.rankstore;

import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.gui.guis.GUIBuyRank;
import net.ntdi.tazpvp.gui.guis.GUICosmetics;
import net.ntdi.tazpvp.gui.guis.GUIGiftRank;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIrG extends GUI {

        public GUIrG(Player player) {
            super(player, 27, ChatColor.translateAlternateColorCodes('&', "&9&lSTORE &r&c25% OFF SALE"));
            init();
            player.openInventory(inventory);
        }
        @SuppressWarnings("deprecation")
        public void init() {
            ItemStack rankItem = createItem(Material.ENDER_PEARL, ChatColor.BLUE + "" + ChatColor.BOLD + "RANK", ChatColor.GRAY + "Purchase a rank for yourself.", true);
            ItemStack creditsItem = createItem(Material.EYE_OF_ENDER, ChatColor.BLUE + "" + ChatColor.BOLD + "GIFT", ChatColor.GRAY + "Gift a player a rank.", true);
            for(int i = 0; i < inventory.getSize(); i++) {
                items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), "");
            }
            setButtons(11, rankItem, event -> switchScreen(new GUIBuyRank(player)));
            setButtons(14, creditsItem, event -> switchScreen(new GUIGiftRank(player)));

        }

        @Override
        public void onInventoryClick(InventoryClickEvent e, GUI gui) {
            if(gui != this) return;
            e.setCancelled(true);
        }
    }
