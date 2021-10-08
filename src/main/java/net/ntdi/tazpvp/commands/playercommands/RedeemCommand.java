package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.gui.guis.GUIDailyReward;
import net.ntdi.tazpvp.gui.guis.upgrades.GUIMainScreen;
import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedeemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof  Player){
            player = (Player) sender;
        }

        if(player != null) {
            new GUIDailyReward(player);
        }
        return true;
    }
}
