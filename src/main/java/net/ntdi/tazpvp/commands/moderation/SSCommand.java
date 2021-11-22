package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.gui.guis.GUIGIFTORBUY;
import net.ntdi.tazpvp.gui.guis.SSGui;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SSCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
            if (player.hasPermission("staff.ban")) {
                if (args.length == 1) {
                    org.bukkit.entity.Player target = player.getServer().getPlayer(args[0]);
                    if (target != null) {
                        //target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 255, true, false));
                        target.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 59, 0.5));
                        target.openInventory(new SSGui(target).inventory);
                    }
                }
            }
        }

        return true;
    }
}
