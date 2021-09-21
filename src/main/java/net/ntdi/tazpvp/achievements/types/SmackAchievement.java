package net.ntdi.tazpvp.achievements.types;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Requirement;
import net.ntdi.tazpvp.achievements.Rewards;
import org.bukkit.entity.Player;

public class SmackAchievement extends Achievements {

    public SmackAchievement(int count, Rewards[] reward, int[] amount) {
        super(Requirement.SMACKS,count,"Smack"+count, reward, amount);
    }

    @Override
    public void onDeath(Player deadPlayer) {

    }

    @Override
    public void onKill(Player killer) {
        if(TazPvP.statsManager.getKills(killer) >= count
                && !TazPvP.achievementsManager.playerCompletedAchievement(this, killer)) {
            onAchievement(killer);
        }
    }

    @Override
    public void onSmack(Player smacker){
        if(TazPvP.statsManager.getSmacks(smacker) >= count && !TazPvP.achievementsManager.playerCompletedAchievement(this, smacker)) {
            onAchievement(smacker);
        }
    }

    @Override
    public void onAchievement(Player player) {
        TazPvP.achievementsManager.addAchievement(this, player);
        player.sendMessage("You have completed the " + count + " smacks achievement!");
        redeemRewards(player);
    }
}
