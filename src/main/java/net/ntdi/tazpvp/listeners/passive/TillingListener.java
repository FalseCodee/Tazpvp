package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TillingListener implements Listener {

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            event.getBlockPlaced();
            if(event.getBlockPlaced().getType() == Material.SOIL){
                event.setCancelled(true);
            }
        }
    }
}
