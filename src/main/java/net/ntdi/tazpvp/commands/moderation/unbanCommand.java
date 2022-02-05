package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class unbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        OfflinePlayer banned = Bukkit.getPlayer(args[0]);
        if (banned != null) {
            if(player != null && player.hasPermission("staff.ban")){
                if(TazPvP.punishmentManager.isBanned(banned)){
                    TazPvP.punishmentManager.removeBan(banned);
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "UNBAN " + ChatColor.RED + "You just unbanned " + ChatColor.WHITE + banned.getName());
                    if (banned.isOnline()) {
                        banned.getPlayer().sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                        banned.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "UNBAN" + ChatColor.GRAY + " You've been unbanned by " + ChatColor.WHITE + player.getName());
                        banned.getPlayer().sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                TazPvP.getInstance().initScoreboard(banned.getPlayer());
                            }
                        }.runTaskLater(TazPvP.getInstance(), 20L);
                    }
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    String pexcmd = "pex user " + banned.getName() + " group remove banned";
                    Bukkit.dispatchCommand(console, pexcmd);
                    if(BanCommand.bannedRunnables.containsKey(banned.getUniqueId())) {
                        BanCommand.bannedRunnables.get(banned.getUniqueId()).cancel();
                        BanCommand.bannedRunnables.remove(banned.getUniqueId());
                    }


                } else {
                    player.sendMessage(ChatColor.GREEN + "You can't unban someone that's not banned!");
                }
            }
        }


        return true;
    }
}
