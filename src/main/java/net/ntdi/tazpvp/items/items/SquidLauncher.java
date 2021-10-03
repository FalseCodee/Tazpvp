package net.ntdi.tazpvp.items.items;

import net.milkbowl.vault.chat.Chat;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.ClickableItem;
import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.UUID;

public class SquidLauncher extends ClickableItem {
    public SquidLauncher() {
        super(Items.SQUID_LAUNCHER);
    }

    public HashMap<UUID, Long> cooldowns = new HashMap<>();

    @Override
    public void execute(Player p, ItemStack itemStack) {
        super.execute(p, itemStack);
        int cooldownTime = 7;

        if(cooldowns.containsKey(p.getUniqueId())) {
            long secondsLeft = cooldowns.get(p.getUniqueId())-System.currentTimeMillis();
            if(secondsLeft>0) {
                // Still cooling down
            } else {
                cooldowns.remove(p.getUniqueId());
            }
        } else {
            if (p.getWorld().getName().equals("arena")) {
                cooldowns.put(p.getUniqueId(), System.currentTimeMillis() + (cooldownTime * 1000));

                Snowball ball = p.launchProjectile(Snowball.class);

                ball.setMetadata("IsSquid", new FixedMetadataValue(TazPvP.getInstance(), true));

                Location loc = (Location) p.getWorld();
            } else {
                p.sendMessage(ChatColor.RED + "You cannot use this here.");
            }
        }

    }



}
