package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.Item;
import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class CrateListener implements Listener {

    @EventHandler
    public void onPlayerCrateEvent(PlayerInteractEvent event){
        Player p = event.getPlayer();

        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.CHEST) {
            event.setCancelled(true);

            Block block = event.getClickedBlock();
            block.setType(Material.AIR);

            // totally not stolen from stack overflow :))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
            Random r = new Random();
            int low = 50;
            int high = 100;
            int result = r.nextInt(high-low) + low;



            TazPvP.statsManager.addMoney(p, result);
            p.giveExp(20);

            p.sendMessage(ChatColor.GOLD + "+" + result + " Coins");
            p.sendMessage(ChatColor.AQUA + "+20 EXP");
            return;

        }
        if(p.getItemInHand().getType() != Material.AIR && p.getItemInHand() != null && p.getItemInHand().getType() != Material.FISHING_ROD) {
            for(Items items : Items.values()) {
                if(items.display.equals(p.getItemInHand().getItemMeta().getDisplayName())) {
                    for(Item i : ItemManager.items) {
                        if(i.enumeration.equals(items)) {
                            i.execute(p, p.getItemInHand());
                            return;
                        }
                    }
                    return;
                }
            }
            }


    }

}
