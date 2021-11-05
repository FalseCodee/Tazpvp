package net.ntdi.tazpvp.items.items;

import net.milkbowl.vault.chat.Chat;
import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;
import java.util.WeakHashMap;

public class LevFeather extends ConsumableItem implements Listener {

    public LevFeather() { super(Items.LEVFEATHER, 10); }
    final HashMap<UUID, Long> cooldown = new HashMap<>();
    int cooldownTime = 10;

    @Override
    public boolean execute(Player p, ItemStack itemStack){
        if(super.execute(p, itemStack)) {
            return true;
        }

        return true;
    }

    @EventHandler
    public void onFeatherHit(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player victim = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            if (damager.getInventory().getItemInHand().getType() == Material.FEATHER){
                if(cooldown.containsKey(damager.getUniqueId())){
                    long secondsLeft = cooldown.get(damager.getUniqueId())-System.currentTimeMillis();
                    if(secondsLeft>0) {
                        damager.sendMessage(ChatColor.RED + "On Cooldown!");
                    } else {
                        damager.sendMessage(ChatColor.GREEN + "Poisoned " + victim.getName());
                        victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 1));
                        cooldown.remove(damager.getUniqueId());
                    }
                }
                cooldown.put(damager.getUniqueId(), System.currentTimeMillis() + (cooldownTime * 1000L));
            }

        }
    }

}

