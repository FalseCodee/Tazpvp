package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("op")){
            if(args.length == 0){
                return false;
            } else {
                OfflinePlayer offlinePlayer = PlayerUtils.getPlayer(args[0]);

                if(offlinePlayer != null){

                    if(TazPvP.statsManager.statsFile.contains(offlinePlayer.getUniqueId().toString())){
                        if(args.length == 1){
                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + " has " + TazPvP.statsManager.getPoints(offlinePlayer) + " points.");
                        } else {
                            switch(args[1].toLowerCase()){
                                case "reset":
                                    TazPvP.statsManager.setPoints(offlinePlayer, 0);
                                    player.sendMessage(ChatColor.RED + "Reset " + offlinePlayer.getName() + "'s points to 0.");
                                    break;
                                case "set":
                                    if(args.length == 3){
                                        try{
                                            TazPvP.statsManager.setPoints(offlinePlayer, Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + "set " + offlinePlayer.getName() + "'s points to "+ args[2] +".");
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
                                            TazPvP.statsManager.addPoints(offlinePlayer, Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + " now has "+ TazPvP.statsManager.getPoints(offlinePlayer) +" points.");
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
                                            TazPvP.statsManager.addPoints(offlinePlayer, -Integer.parseInt(args[2]));
                                            player.sendMessage(ChatColor.RED + offlinePlayer.getName() + "now has "+ TazPvP.statsManager.getPoints(offlinePlayer) +" points.");
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
