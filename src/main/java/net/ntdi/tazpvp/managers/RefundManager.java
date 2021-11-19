package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class RefundManager {

    public void refundEnchant(Player p, ItemStack item) {
        item.getEnchantments().forEach((enchant, level) -> {
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
        });
    }


}
