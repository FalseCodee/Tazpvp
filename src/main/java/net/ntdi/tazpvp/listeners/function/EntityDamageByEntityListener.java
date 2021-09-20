package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void entityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            if(!event.getDamager().getLocation().getWorld().getName().equals(TazPvP.configFile.getString("arena.name"))){
                return;
            }
            ((Player) event.getDamager()).giveExp(1);
        }
    }
}
