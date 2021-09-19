package net.ntdi.tazpvp.commands.playercommands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODO: Test this
public class ApplyCommand implements CommandExecutor {
    private final TextComponent msg;
    public ApplyCommand(){
        msg = new TextComponent("§bClick here to apply for staff");
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fThis link goes to our discord server.\nOnce in, go to §b#information").create()));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.discord.gg/6Zrb7hSGFC"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }

        if(player != null){
            player.sendMessage(ChatColor.DARK_AQUA + "Thank you for showing interest in our staff team!");
            player.sendMessage(ChatColor.DARK_AQUA + "Before you apply, make sure you follow all of these requirements:");
            player.sendMessage(ChatColor.AQUA + " - Must be over the age of 13.");
            player.sendMessage(ChatColor.AQUA + " - Must have played on the server for at least 5 hours.");
            player.sendMessage(ChatColor.AQUA + " - Must have had previous staff experience.");
            player.sendMessage(ChatColor.AQUA + " - Must be able to participate in Voice Calls.");
            player.sendMessage(ChatColor.RED + "FAILURE TO FOLLOW THESE REQUIREMENTS WILL BE AN INSTANT DENIAL.\n");
            player.spigot().sendMessage(msg);
        }
        return false;
    }
}
