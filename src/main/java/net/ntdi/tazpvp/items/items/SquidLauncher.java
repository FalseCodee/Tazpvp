package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.ClickableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.UUID;

public class SquidLauncher extends ClickableItem {
    public SquidLauncher() {
        super(Items.SQUID_LAUNCHER, 7);
    }

    public final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }
            if (p.getWorld().getName().equals("arena")) {

                Snowball ball = p.launchProjectile(Snowball.class);

                ball.setMetadata("IsSquid", new FixedMetadataValue(TazPvP.getInstance(), true));

                Location loc = (Location) p.getWorld();
            } else {
                p.sendMessage(ChatColor.RED + "You cannot use this here.");
            }
        return false;
    }



}
