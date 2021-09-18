package net.ntdi.tazpvp.listeners;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class TrollListener implements Listener {
//    @EventHandler
//    public void onBedLeave(PlayerBedLeaveEvent event){
//        Location impactLocation = event.getBed().getLocation();
//        World world = impactLocation.getWorld();
//        for (int i = 0; i < 50; i++) {
//            world.spawnEntity(impactLocation, EntityType.CREEPER);
//        }
//        event.getPlayer().sendMessage(ChatColor.RED + "Good morning :)");
//    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE){
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cant break blocks in survival!");
            //event.getPlayer().playSound(event.getBlock().getLocation(), Sound.ENTITY_CAT_HISS, 10, 1);
        }
    }
}
