package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Butter extends ConsumableItem {

    public Butter() {
        super(Items.BUTTER);
    }

    @Override
    public void execute(Player p, ItemStack itemStack) {
        super.execute(p, itemStack);
        if(p.getHealth() <= 16) {
            p.setHealth(p.getHealth() + 4);
        } else {
            p.setHealth(20);
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*60, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*3, 0));
        if(p.getItemInHand().getAmount() == 1) {
            p.getItemInHand().setAmount(0);
        }
    }
}
