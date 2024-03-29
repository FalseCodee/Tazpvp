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
        super(player, 36, ChatColor.BLUE + "" + ChatColor.BOLD + "CREDITS〡" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + TazPvP.statsManager.getCredits(player));
        setItems();
        player.openInventory(inventory);
    }

    final Player p = player;


    public void addArmorItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
//                    if (TazPvP.statsManager.getCredits(p) >= 5) {
//                        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-5);
//                        new RecolorArmor().recolorArmor(p, Color.fromRGB(150, 75, 0));
//                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
//                    } else {
//                        TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! " + ChatColor.WHITE + "[CLICK HERE]");
//                            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
//                            p.spigot().sendMessage(nocred);
//                    }
                    new RecolorArmor().recolorArmor(p, Color.fromRGB(150, 75, 0));
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
    @SuppressWarnings("deprecation")
    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addArmorItem(10, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.BROWN.getDyeData()), 1, ChatColor.DARK_AQUA + "RESET ARMOR COLOR", ChatColor.GRAY + "Reset's your armor color!");
        addArmorItem(11, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.RED.getData()), 2, ChatColor.DARK_AQUA + "SET COLOR TO RED", ChatColor.GRAY + "Color's your armor to RED\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(12, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.ORANGE.getData()), 3, ChatColor.DARK_AQUA + "SET COLOR TO ORANGE", ChatColor.GRAY + "Color's your armor to ORANGE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(13, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.YELLOW.getData()), 4, ChatColor.DARK_AQUA + "SET COLOR TO YELLOW", ChatColor.GRAY + "Color's your armor to YELLOW\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(14, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.LIME.getData()), 5, ChatColor.DARK_AQUA + "SET COLOR TO LIME", ChatColor.GRAY + "Color's your armor to LIME\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(15, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.GREEN.getData()), 6, ChatColor.DARK_AQUA + "SET COLOR TO GREEN", ChatColor.GRAY + "Color's your armor to GREEN\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(16, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.LIGHT_BLUE.getData()), 7, ChatColor.DARK_AQUA + "SET COLOR TO LIGHT BLUE", ChatColor.GRAY + "Color's your armor to LIGHT BLUE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(20, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.BLUE.getData()), 8, ChatColor.DARK_AQUA + "SET COLOR TO BLUE", ChatColor.GRAY + "Color's your armor to BLUE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(21, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.PURPLE.getData()), 9, ChatColor.DARK_AQUA + "SET COLOR TO PURPLE", ChatColor.GRAY + "Color's your armor to PURPLE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(22, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.PINK.getData()), 10, ChatColor.DARK_AQUA + "SET COLOR TO PINK", ChatColor.GRAY + "Color's your armor to PINK\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(23, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.WHITE.getData()), 11, ChatColor.DARK_AQUA + "SET COLOR TO WHITE", ChatColor.GRAY + "Color's your armor to WHITE\n" + ChatColor.AQUA + "5 CREDITS");
        addArmorItem(24, new ItemStack(Material.STAINED_GLASS, 1, DyeColor.BLACK.getData()), 12, ChatColor.DARK_AQUA + "SET COLOR TO BLACK", ChatColor.GRAY + "Color's your armor to BLACK\n" + ChatColor.AQUA + "5 CREDITS");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
