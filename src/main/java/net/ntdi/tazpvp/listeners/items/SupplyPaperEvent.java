package net.ntdi.tazpvp.listeners.items;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SupplyPaperEvent implements Listener {

    @EventHandler
    public void onRightCLick(PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Supply Paper")){
                if (event.getPlayer().getItemInHand().getAmount() == 1){
                    event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                } else {
                    event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount()-1);
                }

                Player p = (Player) event.getPlayer();
                p.sendMessage(ChatColor.GREEN + "Claimed Supply Paper!");
                p.sendMessage("+100 Coins");
                p.sendMessage("+1 point");

                TazPvP.statsManager.addMoney(p, 100);
                TazPvP.statsManager.addPoints(p, 1);

            }
        }
    }

}
