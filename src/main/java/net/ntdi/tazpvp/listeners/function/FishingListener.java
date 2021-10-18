package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.items.Item;
import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.Items;
import net.ntdi.tazpvp.items.items.GrapplingHook;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.w3c.dom.Entity;

import java.util.List;

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
