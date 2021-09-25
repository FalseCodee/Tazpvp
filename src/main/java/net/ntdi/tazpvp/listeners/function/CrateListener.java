package net.ntdi.tazpvp.listeners.function;

import net.md_5.bungee.api.chat.ClickEvent;
import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.BlockIterator;

import java.util.Random;

public class CrateListener implements Listener {

    @EventHandler
    public void onPlayerCrateEvent(PlayerInteractEvent event){
        Player p = (Player) event.getPlayer();

        if (event.getClickedBlock().getType() == Material.CHEST) {
            p.sendMessage(ChatColor.RED + "chezt openz");
            event.setCancelled(true);
            Location location = event.getClickedBlock().getLocation();

            Block block = event.getClickedBlock();
            block.setType(Material.AIR);

            // totally not stolen from stack overflow :))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
            Random r = new Random();
            int low = 50;
            int high = 100;
            int result = r.nextInt(high-low) + low;

            TazPvP.statsManager.addMoney(p, result);
            p.giveExp(20);

            p.sendMessage(ChatColor.GOLD + "+" + result + "$");
            p.sendMessage(ChatColor.AQUA + "+20");

        }

    }

}
