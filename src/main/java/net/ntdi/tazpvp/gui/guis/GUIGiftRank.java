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

    final Player p = player;

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (TazPvP.statsManager.getCredits(p) >= 250) {
                        if (!TazPvP.ProGiftRank.contains(p) && !TazPvP.ChampionGiftRank.contains(p) && !TazPvP.LegendGiftRank.contains(p) && !TazPvP.MythicalGiftRank.contains(p)){
                            TazPvP.ProGiftRank.add(p);
                            p.sendMessage("type in chat who you want to gift rank to:");
                        } else {
                            p.sendMessage("type in chat who you want to gift the rank to:");
                        }

                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
                case 2:
                    if (TazPvP.statsManager.getCredits(p) >= 500) {
                        if (!TazPvP.ProGiftRank.contains(p) && !TazPvP.ChampionGiftRank.contains(p) && !TazPvP.LegendGiftRank.contains(p) && !TazPvP.MythicalGiftRank.contains(p)){
                            TazPvP.ChampionGiftRank.add(p);
                            p.sendMessage("type in chat who you want to gift rank to:");

                        }

                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
                case 3:
                    if (TazPvP.statsManager.getCredits(p) >= 1000) {
                        if (!TazPvP.ProGiftRank.contains(p) && !TazPvP.ChampionGiftRank.contains(p) && !TazPvP.LegendGiftRank.contains(p) && !TazPvP.MythicalGiftRank.contains(p)){
                            TazPvP.LegendGiftRank.add(p);
                            p.sendMessage("type in chat who you want to gift rank to:");
                        }

                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
            }
        });
    }

    ItemStack flower = new ItemStack(Material.RED_ROSE, 1, (short) 1);
    @SuppressWarnings("deprecation")
    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addShopItem(11, new ItemStack(Material.EMERALD_ORE, 1), 1, ChatColor.DARK_AQUA + "GIFT VIP RANK", ChatColor.GRAY + "Gift someone the VIP rank!\n" + ChatColor.AQUA + "250 Credits");
        addShopItem(13, new ItemStack(Material.DIAMOND_BLOCK, 1), 2, ChatColor.DARK_AQUA + "GIFT MVP RANK", ChatColor.GRAY + "Gift someone the MVP rank!\n" + ChatColor.AQUA + "500 Credits");
        addShopItem(15, new ItemStack(Material.LAPIS_BLOCK, 1), 3, ChatColor.DARK_AQUA + "GIFT MVP+ RANK", ChatColor.GRAY + "Gift someone the MVP+ rank!\n" + ChatColor.AQUA + "1000 Credits");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
