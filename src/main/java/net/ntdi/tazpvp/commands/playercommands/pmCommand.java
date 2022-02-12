package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class pmCommand implements CommandExecutor, Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        if (TazPvP.newPm.contains(p)) {
            TazPvP.newPm.remove(p);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length >= 2) {
                Player target = p.getServer().getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(ChatColor.RED + "This player does not exist.");
                } else if (target.getName().equals(p.getName())) {
                    p.sendMessage(ChatColor.RED + "You cannot PM yourself!");
                } else if (TazPvP.punishmentManager.isMuted(p)) {
                    p.sendMessage(ChatColor.RED + "You cannot PM while muted.");
                } else {
                    String msg = StringUtils.buildString(args, 1);
                    target.sendMessage( ChatColor.DARK_AQUA + "From " + ChatColor.AQUA + p.getName() + ": "  + ChatColor.GRAY + msg);
                    p.sendMessage(ChatColor.DARK_AQUA + "To " + ChatColor.AQUA + (args[0]) + ": " + ChatColor.GRAY + msg);
                    target.playSound(target.getLocation(), Sound.SHEEP_SHEAR, 1, 1);
                    if (!TazPvP.newPm.contains(p)) {
                        target.sendMessage(ChatColor.AQUA + " To respond to this private message, type " + ChatColor.GRAY + "/PM <player> <message>");
                        TazPvP.newPm.add(p);
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
