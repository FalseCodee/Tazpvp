package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TillingListener implements Listener {

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            event.getBlockPlaced();
            if(event.getBlockPlaced().getType() == Material.SOIL){
                event.setCancelled(true);
            }
            else if(event.getBlockPlaced().getType() == Material.DIRT){
                event.setCancelled(true);
            }
        }
    }
}
