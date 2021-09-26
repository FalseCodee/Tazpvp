package net.ntdi.tazpvp.listeners.items;

import net.milkbowl.vault.chat.Chat;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.Item;
import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.Items;
import net.ntdi.tazpvp.items.items.GrapplingHook;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class SnowballHitEvent implements Listener {

    @EventHandler
    public void onSnowballLand(ProjectileHitEvent event) {
        if(event.getEntity() instanceof Snowball) {
            Snowball ball = (Snowball)event.getEntity();
            if(ball.hasMetadata("IsSquid")) {

                Location landed = (Location)event.getEntity().getLocation();

                LivingEntity squid = (LivingEntity) landed.getWorld().spawnEntity(landed, EntityType.SQUID);
                squid.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "KABOOM");
                squid.setCustomNameVisible(true);

                float power = 2;

                World w = event.getEntity().getWorld();
                float x = event.getEntity().getLocation().getBlockX();
                float y = event.getEntity().getLocation().getBlockY();
                float z = event.getEntity().getLocation().getBlockZ();

                squid.setVelocity(new Vector(0, 10, 0));

                w.createExplosion(x, y, z, power, false, false);

                Bukkit.getScheduler().runTaskLater(TazPvP.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        squid.damage(100);
                    }
                }, 20);
            }
        }
    }
}
