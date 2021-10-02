package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player p = (Player) event.getPlayer();

        if(p.getGameMode() == GameMode.SURVIVAL){
            Block b = event.getBlock();
        }
    }
}
