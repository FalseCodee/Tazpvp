package net.ntdi.tazpvp.items.items;

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
        int cooldownTime = 10;

        if(cooldowns.containsKey(p.getUniqueId())) {
            long secondsLeft = cooldowns.get(p.getUniqueId())-System.currentTimeMillis();
            if(secondsLeft>0) {
                // Still cooling down
                p.sendMessage(ChatColor.RED + "Please Wait "+ secondsLeft/1000 +" seconds!");
            } else {
                cooldowns.remove(p.getUniqueId());
            }
        } else {
            // No cooldown found or cooldown has expired, save new cooldown
            cooldowns.put(p.getUniqueId(), System.currentTimeMillis() + (cooldownTime * 1000));

            Snowball ball = p.launchProjectile(Snowball.class);

            ball.setMetadata("IsSquid", new FixedMetadataValue(TazPvP.getInstance(), true));

            Location loc = (Location) p.getWorld();

            LivingEntity squid = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.SQUID);
            squid.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "KABOOM");
            squid.setCustomNameVisible(true);

            ball.setPassenger(squid);
//            squid.spigot().isInvulnerable()
        }

    }



}
