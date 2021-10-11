package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BlockListener implements Listener {

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            if (!TazPvP.punishmentManager.isBanned(event.getPlayer())) {
                Material blockType = event.getBlockPlaced().getType();
                ArrayList<Material> unreq = new ArrayList<>();
                unreq.add(Material.COAL_ORE);
                unreq.add(Material.IRON_ORE);
                unreq.add(Material.GOLD_ORE);
                unreq.add(Material.LAPIS_ORE);
                unreq.add(Material.DIAMOND_ORE);
                unreq.add(Material.EMERALD_ORE);

                if (!unreq.contains(blockType)) {
                    if (blockType == Material.WOOD) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                event.getBlockPlaced().setType(Material.AIR);
                            }
                        }.runTaskLater(TazPvP.getInstance(), 200L);
                    } else if (blockType == Material.SOIL) {
                        event.setCancelled(true);
                    } else {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                event.getBlockPlaced().setType(Material.AIR);
                            }
                        }.runTaskLater(TazPvP.getInstance(), 200L);
                    }
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        }
    }

}
