package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    //Clears the chat for everyone except staff
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.clearchat")){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(!p.hasPermission("staff.clearchat")){
                    for(int i = 0; i < 100; i++){
                        p.sendMessage("");
                    }
                } else {
                    p.sendMessage(ChatColor.DARK_AQUA + "Chat has been cleared, but you are immune!");
                }
            }
        }

        return true;
    }
}
