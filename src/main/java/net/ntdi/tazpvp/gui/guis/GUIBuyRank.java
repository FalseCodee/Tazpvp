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
                case 4:
                    if (TazPvP.statsManager.getCredits(p) >= 1500) {
                        new RankBuying().buyMythical(p);
                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
            }
        });
    }

    ItemStack flower = new ItemStack(Material.RED_ROSE, 1, (short) 1);

    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addShopItem(10, new ItemStack(Material.DIAMOND_BLOCK, 1), 1, ChatColor.DARK_AQUA + "BUY PRO RANK", ChatColor.GRAY + "Buy yourself the Pro rank!\n" + ChatColor.AQUA + "250 Credits");
        addShopItem(12, new ItemStack(Material.EMERALD_BLOCK, 1), 2, ChatColor.DARK_AQUA + "BUY CHAMPION RANK", ChatColor.GRAY + "Buy yourself the Champion rank!\n" + ChatColor.AQUA + "500 Credits");
        addShopItem(14, new ItemStack(Material.GOLD_BLOCK, 1), 3, ChatColor.DARK_AQUA + "BUY LEGEND RANK", ChatColor.GRAY + "Buy yourself the Legend rank!\n" + ChatColor.AQUA + "1000 Credits");
        addShopItem(16, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.MAGENTA.getData()), 4, ChatColor.DARK_AQUA + "BUY MYTHICAL RANK", ChatColor.GRAY + "Buy yourself the Mythical rank!\n" + ChatColor.AQUA + "1500 Credits");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
