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
            for(String s : Objects.requireNonNull(Utils.readFile(TazPvP.helpFile))) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        }else{
            System.out.println("Only players can execute /help");
        }
        return true;
    }
}