package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.ClickableItem;
import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class SquidLauncher extends ClickableItem {
    public SquidLauncher() {
        super(Items.SQUID_LAUNCHER);
    }

    @Override
    public void execute(Player p, ItemStack itemStack) {
        super.execute(p, itemStack);

        Snowball ball = p.launchProjectile(Snowball.class);

        ball.setMetadata("IsSquid", new FixedMetadataValue(TazPvP.getInstance(), true));

    }
}
