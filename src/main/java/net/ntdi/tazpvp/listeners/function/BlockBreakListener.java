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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player p = event.getPlayer();

        if(p.getWorld().getName().equals("arena") || p.getWorld().getName().equals("duel") && p.getGameMode() == GameMode.SURVIVAL) {
            Block b = event.getBlock();
            Material mat = b.getType();
            if (isPlayerPlaced(b)) {
                event.setCancelled(false);
                return;
            } else {
                event.setCancelled(true);
            }
        }

        if(p.getGameMode() == GameMode.SURVIVAL && p.getWorld().getName().equals("grind")) {
            event.setCancelled(true);
            Block b = event.getBlock();
            Material mat = b.getType();
           if (TazPvP.blocks.contains(mat)) {
                if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH) && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                    if (mat == Material.COAL_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.COAL, 2));
                        respawnOre(Material.COAL_ORE, 120L, b);
                        return;
                    } else if (mat == Material.IRON_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
                        respawnOre(Material.IRON_ORE, 180L, b);
                        return;
                    } else if (mat == Material.LAPIS_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.INK_SACK, 2, (short) 4));
                        respawnOre(Material.LAPIS_ORE, 260L, b);
                        return;
                    } else if (mat == Material.GOLD_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
                        respawnOre(Material.GOLD_ORE, 350L, b);
                        return;
                    } else if (mat == Material.DIAMOND_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
                        respawnOre(Material.DIAMOND_ORE, 400L, b);
                        return;
                    } else if (mat == Material.EMERALD_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.EMERALD, 2));
                        respawnOre(Material.EMERALD_ORE, 450L, b);
                        return;
                    }
                } else if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
                    if (mat == Material.COAL_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.COAL_ORE, 1));
                        respawnOre(Material.COAL_ORE, 120L, b);
                        return;
                    } else if (mat == Material.IRON_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
                        respawnOre(Material.IRON_ORE, 180L, b);
                        return;
                    } else if (mat == Material.LAPIS_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.INK_SACK, 1, (short) 4));
                        respawnOre(Material.LAPIS_ORE, 260L, b);
                        return;
                    } else if (mat == Material.GOLD_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
                        respawnOre(Material.GOLD_ORE, 350L, b);
                        return;
                    } else if (mat == Material.DIAMOND_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                        respawnOre(Material.DIAMOND_ORE, 400L, b);
                        return;
                    } else if (mat == Material.EMERALD_ORE) {
                        p.getInventory().addItem(new ItemStack(Material.EMERALD, 1));
                        respawnOre(Material.EMERALD_ORE, 450L, b);
                        return;
                    }
                } else if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                    p.getInventory().addItem(new ItemStack(mat, 2));
                    // big pp
                } else {
                    p.getInventory().addItem(new ItemStack(mat));
                    if (mat == Material.COAL_ORE) {
                        respawnOre(Material.COAL_ORE, 120L, b);
                        return;
                    } else if (mat == Material.IRON_ORE) {
                        respawnOre(Material.IRON_ORE, 180L, b);
                        return;
                    } else if (mat == Material.LAPIS_ORE) {
                        respawnOre(Material.LAPIS_ORE, 260L, b);
                        return;
                    } else if (mat == Material.GOLD_ORE) {
                        respawnOre(Material.GOLD_ORE, 350L, b);
                        return;
                    } else if (mat == Material.DIAMOND_ORE) {
                        respawnOre(Material.DIAMOND_ORE, 400L, b);
                        return;
                    } else if (mat == Material.EMERALD_ORE) {
                        respawnOre(Material.EMERALD_ORE, 450L, b);
                        return;
                    }
                }
                if (TazPvP.perkManager.getHaste(p)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 1, false));
                }
           }
        }
    }
    public void respawnOre(Material mat, Long dur, Block b){
        b.setType(Material.BEDROCK);
        new BukkitRunnable() {
            @Override
            public void run() {
                b.setType(mat);
            }
        }.runTaskLater(TazPvP.getInstance(), dur);
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent e){
        if (e.getPlayer().getGameMode() == GameMode.SURVIVAL && e.getPlayer().getWorld().getName().equalsIgnoreCase("arena")){
            Player p = e.getPlayer();
            Block b = e.getBlock();
            b.setMetadata("PlayerPlaced", new FixedMetadataValue(TazPvP.getInstance(), true));
        }
    }

    public boolean isPlayerPlaced(Block b){
        List<MetadataValue> metaDataValues = b.getMetadata("PlayerPlaced");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }
}

