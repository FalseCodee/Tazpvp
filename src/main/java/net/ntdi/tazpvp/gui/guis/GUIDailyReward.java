package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;

public class GUIDailyReward extends GUI {

    public GUIDailyReward(Player player) {
        super(player, 9, ChatColor.RED + "Daily Rewards");
        setItems();
        player.openInventory(inventory);
    }
    @SuppressWarnings("deprecation")
    public void setItems(){
        if(System.currentTimeMillis() - TazPvP.statsManager.getLastReward(player) > 1000*60*60*12) {
            for(int i = 0; i < inventory.getSize(); i++) {
                if(i == 4) {
                    items[i] = createItem(Material.CHEST, 1, ChatColor.GREEN + "Click to redeem your daily reward!");
                } else {
                    items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIME.getData()), ChatColor.GREEN + "Click to redeem your daily reward!");
                }
            }
        } else {
            long seconds = ((1000*60*60*12 + TazPvP.statsManager.getLastReward(player)) - System.currentTimeMillis())/1000;
            for(int i = 0; i < inventory.getSize(); i++) {
                if(i == 4) {
                    items[i] = createItem(Material.REDSTONE_BLOCK, 1, ChatColor.RED + "" + ChatColor.BOLD + "Come back later!", ChatColor.RED + "Come back in\n" + ChatColor.WHITE + StringUtils.secondsToHHMMSS(seconds));
                } else {
                    items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData()), ChatColor.RED + "Come back later!");
                }
            }
        }
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
        if(System.currentTimeMillis() - TazPvP.statsManager.getLastReward(player) > 1000*60*60*12) {
            Bukkit.getScheduler().runTask(TazPvP.getInstance(), player::closeInventory);
            TazPvP.statsManager.setLastReward(player);
            player.sendMessage(ChatColor.AQUA + "Come back in 12 hour to redeem again!");
            player.sendMessage("+ " + 200 + ChatColor.YELLOW + " Coins");
            TazPvP.statsManager.addMoney(player, 200);
            player.sendMessage("+ " + 200 + ChatColor.GREEN + " EXP");
            player.giveExp(80);
            player.sendMessage("+ " + 1 + ChatColor.AQUA + " Credit");
            //TODO: Add Credits
        } else {
            setItems();
        }
    }

}
