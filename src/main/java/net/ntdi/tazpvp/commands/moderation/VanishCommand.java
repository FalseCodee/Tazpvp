package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor, Listener {
    public final List<Player> vanishList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.vanish")) {
            if(!vanishList.contains(player)) {
                vanishList.add(player);
                PlayerUtils.hidePlayer(player);
                player.sendMessage(ChatColor.RED + "You have entered vanish mode.");
            } else {
                vanishList.remove(player);
                PlayerUtils.showPlayer(player);
                player.sendMessage(ChatColor.RED + "You are no longer in vanish mode.");

            }
        } else {
            return false;
        }
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        for(Player player : vanishList)
            PlayerUtils.hidePlayer(player);

    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        vanishList.remove(event.getPlayer());
    }
}
