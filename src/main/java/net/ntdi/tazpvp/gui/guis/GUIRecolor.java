package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.listeners.function.RecolorArmor;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIRecolor extends GUI {
    public GUIRecolor(Player player) {
        super(player, 27, "COSMETICS");
        setItems();
        player.openInventory(inventory);
    }

    Player p = player;

    public void addArmorItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    new RecolorArmor().recolorArmor(p, Color.MAROON);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 2:
                    new RecolorArmor().recolorArmor(p, Color.RED);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 3:
                    new RecolorArmor().recolorArmor(p, Color.ORANGE);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 4:
                    new RecolorArmor().recolorArmor(p, Color.YELLOW);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 5:
                    new RecolorArmor().recolorArmor(p, Color.LIME);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 6:
                    new RecolorArmor().recolorArmor(p, Color.GREEN);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 7:
                    new RecolorArmor().recolorArmor(p, Color.TEAL);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 8:
                    new RecolorArmor().recolorArmor(p, Color.BLUE);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 9:
                    new RecolorArmor().recolorArmor(p, Color.PURPLE);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 10:
                    new RecolorArmor().recolorArmor(p, Color.FUCHSIA);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 11:
                    new RecolorArmor().recolorArmor(p, Color.WHITE);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
                case 12:
                    new RecolorArmor().recolorArmor(p, Color.BLACK);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    break;
            }
        });
    }

    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addArmorItem(10, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.BROWN.getData()), 1, ChatColor.DARK_AQUA + "RESET ARMOR COLOR", ChatColor.GRAY + "Reset's your armor color!");
        addArmorItem(11, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.RED.getData()), 2, ChatColor.DARK_AQUA + "SET COLOR TO RED", ChatColor.GRAY + "Color's your armor to RED");
        addArmorItem(12, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.ORANGE.getData()), 3, ChatColor.DARK_AQUA + "SET COLOR TO ORANGE", ChatColor.GRAY + "Color's your armor to ORANGE");
        addArmorItem(13, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.YELLOW.getData()), 4, ChatColor.DARK_AQUA + "SET COLOR TO YELLOW", ChatColor.GRAY + "Color's your armor to YELLOW");
        addArmorItem(14, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.LIME.getData()), 5, ChatColor.DARK_AQUA + "SET COLOR TO LIME", ChatColor.GRAY + "Color's your armor to LIME");
        addArmorItem(15, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.GREEN.getData()), 6, ChatColor.DARK_AQUA + "SET COLOR TO GREEN", ChatColor.GRAY + "Color's your armor to GREEN");
        addArmorItem(16, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.LIGHT_BLUE.getData()), 7, ChatColor.DARK_AQUA + "SET COLOR TO LIGHT BLUE", ChatColor.GRAY + "Color's your armor to LIGHT BLUE");
        addArmorItem(20, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.BLUE.getData()), 8, ChatColor.DARK_AQUA + "SET COLOR TO BLUE", ChatColor.GRAY + "Color's your armor to BLUE");
        addArmorItem(21, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.PURPLE.getData()), 9, ChatColor.DARK_AQUA + "SET COLOR TO PURPLE", ChatColor.GRAY + "Color's your armor to PURPLE");
        addArmorItem(22, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.PINK.getData()), 10, ChatColor.DARK_AQUA + "SET COLOR TO PINK", ChatColor.GRAY + "Color's your armor to PINK");
        addArmorItem(23, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.WHITE.getData()), 11, ChatColor.DARK_AQUA + "SET COLOR TO WHITE", ChatColor.GRAY + "Color's your armor to WHITE");
        addArmorItem(24, new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.BLACK.getDyeData()), 12, ChatColor.DARK_AQUA + "SET COLOR TO BLACK", ChatColor.GRAY + "Color's your armor to BLACK");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
