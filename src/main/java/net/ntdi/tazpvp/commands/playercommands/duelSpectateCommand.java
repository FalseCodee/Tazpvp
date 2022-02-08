package net.ntdi.tazpvp.commands.playercommands;

import javafx.scene.control.TableColumn;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.managers.Duels.DuelManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class duelSpectateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 1) {
                Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                if (!TazPvP.duelManager.isDueling(p)) {
                    if (p.getPlayer().getWorld().getName().equals("spawn")) {
                        if (TazPvP.duelManager.isDueling(targetPlayer)) {
                            if (!DuelManager.spectating.contains(p)) {
                                p.teleport(targetPlayer.getLocation());
                                TazPvP.duelManager.startSpectating(p);
                            } else {
                                p.sendMessage(ChatColor.RED + "You are already spectating.");
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "This player is not in a duel.");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Please go to spawn before spectating.");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You cannot use this in a duel.");
                }
            } else {
                if (!TazPvP.duelManager.isDueling(p)) {
                    if (DuelManager.spectating.contains(p)) {
                        TazPvP.duelManager.stopSpectating(false, p);
                    } else {
                        return false;
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You cannot use this in a duel.");
                }
            }
        }
        return true;
    }
}
