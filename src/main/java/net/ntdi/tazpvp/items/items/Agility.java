package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Agility extends ConsumableItem {
    public Agility() {
        super(Items.AGILITY);
    }

    @Override
    public void execute(Player p, ItemStack itemStack) {
        super.execute(p, itemStack);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*5, 0));
    }
}
