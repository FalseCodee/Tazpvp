package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Help2Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage(ChatColor.AQUA + "-----" + ChatColor.DARK_AQUA + "-----" + ChatColor.AQUA + "-----" + ChatColor.RED + "TAZPVP" + ChatColor.AQUA + "-----" + ChatColor.DARK_AQUA + "-----" + ChatColor.AQUA + "-----");
            p.sendMessage(ChatColor.WHITE + "/report " + ChatColor.GRAY + "Report rule breakers.");
            p.sendMessage(ChatColor.WHITE + "/apply " + ChatColor.GRAY + "Apply for a staff position.");
            p.sendMessage(ChatColor.WHITE + "/spawn " + ChatColor.GRAY + "Teleport back to the spawn.");
            p.sendMessage(ChatColor.WHITE + "/discord " + ChatColor.GRAY + "Join the community.");
            p.sendMessage(ChatColor.WHITE + "/report " + ChatColor.GRAY + "Report rule breakers.");
            p.sendMessage(ChatColor.WHITE + "/duel " + ChatColor.GRAY + "Battle versus another player.");
            p.sendMessage(ChatColor.GRAY + "");
            p.sendMessage(ChatColor.GREEN + "PAGE [2/2] " + ChatColor.GRAY + "/help to go back");
        }else{
            System.out.println("Only players can execute /help");
        }
        return true;
    }
}