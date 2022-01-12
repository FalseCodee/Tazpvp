package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Fireballz extends ConsumableItem {
    public Fireballz() {
        super(Items.FIREBALL, 1);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }

        org.bukkit.entity.Fireball fireball = p.launchProjectile(org.bukkit.entity.Fireball.class);
        fireball.setCustomName(ChatColor.RED + "Arabic person");
        fireball.setCustomNameVisible(true);
        fireball.setBounce(true);

        return true;
    }
}
