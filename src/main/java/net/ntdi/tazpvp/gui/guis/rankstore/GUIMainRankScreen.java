package net.ntdi.tazpvp.gui.guis.rankstore;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.gui.guis.GUICosmetics;
import net.ntdi.tazpvp.gui.guis.GUIRankStore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIMainRankScreen extends GUI {

    public GUIMainRankScreen(Player player) {
        super(player, 27, ChatColor.translateAlternateColorCodes('&', "&9&lSTORE &c&l25% SALE"));
        init();
        player.openInventory(inventory);
    }
    @SuppressWarnings("deprecation")
    public void init() {
        ItemStack rankItem = createItem(Material.EYE_OF_ENDER, ChatColor.BLUE + "" + ChatColor.BOLD + "RANKS", ChatColor.GRAY + "Purchase ranks", true);
        ItemStack donateItem = createItem(Material.BARRIER, ChatColor.BLUE + "" + ChatColor.BOLD + "UNBAN", ChatColor.GRAY + "Unban yourself\n" + ChatColor.RED + "100 Credits", true);
        ItemStack cosmeticsItem = createItem(Material.YELLOW_FLOWER, ChatColor.BLUE + "" + ChatColor.BOLD + "CREDIT SHOP", ChatColor.GRAY + "View cosmetics", true);
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), "");
        }
        setButtons(11, rankItem, event -> switchScreen(new GUIRankStore(player)));
        setButtons(15, donateItem, event -> {
            // TODO: make unban work :D
            if (TazPvP.punishmentManager.isBanned(player)) {
                if (TazPvP.statsManager.getCredits(player) >= 100) {
                    TazPvP.statsManager.addCredits(player, -100);
                    TazPvP.punishmentManager.removeBan(player);
                    player.sendMessage(ChatColor.GREEN + "You have been unbanned!");
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    String pexcmd = "pex user " + player.getName() + " group remove banned";
                    Bukkit.dispatchCommand(console, pexcmd);
                }
            } else {
                player.sendMessage(ChatColor.RED + "You are not banned!");
            }

        });
        setButtons(13, cosmeticsItem, event -> switchScreen(new GUICosmetics(player)));
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
