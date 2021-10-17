package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.listeners.function.RecolorArmor;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class GUIRecolor extends GUI {
    public GUIRecolor(Player player) {
        super(player, 36, "COSMETICS");
        setItems();
        player.openInventory(inventory);
    }

    Player p = player;


    public void addArmorItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.fromRGB(150, 75, 0));
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 2:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.RED);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 3:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.ORANGE);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 4:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.YELLOW);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 5:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.LIME);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 6:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.GREEN);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 7:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.TEAL);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 8:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.BLUE);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 9:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.PURPLE);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 10:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.FUCHSIA);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 11:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.WHITE);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
                case 12:
                    if (TazPvP.statsManager.getCredits(p) >= 5) {
                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
                        new RecolorArmor().recolorArmor(p, Color.BLACK);
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Not enough Credits!");
                    }
                    break;
            }
        });
    }

    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addArmorItem(10, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BROWN.getDyeData()), 1, ChatColor.DARK_AQUA + "RESET ARMOR COLOR", ChatColor.GRAY + "Reset's your armor color!");
        addArmorItem(11, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData()), 2, ChatColor.DARK_AQUA + "SET COLOR TO RED", ChatColor.GRAY + "Color's your armor to RED\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(12, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.ORANGE.getData()), 3, ChatColor.DARK_AQUA + "SET COLOR TO ORANGE", ChatColor.GRAY + "Color's your armor to ORANGE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(13, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData()), 4, ChatColor.DARK_AQUA + "SET COLOR TO YELLOW", ChatColor.GRAY + "Color's your armor to YELLOW\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(14, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIME.getData()), 5, ChatColor.DARK_AQUA + "SET COLOR TO LIME", ChatColor.GRAY + "Color's your armor to LIME\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(15, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData()), 6, ChatColor.DARK_AQUA + "SET COLOR TO GREEN", ChatColor.GRAY + "Color's your armor to GREEN\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(16, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIGHT_BLUE.getData()), 7, ChatColor.DARK_AQUA + "SET COLOR TO LIGHT BLUE", ChatColor.GRAY + "Color's your armor to LIGHT BLUE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(20, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLUE.getData()), 8, ChatColor.DARK_AQUA + "SET COLOR TO BLUE", ChatColor.GRAY + "Color's your armor to BLUE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(21, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.PURPLE.getData()), 9, ChatColor.DARK_AQUA + "SET COLOR TO PURPLE", ChatColor.GRAY + "Color's your armor to PURPLE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(22, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.PINK.getData()), 10, ChatColor.DARK_AQUA + "SET COLOR TO PINK", ChatColor.GRAY + "Color's your armor to PINK\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(23, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.WHITE.getData()), 11, ChatColor.DARK_AQUA + "SET COLOR TO WHITE", ChatColor.GRAY + "Color's your armor to WHITE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(24, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), 12, ChatColor.DARK_AQUA + "SET COLOR TO BLACK", ChatColor.GRAY + "Color's your armor to BLACK\n" + ChatColor.AQUA + "5 CREDITS");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
