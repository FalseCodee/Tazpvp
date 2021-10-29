package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

public class FallDamageListener implements Listener {

    private final Random rand = new Random();


    @EventHandler
    public void onFallDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (TazPvP.perkManager.getFallDamage(p)){
                    if (rand.nextInt(3) == 2){
                        double dmg = event.getDamage();
                        event.setCancelled(true);
                        p.damage(dmg/2);
                        p.sendMessage(ChatColor.GRAY + "Fall Damage perked activated");
                    }
                }
            }
        }
    }
}
