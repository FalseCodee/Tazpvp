package net.ntdi.tazpvp.listeners.items;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class SnowballHitEvent implements Listener {

    @EventHandler
    public void onSnowballLand(ProjectileHitEvent event) {
        if(event.getEntity() instanceof Snowball) {
            Snowball ball = (Snowball)event.getEntity();
            if(ball.hasMetadata("IsSquid")) {

                Player p = (Player) event.getEntity().getShooter();

//                p.spigot().setCollidesWithEntities(true);

//                LivingEntity squid = (LivingEntity) event.getEntity().getPassenger();
//
//                squid.damage(100);
//                squid.setHealth(0);

                Location landed = event.getEntity().getLocation();

                float power = 2;
                World w = event.getEntity().getWorld();
                float x = event.getEntity().getLocation().getBlockX();
                float y = event.getEntity().getLocation().getBlockY();
                float z = event.getEntity().getLocation().getBlockZ();

                w.createExplosion(x, y, z, power, false, false);


                ArmorStand as = (ArmorStand) landed.getWorld().spawnEntity(landed, EntityType.ARMOR_STAND); //Spawn the ArmorStand

                as.setGravity(false); //Make sure it doesn't fall
                as.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
                as.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "KABOOM!"); //Set this to the text you want
                as.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
                as.setVisible(false); //Makes the ArmorStand invisible
                as.setSmall(true);


                LivingEntity squid = (LivingEntity) landed.getWorld().spawnEntity(landed, EntityType.SQUID);
                squid.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "KABOOM");
                squid.setCustomNameVisible(true);
                squid.setVelocity(new Vector(0, 2, 0));

                Bukkit.getScheduler().runTaskLater(TazPvP.getInstance(), () -> squid.damage(100), 20);

                Bukkit.getScheduler().runTaskLater(TazPvP.getInstance(), () -> {
                    as.damage(100);
                    as.setHealth(0);

                }, 40);
            }
        }
    }
}
