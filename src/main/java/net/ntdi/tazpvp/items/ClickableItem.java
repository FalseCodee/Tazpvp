package net.ntdi.tazpvp.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ClickableItem extends Item{
    public ClickableItem(Items item) {
        super(item);
    }

    @Override
    public void execute(Player p, ItemStack itemStack) {

    }
}
