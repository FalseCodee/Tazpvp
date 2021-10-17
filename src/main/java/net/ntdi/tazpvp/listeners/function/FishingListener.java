package net.ntdi.tazpvp.listeners.function;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.w3c.dom.Entity;

import java.util.List;

public class FishingListener implements Listener {

    @EventHandler
    public void onFishing(PlayerFishEvent event){
        Entity caught = (Entity) event.getCaught();

        event.setExpToDrop(0);
        

    }

}
