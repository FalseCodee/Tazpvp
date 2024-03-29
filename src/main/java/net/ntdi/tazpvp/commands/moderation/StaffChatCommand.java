package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.staffchat")) {
            if(args.length == 0) {
                TazPvP.staffManager.toggleStaffChat(player);
                player.sendMessage(ChatColor.DARK_AQUA + "Staff Chat toggled " + ChatColor.WHITE + ((TazPvP.staffManager.staffChatToggled(player)) ? "On" : "Off"));
            } else {
                TazPvP.staffManager.sendStaffChat(player, StringUtils.buildString(args, 0));
            }
        } else {
            return false;
        }
        return true;
    }
}
