package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Butter extends ConsumableItem {

    public Butter() {
        super(Items.BUTTER, 3);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }
        if(p.getHealth() <= 18) {
            p.setHealth(p.getHealth() + 2);
        } else {
            p.setHealth(20);
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*5, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*5, 0));
        p.playSound(p.getLocation(), Sound.BURP, 1, 1);
        return false;
    }
}
