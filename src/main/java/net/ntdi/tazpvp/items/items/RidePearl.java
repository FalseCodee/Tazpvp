package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RidePearl extends ConsumableItem {

    public RidePearl() { super(Items.RIDEPEARL, 10); }

    @Override
    public boolean execute(Player p, ItemStack itemStack){
        if(super.execute(p, itemStack)) {
            return true;
        }

        EnderPearl pearl = p.launchProjectile(EnderPearl.class);

        pearl.setPassenger(p);
        pearl.setCustomName(ChatColor.RED + "WE FLYIN");
        pearl.setCustomNameVisible(true);

        return true;
    }

}

