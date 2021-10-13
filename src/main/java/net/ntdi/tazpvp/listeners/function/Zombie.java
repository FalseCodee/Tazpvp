package net.ntdi.tazpvp.listeners.function;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Zombie implements Listener {

    World world = Bukkit.getWorld("grind");

    double xzomb = 37.5;
    double yzomb = 41;
    double zzomb = -96.5;

    @EventHandler
    public void onZombieDie(EntityDeathEvent event){
        if (event.getEntity() instanceof Zombie && event.getEntity().getName().equals("zombieman")){
//
        }
    }
}
