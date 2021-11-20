package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.guis.upgrades.EnchantInfo;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class RefundManager implements Listener {

    public void refundEnchant(Player p, ItemStack item) {

        for(EnchantInfo enchantInfo : EnchantInfo.values()) {
            if(item.getEnchantments().size() <= 0) {
                return;
            }
            else if(item.getEnchantments().containsKey(enchantInfo.ench)){
                item.removeEnchantment(enchantInfo.ench);
                TazPvP.statsManager.addPoints(p, enchantInfo.cost * item.getEnchantments().get(enchantInfo.ench));
            }
        }

        /*item.getEnchantments().forEach((enchant, level) -> {
            System.out.println(enchant.getName());
            System.out.println(level);
            System.out.println(item.getEnchantments());
           if (enchant == Enchantment.DAMAGE_ALL) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 4);
           } else if (enchant == Enchantment.KNOCKBACK) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 4);
           } else if (enchant == Enchantment.DAMAGE_UNDEAD) {
                item.removeEnchantment(enchant);
                TazPvP.statsManager.addPoints(p, level * 2);
            } else if (enchant == Enchantment.DIG_SPEED) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 3);
           } else if (enchant == Enchantment.LOOT_BONUS_BLOCKS) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 6);
           } else if (enchant == Enchantment.SILK_TOUCH) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 7);
           } else if (enchant == Enchantment.LURE) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 3);
           } else if (enchant == Enchantment.LUCK) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 2);
           } else if (enchant == Enchantment.LOOT_BONUS_MOBS) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 6);
           } else if (enchant == Enchantment.ARROW_DAMAGE) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level);
           } else if (enchant == Enchantment.ARROW_KNOCKBACK) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 4);
           } else if (enchant == Enchantment.ARROW_INFINITE) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 12);
           } else if (enchant == Enchantment.PROTECTION_ENVIRONMENTAL) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 4);
           } else if (enchant == Enchantment.PROTECTION_FALL) {
               item.removeEnchantment(enchant);
               TazPvP.statsManager.addPoints(p, level * 4);
           }
        }); */
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
