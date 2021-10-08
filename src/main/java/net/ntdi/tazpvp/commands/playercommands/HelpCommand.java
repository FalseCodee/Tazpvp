package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
//            p.sendMessage(ChatColor.AQUA + "-----" + ChatColor.DARK_AQUA + "-----" + ChatColor.AQUA + "-----" + ChatColor.RED + "TAZPVP" + ChatColor.AQUA + "-----" + ChatColor.DARK_AQUA + "-----" + ChatColor.AQUA + "-----");
//            p.sendMessage(ChatColor.GRAY + "Gain EXP from crates, dealing damage, advertising,");
//            p.sendMessage(ChatColor.GRAY + "achievements, and killing mobs or players.");
//            p.sendMessage(ChatColor.GRAY + "");
//            p.sendMessage(ChatColor.GOLD + "Get money from killing, achievements, crates, mining,");
//            p.sendMessage(ChatColor.GOLD + "and fishing.");
//            p.sendMessage(ChatColor.GRAY + "");
//            p.sendMessage(ChatColor.AQUA + "Recieve points from leveling and achievements.");
//            p.sendMessage(ChatColor.DARK_AQUA + "Upgrade gear at the Upgrade NPC in spawn.");
//            p.sendMessage(ChatColor.GRAY + "");
//            p.sendMessage(ChatColor.GREEN + "PAGE [1/2]" + ChatColor.GRAY + "/help2 for the second page");

            for(String s : Objects.requireNonNull(Utils.readFile(TazPvP.helpFile))) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        }else{
            System.out.println("Only players can execute /help");
        }
        return true;
    }
}