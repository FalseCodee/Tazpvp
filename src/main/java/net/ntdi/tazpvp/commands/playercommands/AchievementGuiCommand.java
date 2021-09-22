package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.gui.guis.GUIAchievement;
import net.ntdi.tazpvp.gui.guis.GUIDailyReward;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AchievementGuiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof  Player){
            player = (Player) sender;
        }

        if(player != null) {
            new GUIAchievement(player);
        }
        return true;
    }
}
