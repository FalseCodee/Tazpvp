package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatSpamListener implements Listener {

    private final HashMap<Player, Long> cooldowns = new HashMap<>();
    //made static because maybe useful for other things
    public static final ArrayList<String> badlist = new ArrayList<>();

    public ChatSpamListener(){
        badlist.add("bitch");
    }

    public boolean hasIllegalWord(String msg) {
        msg = msg.replaceAll("1","i");
        msg = msg.replaceAll("!","i");
        msg = msg.replaceAll("3","e");
        msg = msg.replaceAll("4","a");
        msg = msg.replaceAll("@","a");
        msg = msg.replaceAll("5","s");
        msg = msg.replaceAll("7","t");
        msg = msg.replaceAll("0","o");
        msg = msg.replaceAll("9","g");
        msg = msg.toLowerCase().replaceAll("[^a-zA-Z]", "");

        return badlist.contains(msg);
    }

    @EventHandler()
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("tazpvp.staff.level")){
            if(TazPvP.permissions.getPrimaryGroup(p).equals("default")) {
                String str = (TazPvP.statsManager.getRebirths(p) > 0) ? ChatColor.GRAY+ "[" + ChatColor.GOLD + TazPvP.statsManager.getLevel(p) + ChatColor.GRAY + "] " + p.getDisplayName() + ": " + "%2$s" : ChatColor.GRAY+ "[" + TazPvP.statsManager.getLevel(p) + "] " + p.getDisplayName() + ": " + "%2$s";
                e.setFormat(str);
            } else {
                String str = (TazPvP.statsManager.getRebirths(p) > 0) ? ChatColor.GRAY+ "[" + ChatColor.GOLD + TazPvP.statsManager.getLevel(p) + ChatColor.GRAY + "] " + ChatColor.translateAlternateColorCodes('&',TazPvP.chat.getGroupPrefix((String) null, TazPvP.permissions.getPrimaryGroup(p))+ p.getDisplayName()) + " " + ChatColor.WHITE + "%2$s" : ChatColor.GRAY+ "[" + TazPvP.statsManager.getLevel(p) + "] " + ChatColor.translateAlternateColorCodes('&',TazPvP.chat.getGroupPrefix((String) null, TazPvP.permissions.getPrimaryGroup(p))+ p.getDisplayName()) + " " + ChatColor.WHITE + "%2$s" ;
                e.setFormat(str);
            }
            if(p.hasPermission("staff.staffchat") && TazPvP.staffManager.staffChatToggled(p)){
                TazPvP.staffManager.sendStaffChat(p, e.getMessage());
                e.setCancelled(true);
                return;
            }
        } else {
            if(TazPvP.permissions.getPrimaryGroup(p).equals("default")) {
                e.setFormat(p.getDisplayName() + ": " + "%2$s");
            } else {
                e.setFormat(ChatColor.translateAlternateColorCodes('&',TazPvP.chat.getGroupPrefix((String) null, TazPvP.permissions.getPrimaryGroup(p))+ p.getDisplayName()) + " " + ChatColor.WHITE + "%2$s");
            }
            if(p.hasPermission("staff.staffchat") && TazPvP.staffManager.staffChatToggled(p)){
                TazPvP.staffManager.sendStaffChat(p, e.getMessage());
                e.setCancelled(true);
                return;
            }
        }

        if(TazPvP.punishmentManager.isMuted(p)){
            p.sendMessage(ChatColor.RED + "You cannot speak while muted.");
            e.setCancelled(true);
            return;
        }
        if (TazPvP.punishmentManager.isBanned(p)){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You cannot chat while banned.");
            return;
        }
        if (p.hasPermission("staff.chatbypass")) return;

        //Assuming permission above applies to bypassing mute chat as well.
        if(ChatUtils.chatMuted) {
            e.setCancelled(true);
            return;
        }

//        if (hasIllegalWord(e.getMessage())) {
//            p.sendMessage("Bad Word");
//            e.setCancelled(true);
//            return;
//        }

        if (!e.getPlayer().hasPermission("staff.chatbypass")) {
            String message = e.getMessage();

            message = message.replace("shit", "####");
            message = message.replace("porn", "####");
            message = message.replace("whore", "#####");
            message = message.replace("dick", "####");
            message = message.replace("dildo", "#####");
            message = message.replace("fag", "###");
            message = message.replace("cock", "####");
            message = message.replace("retar", "####");
            message = message.replace("pussy", "#####");
            message = message.replace("cunt", "####");
            message = message.replace("bitc", "####");
            message = message.replace("fuck", "####");
            message = message.replace("nigg", "####");
            message = message.replace("fuk", "###");
            message = message.replace("fuc", "###");
            message = message.replace("slut", "####");

            if (message.equalsIgnoreCase(message))
            if (message != "night" && message.equalsIgnoreCase("nig")) {
                message = message.replace("nig", "###");
            } else {
                e.setMessage(message);
            }
        }
        if (!e.getPlayer().hasPermission("staff.chatbypass")) {
            String message = e.getMessage();
            message = message.replace(".", " ");
            e.setMessage(message);
        }
        if (!e.getPlayer().hasPermission("staff.chatbypass")) {
            String message = e.getMessage();
            if (message.contains("how")){
                p.sendMessage(ChatColor.DARK_GRAY + "( " + ChatColor.RED + "!" + ChatColor.DARK_GRAY + " ) " + ChatColor.GRAY + "Learn how the server works better by typing /Help.");
            }
        }


        long time = System.currentTimeMillis();

        if(this.cooldowns.containsKey(p)){
            long lastUse = this.cooldowns.get(p);
            if (time - lastUse < 1000) {
                p.sendMessage(ChatColor.RED + "Please wait before chatting again, purchase a rank to remove the cooldown.");
                e.setCancelled(true);
            }
        }
        if(p.isOp()) {
            e.setMessage(ChatColor.translateAlternateColorCodes('&',e.getMessage()));
        }
        cooldowns.put(p, time);
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        cooldowns.remove(p);
    }



}
