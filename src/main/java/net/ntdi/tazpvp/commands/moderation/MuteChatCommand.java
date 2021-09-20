package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.mutechat")){
            ChatUtils.chatMuted = !ChatUtils.chatMuted;
            Bukkit.broadcastMessage(ChatColor.RED + ((ChatUtils.chatMuted) ? "Chat has been muted." : "Chat has been unmuted."));
        }
        return true;
    }
}
