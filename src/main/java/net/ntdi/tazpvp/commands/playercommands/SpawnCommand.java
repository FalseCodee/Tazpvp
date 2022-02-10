package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static net.ntdi.tazpvp.utils.PlayerUtils.delayChangeGamemode;

public class SpawnCommand implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof  Player){
            Player p = (Player) sender;
            if (p.hasPermission("staff.spawn")) {
                p.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 50, 0.5, 180, 0));
            } else {
                if (!TazPvP.duelManager.isDueling(p)) {
                    if (!p.getWorld().getName().equalsIgnoreCase("grind")) {
                        p.sendMessage(ChatColor.DARK_AQUA + "You will be teleported to spawn in " + ChatColor.AQUA + "3 seconds. " + ChatColor.DARK_AQUA + "Do not move." );
                        p.setMetadata("spawnering", new FixedMetadataValue(TazPvP.getInstance(), true));
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (isSpawnering(p)) {
                                    p.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 50, 0.5, 180, 0));
                                    p.setMetadata("spawnering", new FixedMetadataValue(TazPvP.getInstance(), false));
                                    p.sendMessage(ChatColor.DARK_AQUA + "Teleportation complete.");
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                }
                            }
                        }.runTaskLater(TazPvP.getInstance(), 3 * 20);

                    } else {
                        p.sendMessage(ChatColor.RED + "You cannot teleport while in the cave.");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You cannot teleport while dueling.");
                }
            }
        }
        return true;
    }

    @EventHandler
    public void NoMoveNo(PlayerMoveEvent e){
        if(isSpawnering(e.getPlayer())){
            e.getPlayer().setMetadata("spawnering", new FixedMetadataValue(TazPvP.getInstance(), false));
            e.getPlayer().sendMessage(ChatColor.RED + "Teleportation cancelled, you moved.");
        }
    }

    @EventHandler
    public void Damage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (isSpawnering(p)) {
                p.setMetadata("spawnering", new FixedMetadataValue(TazPvP.getInstance(), false));
                p.sendMessage(ChatColor.GREEN + "Detected Damage! Canceling teleportation!");
            }
        }
    }

    public boolean isSpawnering(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("spawnering");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }
}
