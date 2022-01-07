package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.Random;

public class FallDamageListener implements Listener {

    private final Random rand = new Random();


    @EventHandler
    public void onFallDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player && event.getEntity().getWorld().getName().equals("arena")){
            Player p = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (!isFallDamage(p)) {
                    event.setCancelled(true);
                } else {
                    if (TazPvP.perkManager.getFallDamage(p)){
                        if (rand.nextInt(3) == 2){
                            double dmg = event.getDamage();
                            event.setCancelled(true);
                            p.damage(dmg/2);
                            //p.sendMessage(ChatColor.GRAY + "Fall Damage perked activated");
                        }
                    }
                }
            }
        }
    }

    public boolean isFallDamage(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("fallDamage");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return true;
    }
}
