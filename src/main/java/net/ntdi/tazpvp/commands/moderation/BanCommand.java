package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.StringUtils;
import net.ntdi.tazpvp.utils.https.PostHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class BanCommand implements CommandExecutor {

    public static final HashMap<UUID, BukkitRunnable> bannedRunnables = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }
        if(player != null && args[0].equalsIgnoreCase("hm")) {
            player.sendMessage(bannedRunnables.keySet().size() + "");
            return true;
        }
        OfflinePlayer banned = Bukkit.getOfflinePlayer(args[0]);
        if (banned != null) {
            if(player != null && player.hasPermission("staff.ban") && !TazPvP.permissions.playerHas((Player) banned, "staff.banbypass")) {
                if(args.length < 2){
                    return false;
                } else {
                    String reason = StringUtils.buildString(args, 1);
                    if(TazPvP.punishmentManager.isBanned(banned)){
                        player.sendMessage(ChatColor.RED + "Player is already banned");

                    } else {
                        if (banned.isOnline()) {
                            banned.getPlayer().sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                            banned.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN" + ChatColor.GRAY + " You've been banned for "+ChatColor.WHITE+reason);
                            banned.getPlayer().sendMessage(ChatColor.GRAY + "Do not log out if you wish to be unbanned. Join the discord and create a ticket.");
                            banned.getPlayer().sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    TazPvP.getInstance().initScoreboard(banned.getPlayer());
                                }
                            }.runTaskLater(TazPvP.getInstance(), 20L);

                        }
                        TazPvP.punishmentManager.initBan(banned, true, 60*60*1000);

                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        String pexcmd = "pex user " + banned.getName() + " group add banned";
                        Bukkit.dispatchCommand(console, pexcmd);

                        for (Player p : Bukkit.getOnlinePlayers()){
                            if (!Objects.equals(p.getName(), banned.getName())){
                                p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                                p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN " + ChatColor.WHITE + banned.getName() + ChatColor.GRAY + " has been punished for " + ChatColor.WHITE + reason);
                                p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                            }
                        }



//                        BukkitRunnable runnable = getBanRunnable(banned);
//                        runnable.runTaskTimer(TazPvP.getInstance(), 0L, 1200L);
//                        bannedRunnables.put(banned.getUniqueId(), runnable);

                        JSONObject obj = new JSONObject();
                        JSONArray embed = new JSONArray();
                        JSONObject embedObj = new JSONObject();
                        embed.add(embedObj);

                        embedObj.put("title", banned.getName());
                        embedObj.put("color", 16077890);

                        JSONArray fields = new JSONArray();
                        JSONObject fieldBanned = new JSONObject();
                        fieldBanned.put("name", "Ban Info");
                        fieldBanned.put("value", "**Banned Account:** " + banned.getName()+"\n**Reason:** " + reason);
                        JSONObject fieldOther = new JSONObject();
                        fieldOther.put("name", "Other Info");
                        fieldOther.put("value", "**Banner:** " + player.getName()+"\n**Time:** " + StringUtils.dateToString(System.currentTimeMillis()));
                        fields.add(fieldBanned);
                        fields.add(fieldOther);
                        embedObj.put("fields", fields);
                        obj.put("embeds", embed);
                        obj.put("username", "Banner");
                        obj.put("avatar_url", "");
                        try {
                            PostHelper.postRequest(TazPvP.configFile.getString("webhooks.bans"), obj.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            player.sendMessage(ChatColor.RED + "Webhook failed, please contact an admin");
                        }
                    }
                }
            } else if(player != null) {
                if(args.length < 2){
                    return false;
                } else {
                    String reason = StringUtils.buildString(args, 1);
                    if(TazPvP.punishmentManager.isBanned(banned)){
                        System.out.println(ChatColor.RED + "Player is already banned");

                    } else {
                        TazPvP.punishmentManager.initBan(banned, true, 60*60*1000);
                        if (banned.isOnline()){
                            banned.getPlayer().sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                            banned.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN" + ChatColor.GRAY + " You've been banned for "+ChatColor.WHITE+reason);
                            banned.getPlayer().sendMessage(ChatColor.GRAY + "Do not log out if you wish to be unbanned. Join the discord and create a ticket.");
                            banned.getPlayer().sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

//                        banned.teleport(new Location(Bukkit.getWorld("world"), 0.5, 51, 0.5, -179, 0));


                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    TazPvP.getInstance().initScoreboard(banned.getPlayer());
                                }
                            }.runTaskLater(TazPvP.getInstance(), 20L);
                        }

                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        String pexcmd = "pex user " + banned.getName() + " group add banned";
                        Bukkit.dispatchCommand(console, pexcmd);

                        for (Player p : Bukkit.getOnlinePlayers()){
                            if (!Objects.equals(p.getName(), banned.getName())){
                                p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                                p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN " + ChatColor.WHITE + banned.getName() + ChatColor.GRAY + " has been punished for " + ChatColor.WHITE + reason);
                                p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                            }
                        }



//                        BukkitRunnable runnable = getBanRunnable(banned);
//                        runnable.runTaskTimer(TazPvP.getInstance(), 0L, 1200L);
//                        bannedRunnables.put(banned.getUniqueId(), runnable);

                        JSONObject obj = new JSONObject();
                        JSONArray embed = new JSONArray();
                        JSONObject embedObj = new JSONObject();
                        embed.add(embedObj);

                        embedObj.put("title", banned.getName());
                        embedObj.put("color", 16077890);

                        JSONArray fields = new JSONArray();
                        JSONObject fieldBanned = new JSONObject();
                        fieldBanned.put("name", "Ban Info");
                        fieldBanned.put("value", "**Banned Account:** " + banned.getName()+"\n**Reason:** " + reason);
                        JSONObject fieldOther = new JSONObject();
                        fieldOther.put("name", "Other Info");
                        fieldOther.put("value", "**Banner:** CONSOLE" +"\n**Time:** " + StringUtils.dateToString(System.currentTimeMillis()));
                        fields.add(fieldBanned);
                        fields.add(fieldOther);
                        embedObj.put("fields", fields);
                        obj.put("embeds", embed);
                        obj.put("username", "Banner");
                        obj.put("avatar_url", "");
                        try {
                            PostHelper.postRequest(TazPvP.configFile.getString("webhooks.bans"), obj.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println(ChatColor.RED + "Webhook failed, please contact an admin");
                        }
                    }
                }
            } //else if(banned != null) {
                //player.sendMessage(ChatColor.RED + "You cant ban this person!");
           // }
        }


        return true;
    }
/*    public static BukkitRunnable getBanRunnable(Player banned) {
       return new BukkitRunnable() {

           @Override
           public void run() {
               if(TazPvP.punishmentManager.getBanDuration(banned)-(System.currentTimeMillis()-TazPvP.punishmentManager.getBanTime(banned)) <= 0) {
                   this.cancel();
                   bannedRunnables.remove(banned.getUniqueId());
                   TazPvP.punishmentManager.removeBan(banned);

                   banned.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                   banned.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "UNBANNED" + ChatColor.GRAY + " You're ban time is up. Play Fair!");
                   banned.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                   ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                   String pexcmd = "pex user " + banned.getName() + " group remove banned";
                   Bukkit.dispatchCommand(console, pexcmd);

               }
               new BukkitRunnable() {

                   @Override
                   public void run() {
                       TazPvP.getInstance().initScoreboard(banned);
                   }
               }.runTaskLater(TazPvP.getInstance(), 20L);
           }
       };*/
    }

