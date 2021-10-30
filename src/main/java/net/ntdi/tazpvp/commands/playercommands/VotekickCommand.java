package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.ntdi.tazpvp.TazPvP;

public class VotekickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player && sender.hasPermission("rank.votekick")) {

            Player p = (Player) sender;
            if(args.length == 0) {
                return false;
            } else if (args.length == 1){
                Player votekicked = Bukkit.getPlayer(args[0]);
                if(votekicked == null) return false;
                if (!TazPvP.VoteOn){
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
