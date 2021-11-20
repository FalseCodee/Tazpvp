package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CreditCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        } else if (sender instanceof ConsoleCommandSender){
            OfflinePlayer offlinePlayer = PlayerUtils.getPlayer(args[0]);

            if(offlinePlayer != null){

                if(TazPvP.statsManager.statsFile.contains(offlinePlayer.getUniqueId().toString())){
                    if(args.length == 1){
                        player.sendMessage(ChatColor.RED + offlinePlayer.getName() + " has $" + TazPvP.statsManager.getMoney(offlinePlayer) + ".");
                    } else {
                        switch(args[1].toLowerCase()){
                            case "reset":
                                TazPvP.statsManager.setCredits(offlinePlayer, 0);
                                System.out.println(ChatColor.RED + "Reset " + offlinePlayer.getName() + "'s credits to 0.");
                                break;
                            case "set":
                                if(args.length == 3){
                                    try{
                                        TazPvP.statsManager.setCredits(offlinePlayer, Integer.parseInt(args[2]));
                                        System.out.println(ChatColor.RED + "set " + offlinePlayer.getName() + "'s credit amount to "+ args[2] +".");
                                    } catch(NumberFormatException e){
                                        System.out.println(ChatColor.RED + "Use integers only.");
                                    }
                                } else {
                                    System.out.println(ChatColor.RED + "Provide an integer value.");
                                }
                                break;
                            case "add":
                                if(args.length == 3){
                                    try{
                                        TazPvP.statsManager.addCredits(offlinePlayer, Integer.parseInt(args[2]));
                                        System.out.println(ChatColor.RED + offlinePlayer.getName() + " now has "+ TazPvP.statsManager.getCredits(offlinePlayer) +" credits.");
                                    } catch(NumberFormatException e){
                                        System.out.println(ChatColor.RED + "Use integers only.");
                                    }
                                } else {
                                     System.out.println(ChatColor.RED + "Provide an integer value.");
                                }
                                break;
                            case "remove":
                                if(args.length == 3){
                                    try{
                                        TazPvP.statsManager.addCredits(offlinePlayer, -Integer.parseInt(args[2]));
                                        System.out.println(ChatColor.RED + offlinePlayer.getName() + " now has "+ TazPvP.statsManager.getCredits(offlinePlayer) +" credits.");
                                    } catch(NumberFormatException e){
                                        System.out.println(ChatColor.RED + "Use integers only.");
                                    }
                                } else {
                                    System.out.println(ChatColor.RED + "Provide an integer value.");
                                }
                                break;
                        }
                    }
                } else {
                    System.out.println(ChatColor.RED + "Player not found in stats.yml");
                }
                return true;
            } else {
                System.out.println(ChatColor.RED + "Player not found.");
            }
        }

        if(player != null && player.hasPermission("staff.money")){
            if(args.length == 0){
                return false;
            } else {
                OfflinePlayer offlinePlayer = PlayerUtils.getPlayer(args[0]);

                if(offlinePlayer != null){

                    if(TazPvP.statsManager.statsFile.contains(offlinePlayer.getUniqueId().toString())){
                        if(args.length == 1){
                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + " has $" + TazPvP.statsManager.getMoney(offlinePlayer) + ".");
                        } else {
                            switch(args[1].toLowerCase()){
                                case "reset":
                                    TazPvP.statsManager.setCredits(offlinePlayer, 0);
                                    player.sendMessage(ChatColor.RED + "Reset " + offlinePlayer.getName() + "'s credits to 0.");
                                    break;
                                case "set":
                                    if(args.length == 3){
                                        try{
                                            TazPvP.statsManager.setCredits(offlinePlayer, Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + "set " + offlinePlayer.getName() + "'s credit amount to "+ args[2] +".");
                                        } catch(NumberFormatException e){
                                            player.sendMessage(ChatColor.RED + "Use integers only.");
                                        }
                                    } else {
                                        player.sendMessage(ChatColor.RED + "Provide an integer value.");
                                    }
                                    break;
                                case "add":
                                    if(args.length == 3){
                                        try{
                                            TazPvP.statsManager.addCredits(offlinePlayer, Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + " now has "+ TazPvP.statsManager.getCredits(offlinePlayer) +" credits.");
                                        } catch(NumberFormatException e){
                                            player.sendMessage(ChatColor.RED + "Use integers only.");
                                        }
                                    } else {
                                        player.sendMessage(ChatColor.RED + "Provide an integer value.");
                                    }
                                    break;
                                case "remove":
                                    if(args.length == 3){
                                        try{
                                            TazPvP.statsManager.addCredits(offlinePlayer, -Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + " now has "+ TazPvP.statsManager.getCredits(offlinePlayer) +" credits.");
                                        } catch(NumberFormatException e){
                                            player.sendMessage(ChatColor.RED + "Use integers only.");
                                        }
                                    } else {
                                        player.sendMessage(ChatColor.RED + "Provide an integer value.");
                                    }
                                    break;
                            }
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Player not found in stats.yml");
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }
        return true;
    }
}
