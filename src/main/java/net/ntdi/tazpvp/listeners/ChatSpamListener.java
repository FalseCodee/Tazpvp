package net.ntdi.tazpvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatSpamListener implements Listener {


    public ArrayList<String> getIllegalWords(List<String> illegals, String msg) {
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
        ArrayList<String> illegalwords = new ArrayList<>();
        for (int start = 0; start < msg.length(); start++) {
            for (int offset = 1; offset < msg.length() + 1 - start; offset++)  {
                String wordToCheck = msg.substring(start, start + offset);
                if (illegals.contains(wordToCheck)) {
                    illegalwords.add(wordToCheck);
                }
            }
        }
        return illegalwords;
    }

    private HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    @EventHandler()
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("op")) return;
        Long time = System.currentTimeMillis();

        String msg = e.getMessage();
        if (!p.hasPermission("op")){
            ArrayList<String> badlist = new ArrayList<String>();
            badlist.add("bitch");

            if (getIllegalWords(badlist, e.getMessage()).size() > 0) {
                p.sendMessage("Bad Word");
                return;
            }
        }

        try {
            Long lastUse = this.cooldowns.get(p.getName());
            if (lastUse + 1*1000 > time) {
                p.sendMessage(ChatColor.GREEN + "There is a 1 second chat cooldown");
                p.sendMessage(ChatColor.GREEN + "Buy a rank to remove it!");
                e.setCancelled(true);
            }
            if (lastUse + 5*100 > time) {
                p.sendMessage(ChatColor.GREEN + "Dont Spam!");
                e.setCancelled(true);
            }
        } catch (Exception ex) {
        }
        try {
            cooldowns.remove(p.getName());
        } catch (Exception ex) {
        }
        cooldowns.put(p.getName(), time);
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        cooldowns.remove(p.getName());
    }

}
