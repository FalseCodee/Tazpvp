package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gmcCommand implements CommandExecutor{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        readCommand((Player) sender, commandLabel);
        return false;
    }

    public void readCommand(Player player, String command) {
        if(command.equalsIgnoreCase("gmsp")) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("spectator");
        }
        else if(command.equalsIgnoreCase("gmc")) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("gmc");
        }
        else if(command.equalsIgnoreCase("gms")) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("gms");
        }
    }
}