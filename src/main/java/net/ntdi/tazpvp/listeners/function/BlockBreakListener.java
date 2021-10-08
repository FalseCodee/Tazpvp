package net.ntdi.tazpvp.listeners.function;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player p = event.getPlayer();

        if(p.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
//            Block b = event.getBlock();
//
//            ArrayList<Material> blocks = new ArrayList<>();
//            blocks.add(Material.COAL_ORE);
//            blocks.add(Material.IRON_ORE);
//            blocks.add(Material.LAPIS_ORE);
//            blocks.add(Material.GOLD_ORE);
//            blocks.add(Material.DIAMOND_ORE);
//            blocks.add(Material.EMERALD_ORE);
//
//            if(!blocks.contains(b.getType())){
//                event.setCancelled(true);
//            }else if (blocks.contains(b.getType())){
//                event.setCancelled(true);
//                p.getInventory().addItem(new ItemStack(b.getType()));
//                b.setType(Material.BEDROCK);
//                new BukkitRunnable() {
//                    @Override
//                    public void run() {
//                        b.setType(Material.COAL_ORE);
//                    }
//                }.runTaskTimer(TazPvP.getInstance(), 200L, 0L);


        }
    }
}
