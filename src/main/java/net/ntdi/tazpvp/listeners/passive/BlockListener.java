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

    private final Random random = new Random();

    private int next() {
        if (random.nextBoolean()) {
            return 1;
        } else {
            return 2;
        }
    }

    @EventHandler
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
                            if (TazPvP.perkManager.getSaveBlocks(event.getPlayer())){
                                if (next() == 2){
                                    event.getPlayer().getInventory().addItem(event.getPlayer().getItemInHand());
                                }
                            }
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    event.getBlockPlaced().setType(Material.AIR);
                                }
                            }.runTaskLater(TazPvP.getInstance(), 200L);
                        } else if (blockType == Material.SOIL) {
                            event.setCancelled(true);
                        } else {
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
