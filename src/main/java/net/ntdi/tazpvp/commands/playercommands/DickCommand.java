package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class DickCommand implements CommandExecutor {

    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            int cooldownTime = 60; // Get number of seconds from wherever you want
            if(cooldowns.containsKey(p.getName())) {
                long secondsLeft = ((cooldowns.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(secondsLeft>0) {
                    // Still cooling down
                    p.sendMessage("You cant use your dick for another "+ secondsLeft +" seconds!");
                    return true;
                }
            }
            // No cooldown found or cooldown has expired, save new cooldown
            cooldowns.put(p.getName(), System.currentTimeMillis());
            // Do Command Here
            p.setVelocity(p.getVelocity().setY(1));
            p.sendMessage("You are now dicked!");
            return true;
        }
        return true;
    }
}
