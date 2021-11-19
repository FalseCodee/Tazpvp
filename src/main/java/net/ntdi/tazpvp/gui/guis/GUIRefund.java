package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIRefund extends GUI {

    public GUIRefund(Player player) {
        super(player, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "CREDITS〡" + ChatColor.DARK_AQUA + TazPvP.statsManager.getCredits(player));
        setItems();
        player.openInventory(inventory);
    }

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    player.closeInventory();
                    if (TazPvP.statsManager.getCredits(player) >= 50) {
                        TazPvP.RefundItem.add(player);
                        player.sendMessage(ChatColor.RED + "Please hold your item that you wish to refund in your hand\nOnce you've done that type " + ChatColor.GOLD + " \"GO\" " + ChatColor.RED + " in the chat.\nYou may type anything else to cancel this.");

                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have enough credits to refund this item.");
                    }
                    break;
            }
        });
    }

    public void setItems() {
        for (int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addShopItem(13, new ItemStack(Material.DIAMOND_SWORD), 1, ChatColor.GOLD + "Refund a item", ChatColor.AQUA + "50 Credits\n" + ChatColor.RED + "READ CHAT INSTRUCTIONS AFTER CLICKING");

    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
