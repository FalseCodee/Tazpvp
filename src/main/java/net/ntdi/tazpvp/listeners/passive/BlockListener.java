package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class BlockListener implements Listener {

    private final Random rand = new Random();

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPlaceBlock(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            if (TazPvP.AllowBlocks){
                if (!TazPvP.punishmentManager.isBanned(event.getPlayer())) {
                    Material blockType = event.getBlockPlaced().getType();
                    ArrayList<Material> unreq = new ArrayList<>();
                    unreq.add(Material.COAL_ORE);
                    unreq.add(Material.IRON_ORE);
                    unreq.add(Material.GOLD_ORE);
                    unreq.add(Material.LAPIS_ORE);
                    unreq.add(Material.DIAMOND_ORE);
                    unreq.add(Material.EMERALD_ORE);

                    if (!unreq.contains(blockType)) {
                        if (blockType == Material.WOOD) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    event.getBlockPlaced().setType(Material.AIR);
                                }
                            }.runTaskLater(TazPvP.getInstance(), 200L);
                        } else if (blockType == Material.SOIL) {
                            event.setCancelled(true);
                        } else {
                            if (TazPvP.perkManager.getSaveBlocks(event.getPlayer())){
                                if (rand.nextInt(4) == 3){
                                    ItemStack item = new ItemStack(event.getBlock().getType(), 1, event.getBlock().getData());
                                    item.getItemMeta().setDisplayName(event.getPlayer().getItemInHand().getItemMeta().getDisplayName());
                                    event.getPlayer().getInventory().addItem(item);
                                }
                            }
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    event.getBlockPlaced().setType(Material.AIR);
                                }
                            }.runTaskLater(TazPvP.getInstance(), 200L);
                        }
                    } else {
                        event.setCancelled(true);
                    }
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "server is restarting soon! Blocks can no longer be placed");
            }
        }
    }
}
