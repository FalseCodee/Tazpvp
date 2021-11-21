package net.ntdi.tazpvp.items.items;

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

import java.util.HashMap;
import java.util.UUID;

public class LevFeather extends ConsumableItem implements Listener {

    public LevFeather() { super(Items.LEVFEATHER, 10); }
    final HashMap<UUID, Long> cooldown = new HashMap<>();
    final int cooldownTime = 10;

    @Override
    public boolean execute(Player p, ItemStack itemStack){
        //super.execute(p, itemStack);

        return true;
    }

    @EventHandler
    public void onFeatherHit(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            if (event.getEntity().getWorld().getName().equals("arena") || event.getEntity().getWorld().getName().equals("grind")) {
                Player victim = (Player) event.getEntity();
                Player damager = (Player) event.getDamager();
                if (damager.getInventory().getItemInHand().getType() == Material.FEATHER){
                    if(cooldown.containsKey(damager.getUniqueId())){
                        long secondsLeft = cooldown.get(damager.getUniqueId())-System.currentTimeMillis();
                        if(secondsLeft>0) {
                            damager.sendMessage(ChatColor.RED + "On Cooldown!");
                        } else {
                            damager.sendMessage(ChatColor.GREEN + "Poisoned " + victim.getName());
                            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 70, 2));
                            cooldown.remove(damager.getUniqueId());
                        }
                    }
                    cooldown.put(damager.getUniqueId(), System.currentTimeMillis() + (cooldownTime * 1000L));
                }
            }
        }
    }
}

