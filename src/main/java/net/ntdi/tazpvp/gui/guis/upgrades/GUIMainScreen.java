package net.ntdi.tazpvp.gui.guis.upgrades;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIMainScreen extends GUI {
    public GUIMainScreen(Player player) {
        super(player, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "Upgrade Menu");
        init();
        player.openInventory(inventory);
    }

    @SuppressWarnings("deprecation")
    public void init() {
        ItemStack sword = createItem(Material.WOOD_SWORD, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "SWORD", ChatColor.GRAY + "Click to see upgrades.");
        ItemStack pickaxe = createItem(Material.WOOD_PICKAXE, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "PICKAXE", ChatColor.GRAY + "Click to see upgrades.");
        ItemStack rod = createItem(Material.FISHING_ROD, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "FISHING ROD", ChatColor.GRAY + "Click to see upgrades.");
        ItemStack bow = createItem(Material.BOW, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "BOW", ChatColor.GRAY + "Click to see upgrades.");
        ItemStack armor = createItem(Material.LEATHER_CHESTPLATE, 1, ChatColor.WHITE + "" + ChatColor.BOLD + "ARMOR", ChatColor.GRAY + "Click to see upgrades.");
        ItemStack eye = createItem(Material.EYE_OF_ENDER, 1, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "REBIRTH", ChatColor.DARK_PURPLE + "≻ +1 Enchant Level\n" + ChatColor.DARK_PURPLE + "≻ +3 Exp Per Kill\n" + ChatColor.DARK_PURPLE + "≻ Strength on kill\n" + ChatColor.DARK_PURPLE + "≻ Speed 1\n" + ChatColor.RED + "Level " + ChatColor.WHITE + "150");
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), "");
        }

        //initialize the buttons
        setButtons(11, sword,
        event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.SWORD)));

        setButtons(12,pickaxe, event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.PICKAXE)));

        setButtons(13,rod, event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.ROD)));

        setButtons(14,bow, event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.BOW)));

        setButtons(15,armor, event -> switchScreen(new GUIUpgradeMenu(player, UpgradeTypes.ARMOR)));

        setButtons(22,eye, event -> {
            if(TazPvP.statsManager.getLevel(player) >= 150){
                player.sendMessage("Congratulations you have rebirthed!");
                TazPvP.statsManager.addRebirths(player, 1);
                TazPvP.statsManager.setMoney(player,0);
                TazPvP.statsManager.setPoints(player,0);
                TazPvP.statsManager.setLevel(player,0);
                player.giveExpLevels(-player.getLevel());
                player.setExp(0);
                PlayerUtils.equipStarter(player);
                Bukkit.getScheduler().runTask(TazPvP.getInstance(), player::closeInventory);
            } else {
                player.sendMessage(ChatColor.RED + "Reach level " + ChatColor.WHITE + "150" + ChatColor.RED + " to unlock this feature!");
            }
        });
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
