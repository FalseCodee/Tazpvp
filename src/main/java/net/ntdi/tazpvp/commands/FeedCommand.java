package net.ntdi.tazpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (sender instanceof Player){
            Player p = (Player) sender;

            if(args.length > 0){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    // Argument is an online player
                    target.setFoodLevel(20);
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "You have been fed");
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Fed " + target);
                } else {
                    p.setFoodLevel(20);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "You have been fed");
                }
            } else {
                p.setFoodLevel(20);
                p.sendMessage(ChatColor.LIGHT_PURPLE + "You have been fed");
            }

        }

        return true;
    }

}
