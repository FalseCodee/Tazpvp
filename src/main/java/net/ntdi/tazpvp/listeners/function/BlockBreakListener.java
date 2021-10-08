package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
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
                p.getInventory().addItem(new ItemStack(mat));
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
