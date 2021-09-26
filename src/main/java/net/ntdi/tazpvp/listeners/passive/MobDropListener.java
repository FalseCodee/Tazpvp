package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobDropListener implements Listener {

    @EventHandler
    public void onMobDrop(EntityDeathEvent event) {
        event.getDrops().clear();
    }
}
