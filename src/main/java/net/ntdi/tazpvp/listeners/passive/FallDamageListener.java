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

    private final Random random = new Random();

    private int next() {
        if (random.nextBoolean()) {
            return 1;
        } else {
            return 2;
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event){
        Player p = (Player) event.getEntity();
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (TazPvP.perkManager.getFallDamage(p)){
                if (next() == 2){
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.GRAY + "Fall Damage perked activated");
                }
            }
        }
    }

}
