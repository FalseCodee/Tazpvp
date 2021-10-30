package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteyesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {

            Player p = (Player) sender;
            if (TazPvP.VoteOn){
                if (!TazPvP.voteYes.contains(p) && !TazPvP.voteNo.contains(p)){
                    TazPvP.voteYes.add(p);
                    p.sendMessage("Succesfully voted YES");
                } else{
                    p.sendMessage("You already voted!");
                }
            }else {
                p.sendMessage("there is no vote to vote no for!");
            }
            }
        return true;
    }
}
