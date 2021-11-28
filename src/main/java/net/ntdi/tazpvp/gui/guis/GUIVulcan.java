package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIVulcan extends GUI {

    Player target;

    public GUIVulcan(Player player, Player ac) {
        super(player, 27, ChatColor.RED + "" + ChatColor.BOLD + "AC " + ac.getName() + "'s " + ChatColor.GOLD + "Vulcan");
        target = ac;
        setItems();
        player.openInventory(inventory);

    }

    final Player p = player;

    public void addACItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    p.closeInventory();
                    p.getPlayer().performCommand("log " + target.getName());
                    break;
                case 2:
                    p.closeInventory();
                    p.getPlayer().performCommand("vulcan violations " + target.getName());
                    break;
                case 3:
                    p.closeInventory();
                    p.getPlayer().performCommand("vulcan top");
                    break;
                case 4:
                    p.closeInventory();
                    p.getPlayer().performCommand("vulcan freeze " + target.getName());
                    break;
                case 5:
                    p.closeInventory();
                    p.getPlayer().performCommand("vulcan cps " + target.getName());
                    break;
                case 6:
                    p.closeInventory();
                    p.getPlayer().performCommand("vulcan knockback " + target.getName());
                    break;

            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        addACItem(10, new ItemStack(Material.PAPER, 1), 1, ChatColor.RED + "LOG", ChatColor.YELLOW + "View the logs of " + target.getName());
        addACItem(11, new ItemStack(Material.BARRIER, 1), 2, ChatColor.RED + "VIOLATIONS", ChatColor.YELLOW + "View the current violations of " + target.getName());
        addACItem(12, new ItemStack(Material.CHEST, 1), 3, ChatColor.RED + "TOP", ChatColor.YELLOW + "View the top violations, in general or smt idk");

        addACItem(14, new ItemStack(Material.ICE, 1), 4, ChatColor.RED + "FREEZE", ChatColor.YELLOW + "Freeze " + target.getName());
        addACItem(15, new ItemStack(Material.DIAMOND_SWORD, 1), 5, ChatColor.RED + "CPS", ChatColor.YELLOW + "Get the cps of " + target.getName());
        addACItem(16, new ItemStack(Material.IRON_DOOR, 1), 6, ChatColor.RED + "KNOCKBACK", ChatColor.YELLOW + "Get the knock-back of " + target.getName());

        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
