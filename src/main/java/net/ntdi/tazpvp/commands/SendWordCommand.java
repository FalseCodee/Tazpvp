package net.ntdi.tazpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
// what is this command
public class SendWordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                p.sendMessage(ChatColor.LIGHT_PURPLE + args[0]);
            }else{
                return false;
            }

        }else{
            System.out.println("Only players can execute /sendword");
        }
        return true;
    }
}
