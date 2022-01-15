package net.ntdi.tazpvp.commands.playercommands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.chat.Chat;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.managers.Duels.DuelManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class DuelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                } else if(target.getName().equals(player.getName())) {
                    sender.sendMessage(ChatColor.RED + "You can't duel yourself!");
                } else if (TazPvP.punishmentManager.isBanned(player)) {
                    sender.sendMessage(ChatColor.RED + "You are banned!");
                } if (target.isOnline() && !new DuelManager().isDueling(target) && !new DuelManager().isDueling(player)) {
                    // start duel
                    target.setMetadata("sender", new FixedMetadataValue(TazPvP.getInstance(), player.getName()));
                    TextComponent Accept = new TextComponent(" " + "CLICK HERE" + ChatColor.GRAY+ "to accept.");
                    Accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duelaccept " + player.getName()));
                    Accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent(ChatColor.GREEN + "Click to accept duel request")}));

                    player.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    player.sendMessage(ChatColor.YELLOW + " You challenged " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " to a duel.");
                    player.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

                    target.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    target.sendMessage("  " + ChatColor.GOLD + player.getName() + ChatColor.YELLOW + " has challenged you to a duel.");
                    target.spigot().sendMessage(Accept);
                    target.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /duel <player>");
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
