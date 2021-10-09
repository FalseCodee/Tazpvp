package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.StringUtils;
import net.ntdi.tazpvp.utils.https.PostHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class BanCommand implements CommandExecutor {
    @Override
    @SuppressWarnings("unchecked")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.ban")){
            if(args.length < 2){
                return false;
            } else {
                Player banned = Bukkit.getPlayer(args[0]);
                String reason = StringUtils.buildString(args, 1);
                if(banned != null){
                    if(TazPvP.punishmentManager.isBanned(banned)){
                        TazPvP.punishmentManager.removeBan(banned);
                        player.sendMessage(ChatColor.RED + banned.getName() + " has been unbanned.");
                        banned.sendMessage(ChatColor.RED+"You have been unbanned.");

                    } else {
                        //short ban to test
                        TazPvP.punishmentManager.initBan(banned, false, 60*60*1000);
                        player.sendMessage(ChatColor.RED + banned.getName() + " has been banned.");
                        banned.sendMessage(ChatColor.RED+"You have been banned for "+ChatColor.WHITE+reason);

                        Bukkit.broadcastMessage(ChatColor.WHITE + "--------------------------------------------------------");
                        Bukkit.broadcastMessage(ChatColor.WHITE + "");
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + banned.getName() + " has been banned for: " + ChatColor.WHITE + "" + ChatColor.BOLD + args[1]);
                        Bukkit.broadcastMessage(ChatColor.WHITE + "");
                        Bukkit.broadcastMessage(ChatColor.WHITE + "--------------------------------------------------------");


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
                            player.sendMessage(ChatColor.RED + "Webhook failed, please contact an admin.");
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }

        return true;
    }
}
