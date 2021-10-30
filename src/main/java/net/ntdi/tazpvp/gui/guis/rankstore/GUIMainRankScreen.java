package net.ntdi.tazpvp.gui.guis.rankstore;

import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.gui.guis.GUIBuyRank;
import net.ntdi.tazpvp.gui.guis.GUICosmetics;
import net.ntdi.tazpvp.gui.guis.GUIGiftRank;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIMainRankScreen extends GUI {

    public GUIMainRankScreen(Player player) {
        super(player, 27, ChatColor.translateAlternateColorCodes('&', "&9&lSTORE &r&c25% OFF SALE"));
        init();
        player.openInventory(inventory);
    }
    @SuppressWarnings("deprecation")
    public void init() {
        ItemStack rankItem = createItem(Material.EYE_OF_ENDER, ChatColor.BLUE + "" + ChatColor.BOLD + "RANKS", ChatColor.GRAY + "Buy and Gift Ranks.", true);
        ItemStack donateItem = createItem(Material.BARRIER, ChatColor.BLUE + "" + ChatColor.BOLD + "UNBAN", ChatColor.GRAY + "Buy a unban.\n" + ChatColor.RED + "$2.00", true);
        ItemStack cosmeticsItem = createItem(Material.YELLOW_FLOWER, ChatColor.BLUE + "" + ChatColor.BOLD + "CREDIT SHOP", ChatColor.GRAY + "Open the cosmetics menu.", true);
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), "");
        }
        setButtons(10, rankItem, event -> switchScreen(new GUIBuyRank(player)));
        setButtons(12, creditsItem, event -> switchScreen(new GUIGiftRank(player)));
        setButtons(16, donateItem, event -> {

        });
        setButtons(14, cosmeticsItem, event -> switchScreen(new GUICosmetics(player)));
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
