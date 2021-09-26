package net.ntdi.tazpvp.listeners.items;

import net.milkbowl.vault.chat.Chat;
import net.ntdi.tazpvp.items.Item;
import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.Items;
import net.ntdi.tazpvp.items.items.GrapplingHook;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import javax.swing.*;

public class SnowballHitEvent implements Listener {

    @EventHandler
    public void onSnowballLand(ProjectileHitEvent event) {
        if(event.getEntity() instanceof Snowball) {
            Snowball ball = (Snowball)event.getEntity();
            if(ball.hasMetadata("IsSquid")) {

                Location landed = (Location)event.getEntity().getLocation();

                Entity squid = landed.getWorld().spawnEntity(landed, EntityType.SQUID);
                squid.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "KABOOM");
                squid.setCustomNameVisible(true);

                float power = 2;

                World w = event.getEntity().getWorld();
                float x = event.getEntity().getLocation().getBlockX();
                float y = event.getEntity().getLocation().getBlockY();
                float z = event.getEntity().getLocation().getBlockZ();



                w.createExplosion(x, y, z, power, false, false);



            }
        }
    }
}
