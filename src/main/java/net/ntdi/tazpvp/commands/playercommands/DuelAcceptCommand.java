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

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

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
                            if (player.getWorld().getName().equals("spawn")) {
                                if (!TazPvP.punishmentManager.isBanned(player)) {
                                    if (!TazPvP.duelManager.isDueling(target)) {
                                        if (!isRespawning(player) || !isRespawning(target)) {
                                            if (sender(player).equals(target.getName())) {
                                                byte[] array = new byte[15]; // length is bounded by 15
                                                new Random().nextBytes(array);
                                                String generatedString = new String(array, Charset.forName("UTF-8"));
                                                player.setMetadata("sender", new FixedMetadataValue(TazPvP.getInstance(), generatedString));
                                                player.setMetadata("sender", new FixedMetadataValue(TazPvP.getInstance(), ""));
                                                TazPvP.duelManager.startDuel(player, target);
                                            } else {
                                                player.sendMessage(ChatColor.RED + "You have not sent a duel request to " + target.getName());
                                            }
                                        } else {
                                            player.sendMessage(ChatColor.RED + "You or " + target.getName() + " are respawning.");
                                        }
                                    } else {
                                        player.sendMessage(ChatColor.RED + "That user is already in a duel.");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You cannot accept a duel while you are banned.");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "You cannot accept a duel while you are not in the spawn world.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "That player is not online!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You cannot duel yourself!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found");
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

    public boolean isRespawning(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("respawning");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }
}
