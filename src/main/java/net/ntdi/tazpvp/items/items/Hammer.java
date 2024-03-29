package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ClickableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Hammer extends ClickableItem {
    public Hammer() {
        super(Items.HAMMER, 0);
    }

    //public final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }

        for(Entity e : p.getNearbyEntities(100,100,100)) {
            if(e instanceof Player) {
                Player target = (Player) e;

                e.getWorld().strikeLightning(e.getLocation());
                e.getWorld().strikeLightning(e.getLocation());
                e.getWorld().strikeLightning(e.getLocation());
                e.getWorld().strikeLightning(e.getLocation());
                e.getWorld().strikeLightning(e.getLocation());
                e.getWorld().strikeLightning(e.getLocation());
                e.getWorld().strikeLightning(e.getLocation());
                e.setVelocity(new Vector(0,1,0));

            }
        }

        return false;
    }



}
