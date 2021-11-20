package net.ntdi.tazpvp.gui.guis;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUICosmetics extends GUI {
    public GUICosmetics(Player player) {
        super(player, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "CREDITS〡" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + TazPvP.statsManager.getCredits(player));
        setItems();
        player.openInventory(inventory);
    }

    final Player p = player;

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (TazPvP.statsManager.getCredits(p) >= 10) {

                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p) - 10);
                        TazPvP.renamingSword.add(p);
                        p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "RENAME " + ChatColor.DARK_PURPLE + "Please type your new sword name below.");

                    } else {
                        TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! Click to purchase them!");
                        nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                        p.spigot().sendMessage(nocred);
                    }
                    break;
                case 2:
                    if (p.hasPermission("rank.buy")){
                        switchScreen(new GUIRecolor(p));
                    } else {
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED + "You do not have permission to use this feature!");
                    }
                    break;
                case 3:
                    if (p.hasPermission("rank.buy")){
                        if (TazPvP.statsManager.getCredits(p) >= 100) {
                            p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "PREFIX " + ChatColor.DARK_PURPLE + "Please type your new prefix out below.");
                        } else {
                            TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! Click to purchase them!");
                            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                            p.spigot().sendMessage(nocred);
                        }
                    } else {
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED + "You do not have permission to use this feature!");
                    }
                    break;
                case 4:
                    switchScreen(new GUIRefund(p));
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

        ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta pickaxeMeta = sword.getItemMeta();
        pickaxeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sword.setItemMeta(pickaxeMeta);

        addShopItem(10, sword, 1, ChatColor.DARK_AQUA + "Sword Rename ", ChatColor.GRAY + "Rename your sword\n" + ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "10 Credits\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(12, new ItemStack(Material.LEATHER_CHESTPLATE, 1), 2, ChatColor.DARK_AQUA + "Recolor Armor", ChatColor.GRAY + "Colorize your armor\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(14, new ItemStack(Material.ANVIL, 1), 4, ChatColor.DARK_AQUA + "Refund Item", ChatColor.GRAY + "Refund your enchanted item\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(16, new ItemStack(Material.NAME_TAG, 1), 3, ChatColor.DARK_AQUA + "Custom Prefix ", ChatColor.GRAY + "Customize your prefix\n" + ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "100 Credits\n");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
