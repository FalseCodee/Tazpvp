package net.ntdi.tazpvp.listeners.function;

import jdk.javadoc.internal.doclint.HtmlTag;
import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player p = (Player) event.getPlayer();

        if(p.getGameMode() == GameMode.SURVIVAL){
            Block b = event.getBlock();
            ArrayList<Material> blocks = new ArrayList<>();
            blocks.add(Material.COAL_ORE);
            blocks.add(Material.IRON_ORE);
            blocks.add(Material.LAPIS_ORE);
            blocks.add(Material.GOLD_ORE);
            blocks.add(Material.DIAMOND_ORE);
            blocks.add(Material.EMERALD_ORE);

            if(!blocks.contains(b)){
                event.setCancelled(true);
            }else{
                b.setType(Material.BEDROCK);
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        b.setType(b.getType());
                    }
                }.runTaskTimer(TazPvP.getInstance(), 200L, 0L);
            }

        }
    }
}
