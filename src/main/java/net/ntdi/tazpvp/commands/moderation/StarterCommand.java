package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StarterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("staff.starter")){
                if(args.length > 0){
                    Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    PlayerUtils.equipStarter(target);
                    TazPvP.statsManager.setMoney(target, 0);
                    TazPvP.statsManager.setLevel(target, 0);
                    TazPvP.statsManager.setPoints(target, 0);
                    TazPvP.statsManager.setCredits(target, 0);
                    target.setExp(0L);

                } else {
                    p.sendMessage(ChatColor.RED + "Player not found.");
                }
            }else{
                PlayerUtils.equipStarter(p);
            }
        }
        }
        return true;
    }
}
