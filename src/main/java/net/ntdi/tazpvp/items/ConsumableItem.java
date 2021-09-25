package net.ntdi.tazpvp.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ConsumableItem extends Item{
    public ConsumableItem(Items item) {
        super(item);
    }
    public void execute(Player p, ItemStack itemStack) {
        itemStack.setAmount(itemStack.getAmount()-1);
    }
}
