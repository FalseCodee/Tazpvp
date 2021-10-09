package net.ntdi.tazpvp.commands.playercommands;

// import jdk.nashorn.internal.ir.annotations.Ignore;
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

public class ReportCommand implements CommandExecutor {
    @Override
    @SuppressWarnings("unchecked")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }
        if(player == null){
            return false;
        }
        if(args.length == 1){
            return false;
        }
        else {
            Player reportee = Bukkit.getPlayer(args[0]);
            if(reportee != null){
                Bukkit.broadcast(ChatColor.DARK_GRAY +"▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n"+ChatColor.RED+""+ChatColor.BOLD +"REPORT " + ChatColor.WHITE + reportee.getName() + ChatColor.GRAY + " was reported for " + ChatColor.WHITE + StringUtils.buildString(args, 1) + ChatColor.GRAY+" by "+ChatColor.WHITE + player.getName() + "\n" + ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬", "staff.reports");
                player.sendMessage(ChatColor.RED + "Thank you for reporting " + ChatColor.WHITE + reportee.getName() + ChatColor.RED + ", they will be reviewed shortly.");
                JSONObject obj = new JSONObject();
                JSONArray embed = new JSONArray();
                JSONObject embedObj = new JSONObject();
                embed.add(embedObj);

                embedObj.put("title", reportee.getName());
                embedObj.put("color", 16077890);

                JSONArray fields = new JSONArray();
                JSONObject fieldReported = new JSONObject();
                fieldReported.put("name", "Accused Info");
                fieldReported.put("value", "**Accused:** " + reportee.getName()+"\n**Reason:** " + StringUtils.buildString(args, 1));
                //fieldReported.put("inline", true);
                JSONObject fieldReporter = new JSONObject();
                fieldReporter.put("name", "Report Info");
                fieldReporter.put("value", "**Reporter:** " + player.getName()+"\n**Time:** " + StringUtils.dateToString(System.currentTimeMillis()));
                //fieldReporter.put("inline", true);
                fields.add(fieldReported);
                fields.add(fieldReporter);
                embedObj.put("fields", fields);
                obj.put("embeds", embed);
                obj.put("username", "Reporter");
                obj.put("avatar_url", "");
                //obj.put("content", reportee.getName() + " has been reported for " + StringUtils.buildString(args, 1) + " by " + player.getName());
                try {
                    PostHelper.postRequest(TazPvP.configFile.getString("webhooks.reports"), obj.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    player.sendMessage(ChatColor.RED + "Webhook failed, please contact an admin.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Player not found.");
            }
        }
        return true;
    }
}
