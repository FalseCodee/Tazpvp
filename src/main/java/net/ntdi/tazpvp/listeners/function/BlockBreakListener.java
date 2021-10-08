package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player p = event.getPlayer();

        if(p.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
            Block b = event.getBlock();
            Material mat = b.getType();

           if (TazPvP.blocks.contains(mat)){
               if(mat == Material.COAL_ORE) {
                    TazPvP.statsManager.addMoney(p, 1);
                    p.sendMessage(ChatColor.GOLD + "+ 1 Coin");
               } else if( mat == Material.IRON_ORE) {
                   TazPvP.statsManager.addMoney(p, 2);
                   p.sendMessage(ChatColor.GOLD + "+ 2 Coins");
               } else if( mat == Material.LAPIS_ORE) {
                   TazPvP.statsManager.addMoney(p, 3);
                   p.sendMessage(ChatColor.GOLD + "+ 3 Coins");
               } else if( mat == Material.GOLD_ORE) {
                   TazPvP.statsManager.addMoney(p, 4);
                   p.sendMessage(ChatColor.GOLD + "+ 4 Coins");
               } else if( mat == Material.DIAMOND_ORE) {
                   TazPvP.statsManager.addMoney(p, 5);
                   p.sendMessage(ChatColor.GOLD + "+ 5 Coins");
               } else if( mat == Material.EMERALD_ORE) {
                   TazPvP.statsManager.addMoney(p, 6);
                   p.sendMessage(ChatColor.GOLD + "+ 6 Coins");
               } p.getInventory().addItem(new ItemStack(mat));
                b.setType(Material.BEDROCK);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        b.setType(mat);
                    }
                }.runTaskLater(TazPvP.getInstance(), 200L);
           }
        }
    }
}
