package net.ntdi.tazpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
// what is this command
public class SendMessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);
            if(args.length == 2){
                if(target != null) {
                    // Argument is an online player
                    target.sendMessage(ChatColor.LIGHT_PURPLE + args[1]);
                } else {
                    return false;
                }

            }else{
                return false;
            }


        }else{
            System.out.println("Only players can execute /sendword");
        }
        return true;
    }
}
