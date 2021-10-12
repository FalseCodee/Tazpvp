package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.listeners.function.RenameSword;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import net.ntdi.tazpvp.listeners.*;
import org.bukkit.inventory.meta.ItemMeta;

public class GUICosmetics extends GUI {
    public GUICosmetics(Player player) {
        super(player, 27, "COSMETICS");
        setItems();
        player.openInventory(inventory);
    }

    Player p = player;

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (TazPvP.statsManager.getCredits(p) >= 50) {

                        TazPvP.renamingSword.add(p);
                        p.sendMessage("type in chat what u wantz to rename ur sword:");

                    } else {
                        p.sendMessage(ChatColor.RED + "Insufficient Credits!");
                    }
                    break;
                case 2:
                    p.sendMessage(ChatColor.YELLOW + "Opening gui that definitely exists");
                    break;
                case 3:
                    p.sendMessage(ChatColor.YELLOW + "blocks arent real");
                    break;
                case 4:
                    if (TazPvP.statsManager.getCredits(p) >= 100) {
                        p.sendMessage(ChatColor.YELLOW + "prefix's dont deserve to be renamed");
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

        addShopItem(10, new ItemStack(Material.WOOD_SWORD, 1), 1, ChatColor.DARK_AQUA + "RENAME SWORD", ChatColor.GRAY + "Rename your sword!\n" + ChatColor.AQUA + "50 Credits");
        addShopItem(12, new ItemStack(Material.LEATHER_CHESTPLATE, 1), 2, ChatColor.DARK_AQUA + "RECOLOR ARMOR", ChatColor.GRAY + "Recolor your armor!");
        addShopItem(14, new ItemStack(Material.WOOL, 1), 3, ChatColor.DARK_AQUA + "RGB BLOCK SHOP", ChatColor.GRAY + "Rgb blocks!");
        addShopItem(16, new ItemStack(Material.NAME_TAG, 1), 4, ChatColor.DARK_AQUA + "RENAME PREFIX", ChatColor.GRAY + "Rename your prefix!\n" + ChatColor.AQUA + "100 Credits");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
