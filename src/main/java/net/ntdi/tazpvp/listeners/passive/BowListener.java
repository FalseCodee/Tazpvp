package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class BowListener implements Listener {
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    int cooldownTime = 5;

    @EventHandler
    public void onShoot(org.bukkit.event.entity.EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if(cooldowns.containsKey(p.getName())) {
                long secondsLeft = ((cooldowns.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(secondsLeft>0) {
                    event.setCancelled(true);
                    p.sendMessage("Your bow is on cool-down "+ secondsLeft +" seconds!");
                    return;
                }
            }
            // No cooldown found or cooldown has expired, save new cooldown
            cooldowns.put(p.getName(), System.currentTimeMillis());
        }
    }
}
