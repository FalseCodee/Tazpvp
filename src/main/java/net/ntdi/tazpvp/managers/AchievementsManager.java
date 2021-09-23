package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Rewards;
import net.ntdi.tazpvp.achievements.types.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AchievementsManager {
    public FileConfiguration achievementsFile;
    File file;
    public ArrayList<Achievements> achievements = new ArrayList<>();

    public AchievementsManager(){
        file = new File(TazPvP.getInstance().getDataFolder() + "/achievements.yml");
        achievementsFile = YamlConfiguration.loadConfiguration(file);
        init();
    }

    public void init() {
        //achievements.add(new DeathAchievement(5, new Rewards[] {Rewards.MONEY}, new int[] {100}));
        achievements.add(new DeathAchievement(50, new Rewards[] {Rewards.MONEY}, new int[] {100}));
        achievements.add(new DeathAchievement(100, new Rewards[] {Rewards.POINTS}, new int[] {3}));
        achievements.add(new DeathAchievement(200, new Rewards[] {Rewards.MONEY}, new int[] {250}));
        achievements.add(new DeathAchievement(300, new Rewards[] {Rewards.POINTS}, new int[] {10}));
        achievements.add(new DeathAchievement(400, new Rewards[] {Rewards.MONEY}, new int[] {2500}));
        achievements.add(new DeathAchievement(500, new Rewards[] {Rewards.POINTS}, new int[] {15}));
        achievements.add(new DeathAchievement(1000, new Rewards[] {Rewards.MONEY}, new int[] {10000}));

        //achievements.add(new KillAchievement(5, new Rewards[] {Rewards.MONEY}, new int[] {200}));
        achievements.add(new KillAchievement(25, new Rewards[] {Rewards.MONEY}, new int[] {200}));
        achievements.add(new KillAchievement(50, new Rewards[] {Rewards.POINTS}, new int[] {5}));
        achievements.add(new KillAchievement(100, new Rewards[] {Rewards.MONEY}, new int[] {500}));
        achievements.add(new KillAchievement(200, new Rewards[] {Rewards.POINTS}, new int[] {15}));
        achievements.add(new KillAchievement(500, new Rewards[] {Rewards.MONEY}, new int[] {1000}));
        achievements.add(new KillAchievement(1000, new Rewards[] {Rewards.POINTS}, new int[] {25}));
        achievements.add(new KillAchievement(1500, new Rewards[] {Rewards.MONEY}, new int[] {15000}));

        // one heart achievement
        achievements.add(new LivingOnEdgeAchievement(1, new Rewards[] {Rewards.MONEY}, new int[] {50}));

        // tis but a scratch
        achievements.add(new ButAScratchAchievement(1, new Rewards[] {Rewards.POINTS}, new int[] {2}));

        // slowpoke
        achievements.add(new SlowPokeAchievement(1, new Rewards[] {Rewards.MONEY}, new int[] {50}));

        // takeover
        achievements.add(new TakeOverAchievement(1, new Rewards[] {Rewards.POINTS}, new int[] {5}));

        // Smart Player
        achievements.add(new SmartPlayerAchievement(1, new Rewards[] {Rewards.MONEY}, new int[] {100}));

        // Starting beef
        achievements.add(new BeefAchievement(1, new Rewards[] {Rewards.POINTS}, new int[] {3}));

        // Caught a fish
        achievements.add(new CaughtFishAchievement(1, new Rewards[] {Rewards.MONEY}, new int[] {50}));

        // 500 smacks
        achievements.add(new SmackAchievement(500, new Rewards[] {Rewards.MONEY}, new int[] {200}));

    }

    public void onDeath(Player deadPlayer) {
        for(Achievements ach : achievements) {
            ach.onDeath(deadPlayer);
        }
    }
    public void onKill(Player killer) {
        for(Achievements ach : achievements) {
            ach.onKill(killer);
        }
    }
    public void onSmack(Player smacker){
        for(Achievements ach : achievements) {
            if(ach instanceof SmackAchievement) {
                ((SmackAchievement) ach).onSmack(smacker);
            }
        }
    }

    public void addAchievement(Achievements ach, Player player) {
        List<String> stringList = achievementsFile.getStringList(ach.prefix);
        stringList.add(player.getUniqueId().toString());
        achievementsFile.set(ach.prefix, stringList);
    }
    public boolean playerCompletedAchievement(Achievements ach, Player player) {
        return achievementsFile.getStringList(ach.prefix).contains(player.getUniqueId().toString());
    }

    public void saveAchievements(){
        try {
            achievementsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
