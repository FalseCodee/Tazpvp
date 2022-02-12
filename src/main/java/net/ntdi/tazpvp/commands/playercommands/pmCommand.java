package net.ntdi.tazpvp.commands.playercommands;

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
    int num = 0;
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        int num = 0;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 2) {
                Player target = p.getServer().getPlayer(args[0]);
                target.sendMessage( ChatColor.DARK_AQUA + "From " + ChatColor.RED + p.getName() + ": "  + ChatColor.GRAY + (args[1]));
                p.sendMessage(ChatColor.DARK_AQUA + "To " + ChatColor.RED + (args[0]) + ": " + ChatColor.GRAY + (args[1]));
                target.playSound(target.getLocation(), Sound.SHEEP_SHEAR, 1, 1);
                if (num < 1) {
                    target.sendMessage(ChatColor.AQUA + " To respond to this private message, type '/PM <player> <message>'");
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
