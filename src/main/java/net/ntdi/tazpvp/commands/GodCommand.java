package net.ntdi.tazpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

//        if (sender instanceof Player){
//            Player p = (Player) sender;
//            if(args.length > 0){
//                Player target = Bukkit.getPlayer(args[0]);
//                if(target != null) {
//                    // Argument is an online player
//                    if (target.spigot().isInvulnerable()){
//                        target.spigot().setInvulnerable(false);
//                        target.sendMessage(ChatColor.LIGHT_PURPLE + "God mode disabled");
//                        p.sendMessage(ChatColor.LIGHT_PURPLE + "Disabled " + target + "'s god mode");
//                    }else{
//                        target.spigot().setInvulnerable(true);
//                        target.sendMessage(ChatColor.LIGHT_PURPLE+ "God mode enabled");
//                        p.sendMessage(ChatColor.LIGHT_PURPLE + "Enabled " + target + "'s god mode");
//                    }
//                } else {
//                    if (p.spigot().isInvulnerable()){
//                        p.spigot().setInvulnerable(false);
//                        p.sendMessage(ChatColor.LIGHT_PURPLE + "God mode disabled");
//                    }else{
//                        p.spigot().setInvulnerable(true);
//                        p.sendMessage(ChatColor.LIGHT_PURPLE + "God mode enabled");
//                    }
//                }
//            } else {
//                if (p.isInvulnerable()){
//                    p.setInvulnerable(false);
//                    p.sendMessage(ChatColor.LIGHT_PURPLE + "God mode disabled");
//                }else{
//                    p.setInvulnerable(true);
//                    p.sendMessage(ChatColor.LIGHT_PURPLE + "God mode enabled");
//                }
//            }
//        }

        return true;
    }

}

