package net.ntdi.tazpvp.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.WeakHashMap;

public class ConsumableItem extends Item{
    final WeakHashMap<UUID, Long> cooldown = new WeakHashMap<>();
    final int cooldownTime;
    public ConsumableItem(Items item, int cooldownTime) {
        super(item);
        this.cooldownTime = cooldownTime;
    }
    public boolean execute(Player p, ItemStack itemStack) {
        if(cooldown.containsKey(p.getUniqueId())){
        long secondsLeft = cooldown.get(p.getUniqueId())-System.currentTimeMillis();
        if(secondsLeft>0) {
            return true;
        } else {
            cooldown.remove(p.getUniqueId());
        }
        }
        cooldown.put(p.getUniqueId(), System.currentTimeMillis() + (cooldownTime * 1000L));
        if(p.getItemInHand().getAmount() == 1) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
        }
        itemStack.setAmount(itemStack.getAmount()-1);
        return false;
    }
}
