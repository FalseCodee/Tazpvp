//package net.ntdi.tazpvp.listeners;
//
//import org.bukkit.Location;
//import org.bukkit.World;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerEggThrowEvent;
//
//public class EggListener implements Listener {
//
//    @EventHandler
//    public void onPlayerEgg(PlayerEggThrowEvent event){
//        Location impactLocation = event.getEgg().getLocation();
//        World world = impactLocation.getWorld();
//        world.createExplosion(impactLocation, 7f);
//    }
//
//}
