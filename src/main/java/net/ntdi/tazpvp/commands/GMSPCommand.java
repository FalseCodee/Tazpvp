package net.ntdi.tazpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMSPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    // Argument is an online player
                    target.setGameMode(GameMode.SPECTATOR);
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "You are now in Spectator");
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "You have put " + args[0] + " in Spectator");
                } else {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "You are now in Spectator");
                }
            } else {
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ChatColor.LIGHT_PURPLE + "You are now in Spectator");
            }
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage(ChatColor.LIGHT_PURPLE + "You are now in Spectator");
        }

        return true;
    }

}

