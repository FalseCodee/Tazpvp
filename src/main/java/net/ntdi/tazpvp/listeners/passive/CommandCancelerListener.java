package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandCancelerListener implements Listener {

    public TazPvP plugin;

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();
        System.out.println(event.getMessage());
//        String message = event.getMessage();
//        String[] args = message.split(" ");
        if (!player.hasPermission("staff.commandbypass")) {
            if (event.getMessage().toLowerCase().startsWith("/minecraft")) {
                event.setCancelled(true);
                //player.sendMessage(ChatColor.WHITE + "UR MOMMA");
            }
            else if (event.getMessage().toLowerCase().startsWith("/pl") && !event.getMessage().toLowerCase().startsWith("/playtime")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.WHITE + "Plugins (1): " + ChatColor.GREEN + "Tazpvp");
            }

        } //else if (player.hasPermission("staff.commandbypass")) {
//            System.out.println(event.getPlayer().getName() + ": " + event.getMessage());
//            JSONObject obj = new JSONObject();
//            JSONArray embed = new JSONArray();
//            JSONObject embedObj = new JSONObject();
//            embed.add(embedObj);
//
//            embedObj.put("title", player.getName());
//            embedObj.put("color", 16077890);
//
//            JSONArray fields = new JSONArray();
//            JSONObject fieldCommand = new JSONObject();
//            fieldCommand.put("name", "Command Info: it was sent");
//            fieldCommand.put("value", "**Command Sent:** " + event.getMessage());
//            JSONObject fieldOther = new JSONObject();
//            fieldOther.put("name", "Other Info");
//            fieldOther.put("value", "**Sender:** " + player.getName()+"\n**Time:** " + StringUtils.dateToString(System.currentTimeMillis()));
//            fields.add(fieldCommand);
//            fields.add(fieldOther);
//            embedObj.put("fields", fields);
//            obj.put("embeds", embed);
//            obj.put("username", "Banner");
//            obj.put("avatar_url", "");
//            try {
//                PostHelper.postRequest(TazPvP.configFile.getString("webhooks.logs"), obj.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//                player.sendMessage(ChatColor.RED + "Webhook failed, please contact an admin.");
//            }
//        }
    }
}
