package net.ntdi.tazpvp.listeners.items;

import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class FireballHitEvent implements Listener {

    @EventHandler
    public void onFireballHit(ProjectileHitEvent event){
        if(event.getEntity() instanceof Fireball) {
            Fireball ball = (Fireball) event.getEntity();
            if(ball.hasMetadata("IsSquid")) {
                event.getEntity().getLocation();
            }
        }
    }


}
