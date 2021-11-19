package net.ntdi.tazpvp.gui.guis;


import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.gui.guis.upgrades.GUIUpgradeMenu;
import net.ntdi.tazpvp.gui.guis.upgrades.UpgradeTypes;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIRankStore extends GUI {

    public GUIRankStore(Player player) {
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
                    switchScreen(new GUIGIFTORBUY(player,1));
                    break;
                case 2:
                    switchScreen(new GUIGIFTORBUY(player,2));
                    break;
                case 3:
                    switchScreen(new GUIGIFTORBUY(player,3));
                    break;
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addShopItem(11, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData()), 1, ChatColor.DARK_AQUA + "VIP" + ChatColor.AQUA + " 250 Credits",
                ChatColor.RED + "〡Enderchest command\n" + ChatColor.RED + "〡Hat command\n" + ChatColor.RED + "〡Durable Blocks\n" + ChatColor.RED + "〡Anti-spam Bypass\n" + ChatColor.RED + "〡RGB Blocks\n" + ChatColor.RED + "〡VIP++ discord rank");
        addShopItem(13, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData()),2, ChatColor.DARK_AQUA + "MVP" + ChatColor.AQUA + " 450 Credits",
                ChatColor.GOLD + "〡Enderchest command\n" + ChatColor.GOLD + "〡Hat command\n" + ChatColor.GOLD + "〡Nickname command\n" + ChatColor.GOLD + "〡Invsee command\n" + ChatColor.GOLD + "〡Durable Blocks\n" + ChatColor.GOLD + "〡Anti-spam Bypass\n" + ChatColor.GOLD + "〡RGB Blocks\n" + ChatColor.GOLD + "〡VIP++ discord rank");
        addShopItem(15, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLUE.getData()),3, ChatColor.DARK_AQUA + "MVP+" + ChatColor.AQUA + " 950 Credits",
                ChatColor.AQUA + "〡Enderchest command\n" + ChatColor.AQUA + "〡Hat command\n" + ChatColor.AQUA + "〡Nickname command\n" + ChatColor.AQUA + "〡Votekick command\n" + ChatColor.AQUA + "〡Invsee command\n" + ChatColor.AQUA + "〡Durable Blocks\n" + ChatColor.AQUA + "〡Anti-spam Bypass\n" + ChatColor.AQUA + "〡No Lag-back\n" + ChatColor.AQUA + "〡RGB Blocks\n" + ChatColor.AQUA + "〡VIP++ discord rank");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
