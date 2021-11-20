package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.guis.upgrades.EnchantInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class RefundManager implements Listener {

    public void refundEnchant(Player p, ItemStack item) {

        for(EnchantInfo enchantInfo : EnchantInfo.values()) {
            if(item.getEnchantments().size() <= 0) {
                return;
            }
            else if(item.getEnchantments().containsKey(enchantInfo.ench)){
                TazPvP.statsManager.addPoints(p, enchantInfo.cost * item.getEnchantments().get(enchantInfo.ench));
                item.removeEnchantment(enchantInfo.ench);

            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (TazPvP.RefundItem.contains(p)) {
            e.setCancelled(true);
            if (e.getMessage().equalsIgnoreCase("go")) {
                if (p.getItemInHand().getEnchantments().size() > 0 && p.getItemInHand().getType() != null) {
                    TazPvP.statsManager.addCredits(p, -10);
                    refundEnchant(p, p.getInventory().getItemInHand());
                    p.sendMessage(ChatColor.GREEN + "Item Refunded!");
                    TazPvP.RefundItem.remove(p);
                } else {
                    p.sendMessage(ChatColor.RED + "You must have an enchant to refund!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "Refunding canceled!");
                TazPvP.RefundItem.remove(p);
            }
        }

    }

}
