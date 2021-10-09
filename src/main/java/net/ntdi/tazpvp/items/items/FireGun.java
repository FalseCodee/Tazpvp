package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class FireGun extends ConsumableItem {
    public FireGun(Items item, int cooldownTime) {
        super(Items.FIREGUN, 0);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack){

        if (super.execute(p, itemStack)) {
            return true;
        }

        Fireball fireball = p.launchProjectile(Fireball.class);
        fireball.setMetadata("IsFireball", new FixedMetadataValue(TazPvP.getInstance(), true));

        fireball.setPassenger(p);

        return true;
    }
}
