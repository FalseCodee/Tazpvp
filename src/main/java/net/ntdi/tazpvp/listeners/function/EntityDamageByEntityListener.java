package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void entityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            if(!event.getDamager().getLocation().getWorld().getName().equals(TazPvP.configFile.getString("arena.name"))){
                return;
            }

//            ItemStack air = new ItemStack(Material.AIR);
//            ArrayList<Material> swords = new ArrayList<>();
//            swords.add(Material.WOOD_SWORD);
//            swords.add(Material.STONE_SWORD);
//            swords.add(Material.IRON_SWORD);
//            swords.add(Material.DIAMOND_SWORD);
//            swords.add(Material.WOOD_PICKAXE);
//            swords.add(Material.STONE_PICKAXE);
//            swords.add(Material.IRON_PICKAXE);
//            swords.add(Material.DIAMOND_PICKAXE);

            OfflinePlayer damager = ((Player) event.getDamager()).getPlayer();

            if (TazPvP.punishmentManager.isBanned(damager)) {
                event.setCancelled(true);
                return;
            }


            if(!Objects.equals(((Player) event.getDamager()).getItemInHand(), new ItemStack(Material.AIR))){
                ((Player) event.getDamager()).giveExp(1);

            }
        }
    }
}
