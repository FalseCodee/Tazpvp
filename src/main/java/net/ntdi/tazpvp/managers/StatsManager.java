package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
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
    public int getMoney(Player player) {
        return statsFile.getInt(player.getUniqueId().toString()+".money");
    }
    public void setMoney(Player player, int money) {
        statsFile.set(player.getUniqueId().toString()+".money", money);
    }
    public void addMoney(Player player, int money) {
        statsFile.set(player.getUniqueId().toString()+".money", money+getMoney(player));
    }

    public int getPoints(Player player) {
        return statsFile.getInt(player.getUniqueId().toString()+".points");
    }
    public void setPoints(Player player, int points) {
        statsFile.set(player.getUniqueId().toString()+".points", points);
    }
    public void addPoints(Player player, int points) {
        statsFile.set(player.getUniqueId().toString()+".points", points+getPoints(player));
    }

    public int getLevel(Player player) {
        return statsFile.getInt(player.getUniqueId().toString()+".level");
    }
    public void setLevel(Player player, int level) {
        statsFile.set(player.getUniqueId().toString()+".level", level);
    }
    public void addLevels(Player player, int level) {
        statsFile.set(player.getUniqueId().toString()+".level", level+getLevel(player));
    }
}
