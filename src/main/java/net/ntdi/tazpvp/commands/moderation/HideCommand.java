package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }
        if(player != null && player.hasPermission("staff.hide")) {
            TazPvP.staffManager.toggleHidden(player);
            player.sendMessage(ChatColor.DARK_AQUA + "You turned Hidden: " + ChatColor.WHITE + ((TazPvP.staffManager.hiddenToggled(player)) ? "On" : "Off"));
            if(TazPvP.staffManager.hiddenToggled(player)) {
                TazPvP.statsManager.setGroup(player, TazPvP.permissions.getPrimaryGroup(player));
                for(World w : Bukkit.getServer().getWorlds()){
                    TazPvP.permissions.playerRemoveGroup(w.getName(), player, TazPvP.permissions.getPrimaryGroup(player));
                }
                TazPvP.permissions.playerRemoveGroup(null, player, TazPvP.permissions.getPrimaryGroup(player));
                TazPvP.permissions.playerAdd(player, "staff.hide");

            } else {
                TazPvP.permissions.playerAddGroup(player, TazPvP.statsManager.getGroup(player));
                TazPvP.permissions.playerRemove(player, "staff.hide");
            }
            for(Player players : Bukkit.getOnlinePlayers()) {
                TazPvP.getInstance().initScoreboard(players);
            }

        } else {
            return false;
        }
        return true;
    }
}
