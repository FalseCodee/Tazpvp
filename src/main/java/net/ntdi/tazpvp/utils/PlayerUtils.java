package net.ntdi.tazpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class PlayerUtils {
    public static OfflinePlayer getPlayer(String username){
        for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()){
            if(username.equalsIgnoreCase(offlinePlayer.getName())){
                return offlinePlayer;
            }
        }
        return null;
    }
}
