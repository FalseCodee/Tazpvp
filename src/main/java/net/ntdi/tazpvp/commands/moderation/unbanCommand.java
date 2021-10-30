package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

public class unbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        Player banned = Bukkit.getPlayer(args[0]);
        if (banned != null) {
            if(player != null && player.hasPermission("staff.ban")){
                if(TazPvP.punishmentManager.isBanned(banned)){
                    TazPvP.punishmentManager.removeBan(banned);
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "UNBAN " + ChatColor.RED + "You just unbanned " + ChatColor.WHITE + banned.getName());
                    banned.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    banned.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "UNBANNED" + ChatColor.GRAY + " You've been unbanned by " + ChatColor.WHITE + player.getName());
                    banned.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    String pexcmd = "pex user " + banned.getName() + " group remove banned";
                    Bukkit.dispatchCommand(console, pexcmd);
                    if(BanCommand.bannedRunnables.containsKey(banned.getUniqueId())) {
                        BanCommand.bannedRunnables.get(banned.getUniqueId()).cancel();
                        BanCommand.bannedRunnables.remove(banned.getUniqueId());
                    }

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            TazPvP.getInstance().initScoreboard(banned);
                        }
                    }.runTaskLater(TazPvP.getInstance(), 20L);

                } else {
                    player.sendMessage(ChatColor.GREEN + "You cant unban someone thats not banned!");
                }
            }
        }


        return true;
    }
}
