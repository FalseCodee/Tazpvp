package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class BowListener implements Listener {
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    int cooldownTime = 3;
    ItemStack arrow = new ItemStack(Material.ARROW, 1);
    @EventHandler
    public void onShoot(org.bukkit.event.entity.EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            PlayerInventory inv = p.getInventory();
            if (TazPvP.statsManager.getRebirths(p) > 0) {
                inv.addItem(arrow);
                p.sendMessage("gh");
            }
            if(cooldowns.containsKey(p.getName())) {
                long secondsLeft = ((cooldowns.get(p.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                if (secondsLeft > 0) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "Slow down! " + ChatColor.WHITE + secondsLeft + "s");
                    return;
                }
            }
            cooldowns.put(p.getName(), System.currentTimeMillis());
        }
    }
}