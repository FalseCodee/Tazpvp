package net.ntdi.tazpvp.listeners.items;

import net.ntdi.tazpvp.items.Item;
import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.Items;
import net.ntdi.tazpvp.items.items.GrapplingHook;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerFishEvent implements Listener {

    @EventHandler
    public void onFish(org.bukkit.event.player.PlayerFishEvent event) {
        if(event.getState() == org.bukkit.event.player.PlayerFishEvent.State.FAILED_ATTEMPT) {
            if(event.getPlayer().getItemInHand().getType() == Material.FISHING_ROD) {
                if(event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() &&
                        event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(Items.GRAPPLING_HOOK.display)) {
                    for(Item item : ItemManager.items) {
                        if(item instanceof GrapplingHook) {
                            ((GrapplingHook) item).onReel(event.getPlayer(), event.getHook().getLocation());
                        }
                    }
                }
            }
        }
    }
}
