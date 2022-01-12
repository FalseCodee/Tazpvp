package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class DuelAcceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                Player target = Bukkit.getPlayer(strings[0]);
                if (target != null) {
                    if (!target.getName().equals(player.getName())) {
                        if (target.isOnline()) {
                            if (sender(player).equals(target.getName())) {
                                player.setMetadata("sender", new FixedMetadataValue(TazPvP.getInstance(), ""));
                                player.sendMessage(ChatColor.GREEN + "You have accepted the duel request from " + target.getName());
                                target.sendMessage(ChatColor.GREEN + "The duel request has been accepted by " + player.getName());
                                TazPvP.duelManager.startDuel(player, target);
                            }
                        }
                    }
                }
            } else {
                return false;
            }
        }

        return true;
    }

    public String sender(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("sender");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asString();
        }
        return "";
    }
}
