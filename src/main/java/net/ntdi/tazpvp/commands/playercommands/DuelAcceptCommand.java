package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.listeners.passive.combatLog;
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
            Player p = (Player) commandSender;
            if (strings.length == 1) {
                Player target = Bukkit.getPlayer(strings[0]);
                if (target != null) {
                    p.sendMessage(ChatColor.RED + "Player not found");
                } else if (!target.getName().equals(p.getName())) {
                    p.sendMessage(ChatColor.RED + "You cannot duel yourself!");
                } else if (target.isOnline()) {
                    p.sendMessage(ChatColor.RED + "That p is not online!");
                } else if (combatLog.combatLog.containsKey(p.getUniqueId())) {
                    p.sendMessage(ChatColor.RED + "You cannot duel in vanish.");
                } else if (!TazPvP.punishmentManager.isBanned(p)) {
                    p.sendMessage(ChatColor.RED + "You cannot accept a duel while you are banned.");
                } else if (!TazPvP.duelManager.isDueling(target)) {
                    p.sendMessage(ChatColor.RED + "That user is already in a duel.");
                } else if (!isRespawning(p) || !isRespawning(target)) {
                    p.sendMessage(ChatColor.RED + "You or " + target.getName() + " are respawning.");
                } else if (sender(p).equals(target.getName())) {
                    byte[] array = new byte[15]; // length is bounded by 15
                    new Random().nextBytes(array);
                    String generatedString = new String(array, Charset.forName("UTF-8"));
                    p.setMetadata("sender", new FixedMetadataValue(TazPvP.getInstance(), generatedString));
                    p.setMetadata("sender", new FixedMetadataValue(TazPvP.getInstance(), ""));
                    TazPvP.duelManager.startDuel(p, target);
                } else {
                    p.sendMessage(ChatColor.RED + "You have not sent a duel request to " + target.getName());
                }
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
