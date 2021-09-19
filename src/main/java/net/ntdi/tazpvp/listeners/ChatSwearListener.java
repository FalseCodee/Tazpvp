package net.ntdi.tazpvp.listeners;

import com.avaje.ebeaninternal.server.cluster.mcast.Message;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChatSwearListener implements Listener {

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


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = (Player) e.getPlayer();

        String msg = e.getMessage();
        if (!p.hasPermission("op")){
            ArrayList<String> badlist = new ArrayList<String>();
            badlist.add("bitch");

            if (getIllegalWords(badlist, e.getMessage()).size() > 0) {
                p.sendMessage("Bad Word");
                return;
            }
        }

    }

}