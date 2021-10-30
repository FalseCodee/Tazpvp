package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class LevFeather extends ConsumableItem {

    public LevFeather() { super(Items.LEVFEATHER, 10); }

    @Override
    public boolean execute(Player p, ItemStack itemStack){
        if(super.execute(p, itemStack)) {
            return true;
        }

        p.sendMessage(ChatColor.YELLOW + "Wooooosh!");
        p.setVelocity(new Vector(0, 2, 0));

        return true;
    }

}

