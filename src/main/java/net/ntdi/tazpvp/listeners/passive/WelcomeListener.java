package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.commands.moderation.BanCommand;
import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

public class WelcomeListener<SerializedOfflinePlayer> implements Listener {

    public TazPvP plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player p = event.getPlayer();
        p.sendMessage(ChatColor.AQUA + "");
        p.sendMessage(ChatColor.DARK_GRAY + "  ▍  " + ChatColor.RED + ChatColor.BOLD + "TAZPVP " + ChatColor.WHITE + "Legacy PvP");
        p.sendMessage(ChatColor.DARK_GRAY + "  ▍  " + ChatColor.GRAY + "Type /discord");
        p.sendMessage(ChatColor.DARK_GRAY + "  ▍  " + ChatColor.GRAY + "Server IP: tazpvp.net");
        p.sendMessage(ChatColor.AQUA + "");

        if (!p.hasPlayedBefore()){
            PlayerUtils.equipStarter(p);
        }

        World wrld = Bukkit.getWorld(TazPvP.configFile.getString("spawn.world"));

        Location loc = new Location(wrld, 0.5, 51, 0.5, 180, 0);

        p.teleport(loc);

        for(Scoreboard sb : TazPvP.statsManager.scoreboards.values()) {
            TazPvP.statsManager.getTeam(p, sb).addEntry(p.getName());
        }
        TazPvP.statsManager.initScoreboard(p);
            if(!TazPvP.staffManager.hiddenToggled(p)){
                TazPvP.statsManager.setGroup(p, TazPvP.permissions.getPrimaryGroup(p));
            } else {
                p.sendMessage(ChatColor.DARK_AQUA+ "You are currently hidden!");
            }
        if(TazPvP.punishmentManager.isMuted(p) &&
                System.currentTimeMillis()-TazPvP.punishmentManager.getMuteTime(p) >= TazPvP.punishmentManager.getMuteDuration(p)){
            TazPvP.punishmentManager.removeMute(p);
            p.sendMessage(ChatColor.RED+"You have been unmuted.");
        }
        if(TazPvP.punishmentManager.isBanned(p) && System.currentTimeMillis()-TazPvP.punishmentManager.getBanTime(p) >= TazPvP.punishmentManager.getBanDuration(p)){
            TazPvP.punishmentManager.removeBan(p);
            p.sendMessage(ChatColor.RED+"You have been unbanned.");
        } else if (TazPvP.punishmentManager.isBanned(p)) {
            p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            p.sendMessage(ChatColor.WHITE + "");
            p.sendMessage(ChatColor.RED + ""+ ChatColor.BOLD + " BANNED" + ChatColor.RED + " You currently have" + ChatColor.WHITE + (((TazPvP.punishmentManager.getBanDuration(p) - (System.currentTimeMillis()-TazPvP.punishmentManager.getBanTime(p))) / 60000) + 1) + ChatColor.RED + " minutes left");
            p.sendMessage(ChatColor.WHITE + "");
            p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            BukkitRunnable runnable = BanCommand.getBanRunnable(p);
            runnable.runTaskTimer(TazPvP.getInstance(), 0L, 20L);
            BanCommand.bannedRunnables.put(p.getUniqueId(), runnable);
        }


        boolean hasPlayed = p.hasPlayedBefore();

        if (hasPlayed) {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + p.getName());
        } else {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + p.getName());
            Player player = event.getPlayer();
            PlayerUtils.equipStarter(p);
            TazPvP.statsManager.initPlayer(p);

        }

/*        if(TazPvP.statsManager.statsFile.contains(event.getPlayer().getUniqueId().toString())) {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + p.getName());
        } else {
            TazPvP.statsManager.initPlayer(p);

            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + p.getName());
            Player player = event.getPlayer();
            PlayerUtils.equipStarter(player);
        } */
        p.spigot().setCollidesWithEntities(true);
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        for(Scoreboard sb : TazPvP.statsManager.scoreboards.values()) {
            TazPvP.statsManager.getTeam(p, sb).removeEntry(p.getName());
        }

        TazPvP.statsManager.scoreboards.remove(p.getUniqueId());
        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + p.getName());
        if(BanCommand.bannedRunnables.containsKey(p.getUniqueId())) {
            BanCommand.bannedRunnables.get(p.getUniqueId()).cancel();
            BanCommand.bannedRunnables.remove(p.getUniqueId());
        }
        p.spigot().setCollidesWithEntities(true);
    }

    @EventHandler
    public void onPlayerWorldChange(PlayerChangedWorldEvent event){
        if (event.getPlayer().getWorld().getName().equals("arena")) {
            World world = event.getPlayer().getWorld();
            Player p = event.getPlayer();




            int min = 1;
            int max = 6;

            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);

            if (random_int == 1) {
                Location location = new Location(world, 0, 30, 0);
                p.teleport(location);
            } else if (random_int == 2) {
                Location location = new Location(world, -18, 30, -11);
                p.teleport(location);
            } else if (random_int == 3) {
                Location location = new Location(world, 13, 30, -28);
                p.teleport(location);
            } else if (random_int == 4) {
                Location location = new Location(world, 26, 30, -1);
                p.teleport(location);
            } else if (random_int == 5) {
                Location location = new Location(world, 6, 30, 35);
                p.teleport(location);
            } else {
                Location location = new Location(world, -30, 30, 16);
                p.teleport(location);
            }
        }
    }
}
