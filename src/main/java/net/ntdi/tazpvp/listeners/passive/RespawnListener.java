package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;

public class RespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(Bukkit.getWorld("spawn"), 0.5, 40, 0.5, 180, 0));
        event.getPlayer().setHealth(20);
        event.getPlayer().setMaxHealth(20);
        for (PotionEffect effect : event.getPlayer().getActivePotionEffects()) {
            event.getPlayer().removePotionEffect(effect.getType());
        }
    }
}
