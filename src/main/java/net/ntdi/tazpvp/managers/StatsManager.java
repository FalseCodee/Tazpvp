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
}
