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
import org.bukkit.inventory.ItemStack;

public class GUICosmetics extends GUI {
    public GUICosmetics(Player player) {
        super(player, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "CREDITS〡" + TazPvP.statsManager.getCredits(player));
        setItems();
        player.openInventory(inventory);
    }

    final Player p = player;

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (TazPvP.statsManager.getCredits(p) >= 50) {

                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p) - 50);
                        TazPvP.renamingSword.add(p);
                        p.sendMessage("Please type what you would like your sword name to be.");

                    } else {
                        TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! Click to purchase them!");
                        nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                        p.spigot().sendMessage(nocred);
                    }
                    break;
                case 2:
                    switchScreen(new GUIRecolor(p));
                    break;
                case 3:
                    if (TazPvP.statsManager.getCredits(p) >= 100) {
                        p.sendMessage(ChatColor.YELLOW + "Please type what you would like your prefix to be.");
                    } else {
                        TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! Click to purchase them!");
                        nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                        p.spigot().sendMessage(nocred);
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

        addShopItem(10, new ItemStack(Material.WOOD_SWORD, 1), 1, ChatColor.DARK_AQUA + "RENAME SWORD", ChatColor.GRAY + "Rename your sword\n" + ChatColor.AQUA + "50 Credits");
        addShopItem(12, new ItemStack(Material.LEATHER_CHESTPLATE, 1), 2, ChatColor.DARK_AQUA + "RECOLOR ARMOR", ChatColor.GRAY + "Colorize your armor");
        addShopItem(14, new ItemStack(Material.ANVIL, 1), 4, ChatColor.DARK_AQUA + "REFUND ENCHANTS", ChatColor.GRAY + "Refund your enchanted item");
        addShopItem(16, new ItemStack(Material.NAME_TAG, 1), 3, ChatColor.DARK_AQUA + "RENAME PREFIX", ChatColor.GRAY + "Customize your prefix\n" + ChatColor.AQUA + "100 Credits");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
