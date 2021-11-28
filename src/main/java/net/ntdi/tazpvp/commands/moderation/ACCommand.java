package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.gui.guis.GUIVulcan;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.ntdi.tazpvp.gui.GUIManager;

public class ACCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("tazpvp.ban")){
                if (args.length == 1){
                    if (Bukkit.getPlayer(args[0]) != null){
                        Player target = Bukkit.getPlayer(args[0]);

                        new GUIVulcan(player, target);
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
