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
public class AppealCommand implements CommandExecutor {

    private final TextComponent msg;

    public AppealCommand(){
        msg = new TextComponent("§bClick here to appeal your punishment.");
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fThis link goes to the Taznanium discord server.\nOnce in, create a ticket in §b#support").create()));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.discord.gg/6Zrb7hSGFC"));
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }

        if(player != null){
            player.sendMessage(ChatColor.DARK_AQUA + "Please create a ticket in our discord server.\n");
            player.spigot().sendMessage(msg);
        }

        return false;
    }
}
