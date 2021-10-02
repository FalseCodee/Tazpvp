package net.ntdi.tazpvp.commands.playercommands;

import me.clip.placeholderapi.PlaceholderAPI;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PapiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        String msg = PlaceholderAPI.setPlaceholders(p, "x: %player_x%");

        return true;
    }
}