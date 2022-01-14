package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldGuard implements Listener {

    // lava-fire
    @EventHandler
    public void lavaFire(BlockIgniteEvent event) {
        event.setCancelled(true);
    }

    // Water-flow Lava-flow dirt-to-grass
    @EventHandler
    public void waterFlow(BlockPhysicsEvent event) {
        if (event.getBlock().getWorld().getName().equals("arena") || event.getBlock().getWorld().getName().equals("duel")) {
            Material mat = event.getBlock().getType();
            if (mat == Material.STATIONARY_WATER || mat == Material.WATER || mat == Material.STATIONARY_LAVA || mat == Material.LAVA || mat == Material.DIRT || mat == Material.GRASS) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void waterFromTo(BlockFromToEvent event) {
        if (event.getBlock().getWorld().getName().equals("arena") || event.getBlock().getWorld().getName().equals("duel")) {
            Block block = event.getBlock();
            if (block.getType() == Material.STATIONARY_WATER || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.LAVA || block.getType() == Material.DIRT || block.getType() == Material.GRASS) {
                event.setCancelled(true);
            }
        }
    }

    //leaf-decay
    @EventHandler
    public void leafDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    //weather-lock
    @EventHandler
    public void weatherLock(WeatherChangeEvent event) {
        if (event.getWorld().getName().equals("arena") || event.getWorld().getName().equals("spawn") || event.getWorld().getName().equals("duel")) {
            event.setCancelled(true);
        }
    }

    //mob-spwaning
    @EventHandler
    public void mobSpawn(EntitySpawnEvent event) {
        if (event.getEntity().getWorld().getName().equals("arena")) {
            EntityType mat = event.getEntity().getType();
            if (event.getEntity().getType() != EntityType.ARROW && mat != EntityType.SQUID && mat != EntityType.SNOWBALL && mat != EntityType.DROPPED_ITEM) {
                event.setCancelled(true);
            }
        } else if (event.getEntity().getWorld().getName().equals("spawn")) {
            event.setCancelled(true);
        } else if (event.getEntity().getWorld().getName().equals("duel")) {
            event.setCancelled(true);
        }
    }

    //exp-drops
    @EventHandler
    public void expDrop(PlayerDeathEvent event) {
        event.setDroppedExp(0);
    }

    //vine-growth and fire-spread
    @EventHandler
    public void vineGrowth(BlockGrowEvent event) {
        event.setCancelled(true);
    }

    //feed
    @EventHandler
    public void feed(FoodLevelChangeEvent event) {
        if (event.getEntity().getWorld().getName().equals("spawn")) {
            event.setCancelled(true);
        }
    }

    //item-pickup
    @EventHandler
    public void itemPickup(PlayerPickupItemEvent event) {
        if (event.getPlayer().getWorld().getName().equals("spawn") || event.getPlayer().getWorld().getName().equals("duel")) {
            event.setCancelled(true);
        }
    }

    //build
    @EventHandler
    public void build(BlockPlaceEvent event) {
        if (event.getPlayer().getWorld().getName().equals("spawn") || event.getPlayer().getWorld().getName().equals("grind") || event.getPlayer().getWorld().getName().equals("duel")) {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void deniedRights(BlockPhysicsEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.TORCH) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void blockHit(EntityDamageByEntityEvent event) {
        if (event.getEntity().getWorld().getName().equals("spawn")) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld().getName().equals("spawn")) {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Material mat = event.getClickedBlock().getType();
                    if (mat == Material.TRAP_DOOR || mat == Material.LEVER || mat == Material.SPRUCE_FENCE_GATE) {
                        event.setCancelled(true);
                    }
                }
            }
        } else if (event.getPlayer().getWorld().getName().equals("arena")) {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Material mat = event.getClickedBlock().getType();
                    if (mat == Material.TRAP_DOOR || mat == Material.SPRUCE_DOOR || mat == Material.SPRUCE_FENCE_GATE) {                     
                        event.setCancelled(true);
                    }
                }
            }
        } else if (event.getPlayer().getWorld().getName().equals("duel")) {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Material mat = event.getClickedBlock().getType();
                    if (mat == Material.LEVER) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
