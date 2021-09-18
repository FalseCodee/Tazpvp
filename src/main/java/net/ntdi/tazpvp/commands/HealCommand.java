package net.ntdi.tazpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    // Argument is an online player
                    target.setFoodLevel(20);
                    target.setHealth(20);
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "You have been Healed");
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Healed " + target);
                } else {
                    p.setFoodLevel(20);
                    p.setHealth(20);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "You have been Healed");
                }
            } else {
                p.setFoodLevel(20);
                p.setHealth(20);
                p.sendMessage(ChatColor.LIGHT_PURPLE + "You have been Healed");
            }
        } return true;
    }
}
