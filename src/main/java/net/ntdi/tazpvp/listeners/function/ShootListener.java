package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Random;

public class ShootListener implements Listener {

    private final Random rand = new Random();

    @EventHandler
    public void onShoot(org.bukkit.event.entity.EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(player.hasPermission("is.banned") && !player.hasPermission("op")) {
                event.setCancelled(true);
            }

            if (TazPvP.perkManager.getArrow(player)){
                if (rand.nextInt(12) == 7){
                    player.getInventory().addItem(new org.bukkit.inventory.ItemStack(org.bukkit.Material.ARROW, 1));
                }
            }
        }
    }
}
