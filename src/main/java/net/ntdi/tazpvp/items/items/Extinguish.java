package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Extinguish extends ConsumableItem {
    public Extinguish() {
        super(Items.EXTINGUISH);
    }

    @Override
    public void execute(Player p, ItemStack itemStack) {
        super.execute(p, itemStack);
        p.setFireTicks(0);
    }
}
