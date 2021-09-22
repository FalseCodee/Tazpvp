package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIShop extends GUI {

    public GUIShop(Player player) {
        super(player, 27, "Example Menu");
        init();
        player.openInventory(inventory);
    }

    ItemStack item = createItem(Material.COAL_BLOCK, 1,"This is Coal");

    public void init() {
        //initialize the buttons
        setButtons(10, event -> {
            player.sendMessage("Set to Iron!");
            item = createItem(Material.IRON_BLOCK, 1,"This is Iron");
            setItems();
        });

        setButtons(12, event -> {
            player.sendMessage("Set to Gold!");
            item = createItem(Material.GOLD_BLOCK, 1,"This is Gold");
            setItems();
        });

        setButtons(14, event -> {
            player.sendMessage("Set to Emerald!");
            item = createItem(Material.EMERALD_BLOCK, 1,"This is Emerald");
            setItems();
        });
        setButtons(16, event -> {
            player.sendMessage("Set to Coal!");
            item = createItem(Material.COAL_BLOCK, 1,"This is Coal");
            setItems();
        });
        setItems();
    }

    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            if(i == 10) {
                items[i] = createItem(Material.IRON_ORE, 1,"Click to set to Iron");
            } else if(i == 12) {
                items[i] = createItem(Material.GOLD_ORE, 1,"Click to set to Gold");
            } else if(i == 14) {
                items[i] = createItem(Material.EMERALD_ORE, 1,"Click to set to Emerald");
            } else if(i == 16) {
                items[i] = createItem(Material.COAL_ORE, 1,"Click to set to Coal");
            } else {
                items[i] = item;
            }
        }
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
