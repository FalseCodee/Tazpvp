package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.ntdi.tazpvp.TazPvP;

import java.awt.*;

public class VotekickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player && sender.hasPermission("rank.votekick")) {

            Player p = (Player) sender;
            Player votekicked = Bukkit.getPlayer(args[0]);
            if(args.length == 0) {
                return false;
            } else if (args.length == 1 && votekicked != null){
                if (TazPvP.VoteOn == false){
                    TazPvP.getInstance().initVoteKick(votekicked);

                }else{
                    p.sendMessage("There is already a vote going on!");
                }
            } else {
                return false;
            }

        }
        return true;
    }
}
