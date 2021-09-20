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

//TODO: Test this

public class ChatSpamListener implements Listener {

    private final HashMap<Player, Long> cooldowns = new HashMap<>();
    //made static because maybe useful for other things
    public static ArrayList<String> badlist = new ArrayList<>();

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
        if(TazPvP.punishmentManager.isMuted(p)){
            long muteTime = TazPvP.punishmentManager.getMuteTime(p);
            long muteDuration = TazPvP.punishmentManager.getMuteDuration(p);
            if(System.currentTimeMillis()-muteTime >= muteDuration){
                TazPvP.punishmentManager.removeMute(p);
                p.sendMessage(ChatColor.RED+"You have been unmuted.");
            } else {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You are muted, you will be unmuted in, " + ChatColor.WHITE + (((muteTime+muteDuration)-System.currentTimeMillis())/1000) + "s");
                return;
            }
        }
        if (p.hasPermission("staff.chatbypass")) return;

        //Assuming permission above applies to bypassing mute chat as well.
        if(ChatUtils.chatMuted) {
            e.setCancelled(true);
            return;
        }

        if (hasIllegalWord(e.getMessage())) {
            p.sendMessage("Bad Word");
            e.setCancelled(true);
            return;
        }


        long time = System.currentTimeMillis();

        if(this.cooldowns.containsKey(p)){
            long lastUse = this.cooldowns.get(p);
            if (time - lastUse < 1*1000) {
                p.sendMessage(ChatColor.GREEN + "There is a 1 second chat cooldown");
                p.sendMessage(ChatColor.GREEN + "Buy a rank to remove it!");
                e.setCancelled(true);
            }
            /*
             *   what is the point of |
             *   this conditional     V
             */
            else if (time - lastUse < 5*100) {
                p.sendMessage(ChatColor.GREEN + "Dont Spam!");
                e.setCancelled(true);
            }
        }
        cooldowns.put(p, time);
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        cooldowns.remove(p);
    }

}
