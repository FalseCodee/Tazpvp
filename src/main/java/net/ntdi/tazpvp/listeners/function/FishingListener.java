package net.ntdi.tazpvp.listeners.function;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishingListener implements Listener {

    @EventHandler
    public void onFishing(PlayerFishEvent event){
        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if(event.getPlayer().getItemInHand().getType() == Material.FISHING_ROD) {
                event.setCancelled(true);
            }
        }
    }
}
