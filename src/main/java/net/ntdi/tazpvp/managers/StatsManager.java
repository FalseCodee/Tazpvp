package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class StatsManager {
    public FileConfiguration statsFile;
    File file;

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
}
