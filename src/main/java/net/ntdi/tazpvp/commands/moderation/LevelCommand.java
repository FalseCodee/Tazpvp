package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODO: Test this
public class LevelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("op")){
            if(args.length == 0){
                player.sendMessage(ChatColor.RED + "Usage: /levels <Player> [add|remove|reset] [integer]");
            } else {
                OfflinePlayer offlinePlayer = null;
                for(OfflinePlayer offlineP : Bukkit.getOfflinePlayers()){
                    if(offlineP.getName().equalsIgnoreCase(args[0])){
                        offlinePlayer = offlineP;
                        break;
                    }
                }
                if(offlinePlayer != null){

                    if(TazPvP.statsManager.statsFile.contains(offlinePlayer.getUniqueId().toString())){
                        if(args.length == 1){
                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + " has " + TazPvP.statsManager.getLevel((Player) offlinePlayer) + " levels.");
                        } else {
                            switch(args[1].toLowerCase()){
                                case "reset":
                                    TazPvP.statsManager.setLevel((Player) offlinePlayer, 0);
                                    player.sendMessage(ChatColor.RED + "Reset " + offlinePlayer.getName() + "'s level to 0.");
                                    break;
                                case "set":
                                    if(args.length == 3){
                                        try{
                                            TazPvP.statsManager.setLevel((Player) offlinePlayer, Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + "set " + offlinePlayer.getName() + "'s level to "+ args[2] +".");
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
                                            TazPvP.statsManager.addLevels((Player) offlinePlayer, Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + "now has "+ args[2] +" levels.");
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
                                            TazPvP.statsManager.addLevels((Player) offlinePlayer, -Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + "now has "+ args[2] +" levels.");
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
                    return false;
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }
        return false;
    }
}
