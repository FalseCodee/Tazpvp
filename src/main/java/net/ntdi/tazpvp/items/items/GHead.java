package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GHead extends ConsumableItem {

    public GHead() {
        super(Items.GHEAD, 3);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }
        if(p.getHealth() <= 16) {
            p.setHealth(p.getHealth() + 4);
        } else {
            p.setHealth(20);
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*60, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*3, 0));
        return false;
    }
}
