package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class StaffManager {
    public final FileConfiguration staffFile;
    final File file;

    public StaffManager(){
        file = new File(TazPvP.getInstance().getDataFolder() + "/staff.yml");
        staffFile = YamlConfiguration.loadConfiguration(file);
    }
    public void saveStaffFile(){
        try {
            staffFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean staffChatToggled(OfflinePlayer player) {
        if(!staffFile.contains("staffchat."+player.getUniqueId().toString())){
            return false;
        }
        return staffFile.getBoolean("staffchat."+player.getUniqueId().toString());
    }
    public void toggleStaffChat(OfflinePlayer player) {
        staffFile.set("staffchat."+player.getUniqueId().toString(), !staffChatToggled(player));
    }

    public void sendStaffChat(Player player, String message){
        Bukkit.broadcast(ChatColor.YELLOW + "STAFFCHAT " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&',TazPvP.chat.getGroupPrefix((String) null, TazPvP.permissions.getPrimaryGroup(player))) + "" + player.getName() + ChatColor.YELLOW +" >> " + ChatColor.WHITE + message, "staff.staffchat");
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("staff.staffchat")){
                if (p != player){
                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                }
            }
        }
    }

    public boolean hiddenToggled(OfflinePlayer player) {
        if(!staffFile.contains("hidden."+player.getUniqueId().toString())){
            return false;
        }
        return staffFile.getBoolean("hidden."+player.getUniqueId().toString());
    }
    public void toggleHidden(OfflinePlayer player) {
        staffFile.set("hidden."+player.getUniqueId().toString(), !hiddenToggled(player));
    }

}
