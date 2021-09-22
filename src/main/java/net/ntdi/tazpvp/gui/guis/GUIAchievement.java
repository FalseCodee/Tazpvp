package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIAchievement extends GUI {

    public GUIAchievement(Player player) {
        super(player, 54, "Achievement");
        init();
        player.openInventory(inventory);
    }

    ItemStack item = createItem(Material.COAL_BLOCK, 1,"This is Coal");

    public void init() {
        //initialize the buttons
        items[10] = createItem(Material.MINECART, 1, "Ach");
        items[11] = createItem(Material.MINECART, 1, "Ach");
        items[12] = createItem(Material.MINECART, 1, "Ach");
        items[13] = createItem(Material.MINECART, 1, "Ach");
        items[14] = createItem(Material.MINECART, 1, "Ach");
        items[15] = createItem(Material.MINECART, 1, "Ach");
        items[16] = createItem(Material.MINECART, 1, "Ach");

        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
