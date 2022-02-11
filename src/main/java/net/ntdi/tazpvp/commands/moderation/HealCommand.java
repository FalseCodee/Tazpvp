package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p != null && p.hasPermission("tazpvp.heal")) {
                if (args.length < 1) {
                    p.setHealth(p.getMaxHealth());
                    p.setFoodLevel(20);
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                } else if (args.length == 1) {
                    Player targetP = Bukkit.getServer().getPlayer(args[0]);
                    targetP.setHealth(targetP.getMaxHealth());
                    targetP.setFoodLevel(20);
                    for (PotionEffect effect : targetP.getActivePotionEffects()) {
                        targetP.removePotionEffect(effect.getType());
                    }
                }
            }
        }
        return true;
    }
}
