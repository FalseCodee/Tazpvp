package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Coal;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player p = event.getPlayer();

        if(p.getWorld().getName().equals("arena") && p.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
            return;
        }

        if(p.getGameMode() == GameMode.SURVIVAL && p.getWorld().getName().equals("grind")) {
            event.setCancelled(true);
            Block b = event.getBlock();
            Material mat = b.getType();
            b.setType(Material.BEDROCK);
           if (TazPvP.blocks.contains(mat)) {
                if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH) && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                    if (mat == Material.COAL_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.COAL, 2));
                        respawnOre(Material.COAL_ORE, 120L, b);
                    } else if (mat == Material.IRON_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
                        respawnOre(Material.IRON_ORE, 180L, b);
                    } else if (mat == Material.LAPIS_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.INK_SACK, 2, (short) 4));
                        respawnOre(Material.LAPIS_ORE, 260L, b);
                    } else if (mat == Material.GOLD_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
                        respawnOre(Material.GOLD_ORE, 350L, b);
                    } else if (mat == Material.DIAMOND_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
                        respawnOre(Material.DIAMOND_ORE, 400L, b);
                    } else if (mat == Material.EMERALD_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.EMERALD, 2));
                        respawnOre(Material.EMERALD_ORE, 450L, b);
                    }
                } else if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
                    if (mat == Material.COAL_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.COAL_ORE, 1));
                        respawnOre(Material.COAL_ORE, 120L, b);
                    } else if (mat == Material.IRON_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
                        respawnOre(Material.IRON_ORE, 180L, b);
                    } else if (mat == Material.LAPIS_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.INK_SACK, 1, (short) 4));
                        respawnOre(Material.LAPIS_ORE, 260L, b);
                    } else if (mat == Material.GOLD_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
                        respawnOre(Material.GOLD_ORE, 350L, b);
                    } else if (mat == Material.DIAMOND_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                        respawnOre(Material.DIAMOND_ORE, 400L, b);
                    } else if (mat == Material.EMERALD_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.EMERALD, 1));
                        respawnOre(Material.EMERALD_ORE, 450L, b);
                    }
                } else if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                    p.getInventory().addItem(new ItemStack(mat, 2));
                    // big pp
                } else {
                    p.getInventory().addItem(new ItemStack(mat));
                }
                if (TazPvP.perkManager.getHaste(p)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 1, false));
                }
           }
        }
    }
    public void respawnOre(Material mat, Long dur, Block b){
        new BukkitRunnable() {
            @Override
            public void run() {
                b.setType(mat);
            }
        }.runTaskLater(TazPvP.getInstance(), dur);
    }
}

