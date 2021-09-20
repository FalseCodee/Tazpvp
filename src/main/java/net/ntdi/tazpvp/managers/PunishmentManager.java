package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class PunishmentManager {
    public FileConfiguration punishmentFile;
    File file;

    public PunishmentManager(){
        file = new File(TazPvP.getInstance().getDataFolder() + "/punishments.yml");
        punishmentFile = YamlConfiguration.loadConfiguration(file);
    }
    public void savePunishments(){
        try {
            punishmentFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initBan(OfflinePlayer player, boolean permanent, long duration){
        punishmentFile.set("bans." + player.getUniqueId().toString()+".time", new Date().getTime());
        punishmentFile.set("bans." + player.getUniqueId().toString()+".duration", duration);
        punishmentFile.set("bans." + player.getUniqueId().toString()+".perm", permanent);

    }
    public void initMute(OfflinePlayer player, boolean permanent, long duration){
        punishmentFile.set("mutes." + player.getUniqueId().toString()+".time", new Date().getTime());
        punishmentFile.set("mutes." + player.getUniqueId().toString()+".duration", duration);
        punishmentFile.set("mutes." + player.getUniqueId().toString()+".perm", permanent);
    }

    public void removeBan(OfflinePlayer player){
        punishmentFile.set("bans." + player.getUniqueId().toString(), null);

    }

    public void removeMute(OfflinePlayer player) {
        punishmentFile.set("mutes." + player.getUniqueId().toString(), null);

    }
    public boolean isBanned(OfflinePlayer player) {
        return punishmentFile.contains("bans." + player.getUniqueId().toString());
    }
    public boolean isMuted(OfflinePlayer player) {
        return punishmentFile.contains("mutes." + player.getUniqueId().toString());
    }
    public long getBanTime(OfflinePlayer player) {
        return punishmentFile.getLong("bans." + player.getUniqueId().toString() + ".time");
    }
    public long getBanDuration(OfflinePlayer player) {
        return punishmentFile.getLong("bans." + player.getUniqueId().toString() + ".duration");
    }
    public boolean isPermanentBan(OfflinePlayer player) {
        return punishmentFile.getBoolean("bans." + player.getUniqueId().toString() + ".perm");
    }

    public long getMuteTime(OfflinePlayer player) {
        return punishmentFile.getLong("mutes." + player.getUniqueId().toString() + ".time");
    }
    public long getMuteDuration(OfflinePlayer player) {
        return punishmentFile.getLong("mutes." + player.getUniqueId().toString() + ".duration");
    }
    public boolean isPermanentMute(OfflinePlayer player) {
        return punishmentFile.getBoolean("mutes." + player.getUniqueId().toString() + ".perm");
    }
}
