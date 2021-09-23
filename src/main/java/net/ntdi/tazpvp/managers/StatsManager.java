package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class StatsManager {
    public final FileConfiguration statsFile;
    final File file;

    public StatsManager(){
        file = new File(TazPvP.getInstance().getDataFolder() + "/stats.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }
    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPlayer(OfflinePlayer player){
        statsFile.set(player.getUniqueId().toString()+".points", 0);
        statsFile.set(player.getUniqueId().toString()+".level", 0);
        statsFile.set(player.getUniqueId().toString()+".money", 0);
        statsFile.set(player.getUniqueId().toString()+".deaths", 0);
        statsFile.set(player.getUniqueId().toString()+".kills", 0);
        statsFile.set(player.getUniqueId().toString()+".rank", TazPvP.permissions.getPrimaryGroup((Player) player));
        statsFile.set(player.getUniqueId().toString()+".smacks", 0);
        statsFile.set(player.getUniqueId().toString()+".lastReward", 0);
        statsFile.set(player.getUniqueId().toString()+".rebirths", 0);
        statsFile.set(player.getUniqueId().toString()+".credits", 0);
        statsFile.set(player.getUniqueId().toString()+".streak", 0);
    }

    public int getMoney(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".money");
    }
    public void setMoney(OfflinePlayer player, int money) {
        statsFile.set(player.getUniqueId().toString()+".money", money);
    }
    public void addMoney(OfflinePlayer player, int money) {
        setMoney(player, money+getMoney(player));
    }

    public int getPoints(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".points");
    }
    public void setPoints(OfflinePlayer player, int points) {
        statsFile.set(player.getUniqueId().toString()+".points", points);
    }
    public void addPoints(OfflinePlayer player, int points) {
        setPoints(player, points+getPoints(player));
    }

    public int getLevel(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".level");
    }
    public void setLevel(OfflinePlayer player, int level) {
        statsFile.set(player.getUniqueId().toString()+".level", level);
    }
    public void addLevels(OfflinePlayer player, int level) {
        setLevel(player, level+getLevel(player));
    }

    public int getDeaths(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".deaths");
    }
    public void setDeaths(OfflinePlayer player, int deaths) {
        statsFile.set(player.getUniqueId().toString()+".deaths", deaths);
    }
    public void addDeaths(OfflinePlayer player, int deaths) {
        setDeaths(player, deaths+getDeaths(player));
    }

    public int getKills(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".kills");
    }
    public void setKills(OfflinePlayer player, int kills) {
        statsFile.set(player.getUniqueId().toString()+".kills", kills);
    }
    public void addKills(OfflinePlayer player, int kills) {
        setKills(player, kills+getKills(player));
    }

    public String getGroup(OfflinePlayer player) {
        return statsFile.getString(player.getUniqueId().toString()+".rank");
    }
    public void setGroup(OfflinePlayer player, String group) {
        statsFile.set(player.getUniqueId().toString() + ".rank", group);
    }

    public int getSmacks(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".smacks");
    }
    public void setSmacks(OfflinePlayer player, int smacks) {
        statsFile.set(player.getUniqueId().toString()+".smacks", smacks);
    }
    public void addSmacks(OfflinePlayer player, int smacks) {
        setSmacks(player, smacks+getSmacks(player));
    }

    public long getLastReward(OfflinePlayer player) {
        return statsFile.getLong(player.getUniqueId().toString()+".lastReward");
    }
    public void setLastReward(OfflinePlayer player) {
        statsFile.set(player.getUniqueId().toString() + ".lastReward", System.currentTimeMillis());
    }
    public int getRebirths(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".rebirths");
    }
    public void setRebirths(OfflinePlayer player, int rebirths) {
        statsFile.set(player.getUniqueId().toString()+".rebirths", rebirths);
    }
    public void addRebirths(OfflinePlayer player, int rebirths) {
        setRebirths(player, rebirths+getRebirths(player));
    }

    public int getCredits(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".credits");
    }
    public void setCredits(OfflinePlayer player, int kills) {
        statsFile.set(player.getUniqueId().toString()+".credits", kills);
    }
    public void addCredits(OfflinePlayer player, int kills) {
        setCredits(player, kills+getCredits(player));
    }
    public int getStreak(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".streak");
    }
    public void setStreak(OfflinePlayer player, int kills) {
        statsFile.set(player.getUniqueId().toString()+".streak", kills);
    }
    public void addStreak(OfflinePlayer player, int kills) {
        setStreak(player, kills+getStreak(player));
    }
}
