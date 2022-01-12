package net.ntdi.tazpvp.commands.playercommands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class AdCommand implements CommandExecutor {

    final String ad = "/ad Tazpvp &f✗ &b1.8 PVP &f✗ &3Grind &f✗ &bKeepInv &f✗ &3Duel";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            TextComponent copy = new TextComponent("\n" + ChatColor.YELLOW + "  Click " + ChatColor.GOLD + "" + ChatColor.BOLD + "Here" + ChatColor.YELLOW + " to copy the advertisement for this server.\n");
            copy.setClickEvent(new net.md_5.bungee.api.chat.ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ad));
            p.spigot().sendMessage(copy);
        }

        return true;
    }


}
