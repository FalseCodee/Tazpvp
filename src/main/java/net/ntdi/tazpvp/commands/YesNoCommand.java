package net.ntdi.tazpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class YesNoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("yes")){
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Yes indeed!");
                }else if(args[0].equalsIgnoreCase("no")) {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "No, today is not the day");
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            System.out.println("Only players can execute /yesno");
        }
        return true;
    }
}
