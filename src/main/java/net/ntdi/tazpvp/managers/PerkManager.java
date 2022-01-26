package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.WeakHashMap;

public class PerkManager {
    public final FileConfiguration perksFile;
    final File file;
    public final WeakHashMap<UUID, Scoreboard> scoreboards = new WeakHashMap<>();

    public PerkManager(){
        file = new File(TazPvP.getInstance().getDataFolder() + "/perks.yml");
        perksFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveStats(){
        try {
            perksFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPlayer(OfflinePlayer player){

        UUID uuid = player.getUniqueId();

        perksFile.set(uuid+".saveblocks", false);
        perksFile.set(uuid+".butter", false);
        perksFile.set(uuid+".agility", false);
        perksFile.set(uuid+".extinguish", false);
        perksFile.set(uuid+".falldamage", false);
        perksFile.set(uuid+".hunger", false);
        perksFile.set(uuid+".haste", false);
        perksFile.set(uuid+".robbery", false);
        perksFile.set(uuid+".strength", false);
        perksFile.set(uuid+".arrow", false);
        perksFile.set(uuid+".fat", false);
        perksFile.set(uuid+".poison", false);
        perksFile.set(uuid+".excavator", false);
    }

    // TODO add more perks

    public boolean getSaveBlocks(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".saveblocks");
    }
    public void setSaveBlocks(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".saveblocks", bool);
    }
    public boolean getButter(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".butter");
    }
    public void setButter(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".butter", bool);
    }
    public boolean getAgility(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".agility");
    }
    public void setAgility(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".agility", bool);
    }
    public boolean getExtinguish(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".extinguish");
    }
    public void setExtinguish(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".extinguish", bool);
    }
    public boolean getFallDamage(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".falldamage");
    }
    public void setFallDamage(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".falldamage", bool);
    }
    public boolean getHunger(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".hunger");
    }
    public void setHunger(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".hunger", bool);
    }
    public boolean getHaste(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".haste");
    }
    public void setHaste(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".haste", bool);
    }
    public boolean getRobbery(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".robbery");
    }
    public void setRobbery(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".robbery", bool);
    }
    public boolean getStrength(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".robbery");
    }
    public void setStrength(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".robbery", bool);
    }
    public boolean getArrow(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".arrow");
    }
    public void setArrow(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".arrow", bool);
    }
    public boolean getFat(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".arrow");
    }
    public void setFat(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".fat", bool);
    }
    public boolean getPoison(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".poison");
    }
    public void setPoison(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".poison", bool);
    }
    public boolean getExcavator(OfflinePlayer player) {
        return perksFile.getBoolean(player.getUniqueId().toString()+".excavator");
    }
    public void setExcavator(OfflinePlayer player, Boolean bool) {
        perksFile.set(player.getUniqueId().toString()+".excavator", bool);
    }


    public static <T> T getOrDefault(FileConfiguration file, String path, T defaultReturn) {
        if(file.contains(path) && file.get(path) != null) {
            return (T) file.get(path);
        }
        return defaultReturn;
    }
}
