package net.ntdi.tazpvp.gui.guis.upgrades;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.gui.guis.GUIPerk;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIMainScreen extends GUI {
    public GUIMainScreen(Player player) {
        super(player, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "UPGRADE");
        init();
        player.openInventory(inventory);
    }

    @SuppressWarnings("deprecation")
    public void init() {
        ItemStack sword = createItem(Material.WOOD_SWORD, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "SWORD", ChatColor.GRAY + "Click to see upgrades.\n" + ChatColor.RED + "WARNING: Upgrades lost on death.");
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sword.setItemMeta(swordMeta);
        ItemStack pickaxe = createItem(Material.WOOD_PICKAXE, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "PICKAXE", ChatColor.GRAY + "Click to see upgrades.\n" + ChatColor.RED + "WARNING: Upgrades lost on death.");
        ItemMeta pickaxeMeta = pickaxe.getItemMeta();
        pickaxeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pickaxe.setItemMeta(pickaxeMeta);
        ItemStack rod = createItem(Material.FISHING_ROD, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "FISHING ROD", ChatColor.GRAY + "Click to see upgrades.\n" + ChatColor.RED + "WARNING: Upgrades lost on death.");
        ItemStack bow = createItem(Material.BOW, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "BOW", ChatColor.GRAY + "Click to see upgrades.\n" + ChatColor.RED + "WARNING: Upgrades lost on death.");
        ItemStack armor = createItem(Material.LEATHER_CHESTPLATE, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "ARMOR", ChatColor.GRAY + "Click to see upgrades.\n" + ChatColor.RED + "WARNING: Upgrades lost on death.");
        ItemStack eye = createItem(Material.EYE_OF_ENDER, 1, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "REBIRTH", ChatColor.LIGHT_PURPLE + "Exchange levels for:\n" + ChatColor.DARK_PURPLE + "≻ +1 Enchant Level\n" + ChatColor.DARK_PURPLE + "≻ +3 Exp Per Kill\n" + ChatColor.DARK_PURPLE + "≻ +1 Max health\n" + ChatColor.DARK_PURPLE + "≻ Infinite Arrows\n" + ChatColor.DARK_PURPLE + "≻ Strength on kill\n" + ChatColor.DARK_PURPLE + "≻ Speed on kill\n" + ChatColor.RED + "Level " + ChatColor.WHITE + "75\n" + ChatColor.GOLD+"Cost: "+ChatColor.GRAY+"50 Points");
        ItemStack firecharge = createItem(Material.FIREBALL, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "PERKS", ChatColor.GRAY + "Click to see perks.");
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), "");
        }

        //initialize the buttons
        setButtons(10, sword,
        event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.SWORD)));

        setButtons(11,pickaxe, event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.PICKAXE)));

        setButtons(12,bow, event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.BOW)));

        setButtons(13,armor, event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.ARMOR)));

        setButtons(16,eye, event -> {
            if(TazPvP.statsManager.getLevel(player) >= 75){
                if(TazPvP.statsManager.getPoints(player) >= 50) {
                    TazPvP.statsManager.addPoints(player, -50);
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " re-birthed and became stronger.");
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    TazPvP.statsManager.rebirthPlayer(player);
                    TazPvP.statsManager.addRebirths(player, 1);
                    player.giveExpLevels(-player.getLevel());
                    Bukkit.getScheduler().runTask(TazPvP.getInstance(), player::closeInventory);
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.playSound(pl.getLocation(), Sound.WITHER_DEATH, 1, 1);
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Not enough points!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Reach level " + ChatColor.WHITE + "75" + ChatColor.RED + " to use this feature!");
            }
        });

        setButtons(15,firecharge, event -> switchScreen(new GUIPerk(player)));

        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
