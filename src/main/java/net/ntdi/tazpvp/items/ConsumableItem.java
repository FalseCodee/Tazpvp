package net.ntdi.tazpvp.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ConsumableItem extends Item{
    public ConsumableItem(Items item) {
        super(item);
    }
    public void execute(Player p, ItemStack itemStack) {
        if(p.getItemInHand().getAmount() == 1) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
        }
        itemStack.setAmount(itemStack.getAmount()-1);
    }
}
