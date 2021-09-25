package net.ntdi.tazpvp.gui.guis.rankstore;

import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIMainRankScreen extends GUI {

    public GUIMainRankScreen(Player player) {
        super(player, 36, ChatColor.translateAlternateColorCodes('&', "&9&lSTORE &r&c25% OFF SALE"));
        init();
        player.openInventory(inventory);
    }
    public void init() {
         ItemStack rankItem = createItem(Material.EYE_OF_ENDER, ChatColor.BLUE + "" + ChatColor.BOLD + "RANKS", ChatColor.GRAY + "Purchase a rank.", true);
        ItemStack creditsItem = createItem(Material.DIAMOND, ChatColor.BLUE + "" + ChatColor.BOLD + "CREDITS", ChatColor.GRAY + "Purchase credits to gift ranks or custom prefixes.", true);
        ItemStack donateItem = createItem(Material.SLIME_BALL, ChatColor.BLUE + "" + ChatColor.BOLD + "DONATE", ChatColor.GRAY + "Just donate.", true);
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), "");
        }
        setButtons(11, rankItem, event -> {

        });
        setButtons(13, creditsItem, event -> {

        });
        setButtons(15, donateItem, event -> {

        });
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
