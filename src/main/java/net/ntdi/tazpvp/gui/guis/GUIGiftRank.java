package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIGiftRank extends GUI {
    public GUIGiftRank(Player player) {
        super(player, 27, "GIFT RANK");
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

                        TazPvP.ProGiftRank.add(p);
                        p.sendMessage("type in chat who you want to gift rank to:");

                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
                case 2:
                    if (TazPvP.statsManager.getCredits(p) >= 500) {

                        TazPvP.ChampionGiftRank.add(p);
                        p.sendMessage("type in chat who you want to gift rank to:");

                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
                case 3:
                    if (TazPvP.statsManager.getCredits(p) >= 1000) {

                        TazPvP.LegendGiftRank.add(p);
                        p.sendMessage("type in chat who you want to gift rank to:");

                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
                case 4:
                    if (TazPvP.statsManager.getCredits(p) >= 1500) {

                        TazPvP.MythicalGiftRank.add(p);
                        p.sendMessage("type in chat who you want to gift rank to:");

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

        addShopItem(10, new ItemStack(Material.DIAMOND_BLOCK, 1), 1, ChatColor.DARK_AQUA + "GIFT PRO RANK", ChatColor.GRAY + "Gift someone the Pro rank!\n" + ChatColor.AQUA + "250 Credits");
        addShopItem(12, new ItemStack(Material.EMERALD_BLOCK, 1), 2, ChatColor.DARK_AQUA + "GIFT CHAMPION RANK", ChatColor.GRAY + "Gift someone the Champion rank!\n" + ChatColor.AQUA + "500 Credits");
        addShopItem(14, new ItemStack(Material.GOLD_BLOCK, 1), 3, ChatColor.DARK_AQUA + "GIFT LEGEND RANK", ChatColor.GRAY + "Gift someone the Legend rank!\n" + ChatColor.AQUA + "1000 Credits");
        addShopItem(16, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.MAGENTA.getData()), 4, ChatColor.DARK_AQUA + "GIFT MYTHICAL RANK", ChatColor.GRAY + "Gift someone the Mythical rank!\n" + ChatColor.AQUA + "1500 Credits");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
