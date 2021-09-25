package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIShop extends GUI {

    public GUIShop(Player player) {
        super(player, 54, "SHOP");
        init();
        player.openInventory(inventory);
    }

    public void init() {
        //initialize the buttons
        setButtons(10, event -> {
            player.sendMessage("Set to Iron!");
            setItems();
        });

        setButtons(12, event -> {
            player.sendMessage("Set to Gold!");
            setItems();
        });

        setButtons(14, event -> {
            player.sendMessage("Set to Emerald!");
            setItems();
        });
        setButtons(16, event -> {
            player.sendMessage("Set to Coal!");
            setItems();
        });
        setItems();
    }

    ItemStack flower = new ItemStack(Material.RED_ROSE, 1, (short) 1);

    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            if(i == 10) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "Butter", ChatColor.GRAY + "Instant Health\n"+ ChatColor.GOLD +"$50");
            } else if(i == 12) {
                items[i] = createItem(Material.EYE_OF_ENDER, 1, ChatColor.BLUE + "Agility", ChatColor.GRAY + "Instant Speed\n"+ ChatColor.GOLD +"$50");
            } else if(i == 14) {
                items[i] = createItem(Material.RED_ROSE, 1, ChatColor.BLUE + "Extinguisher", ChatColor.GRAY + "Become cool again\n"+ ChatColor.GOLD +"$50");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_HOE, 1, ChatColor.BLUE + "Tactical Launcher", ChatColor.GRAY + "Explosive Squids\n"+ ChatColor.GOLD +"$450");
            } else if(i == 16) {
                items[i] = createItem(Material.CARROT_STICK, 1, ChatColor.BLUE + "Grappling Hook", ChatColor.GRAY + "Swing Away\n"+ ChatColor.GOLD +"$450");
            } else if(i == 16) {
                items[i] = createItem(Material.ARROW, 1, ChatColor.BLUE + "Arrows", ChatColor.GRAY + "5 Arrows\n"+ ChatColor.GOLD +"$25");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else if(i == 16) {
                items[i] = createItem(Material.GOLD_ORE, 1, ChatColor.BLUE + "", ChatColor.GRAY + "\n"+ ChatColor.GOLD +"$");
            } else {
                items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), "");
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
