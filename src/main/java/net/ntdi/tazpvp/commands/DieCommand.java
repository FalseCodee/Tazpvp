package net.ntdi.tazpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DieCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (sender instanceof Player){
            Player p = (Player) sender;

            if(args.length > 0){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    // Argument is an online player
                    target.setHealth(0.0);
                    target.sendMessage(ChatColor.GREEN + "You " + ChatColor.LIGHT_PURPLE + "been " + ChatColor.DARK_PURPLE + "dead");
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Killed " + args[0]);
                } else {
                    p.setHealth(0.0);
                    p.sendMessage(ChatColor.GREEN + "You " + ChatColor.LIGHT_PURPLE + "been " + ChatColor.DARK_PURPLE + "dead");
                }
            } else {
                p.setHealth(0.0);
                p.sendMessage(ChatColor.GREEN + "You " + ChatColor.LIGHT_PURPLE + "been " + ChatColor.DARK_PURPLE + "dead");
            }
        }

        return true;
    }

}
