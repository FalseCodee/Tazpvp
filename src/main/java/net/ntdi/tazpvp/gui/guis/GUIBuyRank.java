package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.listeners.function.RankBuying;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIBuyRank extends GUI {
    public GUIBuyRank(Player player) {
        super(player, 27, "BUY RANK");
        setItems();
        player.openInventory(inventory);
    }

    Player p = player;

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (TazPvP.statsManager.getCredits(p) >= 250) {
                        new RankBuying().buyPro(p);
                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
                case 2:
                    if (TazPvP.statsManager.getCredits(p) >= 500) {
                        new RankBuying().buyChampion(p);
                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
                case 3:
                    if (TazPvP.statsManager.getCredits(p) >= 1000) {
                        new RankBuying().buyLegend(p);
                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
            }
        });
    }

    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addShopItem(11, new ItemStack(Material.EMERALD_ORE, 1), 1, ChatColor.DARK_AQUA + "BUY VIP RANK", ChatColor.GRAY + "Buy the VIP rank!\n" + ChatColor.AQUA + "250 Credits");
        addShopItem(13, new ItemStack(Material.DIAMOND_BLOCK, 1), 2, ChatColor.DARK_AQUA + "BUY MVP RANK", ChatColor.GRAY + "Buy the MVP rank!\n" + ChatColor.AQUA + "500 Credits");
        addShopItem(15, new ItemStack(Material.LAPIS_BLOCK, 1), 3, ChatColor.DARK_AQUA + "BUY MVP+ RANK", ChatColor.GRAY + "Buy the MVP+ rank!\n" + ChatColor.AQUA + "1000 Credits");        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
