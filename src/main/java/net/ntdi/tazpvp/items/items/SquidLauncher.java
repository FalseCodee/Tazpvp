package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.ClickableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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
                if (!TazPvP.punishmentManager.isBanned(p)) {
                    ItemStack tsl = p.getInventory().getItemInHand();

                    if (tsl.getDurability() >= 32) {
                        p.getInventory().setItemInHand(new ItemStack(Material.AIR));
                        p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                    } else {
                        tsl.setDurability((short) (tsl.getDurability() + 2));

                        Snowball ball = p.launchProjectile(Snowball.class);

                        ball.setMetadata("IsSquid", new FixedMetadataValue(TazPvP.getInstance(), true));
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Hackers arent cool!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You cannot use this here.");
            }
        return false;
    }



}
