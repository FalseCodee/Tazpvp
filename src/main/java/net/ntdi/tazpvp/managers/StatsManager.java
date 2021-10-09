package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.WeakHashMap;

public class StatsManager {
    public final FileConfiguration statsFile;
    final File file;
    public final WeakHashMap<UUID, Scoreboard> scoreboards = new WeakHashMap<>();
    



    public StatsManager(){
        file = new File(TazPvP.getInstance().getDataFolder() + "/stats.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }
    public void initScoreboard(Player player) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        sb.registerNewTeam("a");
        sb.registerNewTeam("b");
        sb.registerNewTeam("c");
        sb.registerNewTeam("d");
        sb.registerNewTeam("e");
        sb.registerNewTeam("f");
        sb.registerNewTeam("g");
        sb.registerNewTeam("h");
        sb.registerNewTeam("i");
        sb.registerNewTeam("j");
        sb.registerNewTeam("k");
        sb.registerNewTeam("l");
        sb.registerNewTeam("m");
        sb.registerNewTeam("n");
        sb.registerNewTeam("z");
        sb.registerNewObjective("sb", "dummy");
        scoreboards.put(player.getUniqueId(), sb);
        TazPvP.getInstance().initScoreboard(player);
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
        TazPvP.getInstance().initScoreboard((Player) player);
    }
    public void addMoney(OfflinePlayer player, int money) {
        setMoney(player, (money+getMoney(player)));
    }
    public int getPoints(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".points");
    }
    public void setPoints(OfflinePlayer player, int points) {
        statsFile.set(player.getUniqueId().toString()+".points", points);
        TazPvP.getInstance().initScoreboard((Player) player);
    }
    public void addPoints(OfflinePlayer player, int points) {
        setPoints(player, points+getPoints(player));
    }
    public int getLevel(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".level");
    }
    public void setLevel(OfflinePlayer player, int level) {
        statsFile.set(player.getUniqueId().toString()+".level", level);
        TazPvP.getInstance().initScoreboard((Player) player);
    }
    public void addLevels(OfflinePlayer player, int level) {
        setLevel(player, level+getLevel(player));
    }
    public int getDeaths(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".deaths");
    }
    public void setDeaths(OfflinePlayer player, int deaths) {
        statsFile.set(player.getUniqueId().toString()+".deaths", deaths);
        TazPvP.getInstance().initScoreboard((Player) player);
    }
    public void addDeaths(OfflinePlayer player, int deaths) {
        setDeaths(player, deaths+getDeaths(player));
    }
    public int getKills(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".kills");
    }
    public void setKills(OfflinePlayer player, int kills) {
        statsFile.set(player.getUniqueId().toString()+".kills", kills);
        TazPvP.getInstance().initScoreboard((Player) player);
    }
    public void addKills(OfflinePlayer player, int kills) {
        setKills(player, kills+getKills(player));
    }
    public String getGroup(OfflinePlayer player) {
        return statsFile.getString(player.getUniqueId().toString()+".rank");
    }
    public void setGroup(OfflinePlayer player, String group) {
        statsFile.set(player.getUniqueId().toString() + ".rank", group);
        TazPvP.getInstance().initScoreboard((Player) player);
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
        TazPvP.getInstance().initScoreboard((Player) player);
    }
    public void addRebirths(OfflinePlayer player, int rebirths) {
        setRebirths(player, rebirths+getRebirths(player));
    }
    public int getCredits(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".credits");
    }
    public void setCredits(OfflinePlayer player, int kills) {
        statsFile.set(player.getUniqueId().toString()+".credits", kills);
        TazPvP.getInstance().initScoreboard((Player) player);
    }
    public void addCredits(OfflinePlayer player, int kills) {
        setCredits(player, kills+getCredits(player));
    }
    public int getStreak(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".streak");
    }
    public void setStreak(OfflinePlayer player, int kills) {
        statsFile.set(player.getUniqueId().toString()+".streak", kills);
        TazPvP.getInstance().initScoreboard((Player) player);
    }
    public void addStreak(OfflinePlayer player, int kills) {
        setStreak(player, kills+getStreak(player));
    }
    
    public Team getTeam(Player player, Scoreboard sb) {
            switch (TazPvP.permissions.getPrimaryGroup(player).toLowerCase()) {
                case"default":
                    return sb.getTeam("n");
                case"owner":
                    return sb.getTeam("a");
                case"manager":
                    return sb.getTeam("b");
                case"admin":
                    return sb.getTeam("c");
                case"mod":
                    return sb.getTeam("e");
                case"helper":
                    return sb.getTeam("f");
                case"builder":
                    return sb.getTeam("g");
                case"sr.Mod":
                    return sb.getTeam("d");
                case"pro":
                    return sb.getTeam("j");
                case"champion":
                    return sb.getTeam("k");
                case"legend":
                    return sb.getTeam("l");
                case"dev":
                    return sb.getTeam("h");
                case"youtube":
                    return sb.getTeam("i");
                case"mythical":
                    return sb.getTeam("m");
                case"banned":
                    return sb.getTeam("z");
            } 
            return null;
        }
}
