package net.ntdi.tazpvp.achievements.types;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Requirement;
import net.ntdi.tazpvp.achievements.Rewards;
import org.bukkit.entity.Player;

public class DeathAchievement extends Achievements {
    public DeathAchievement(int count, Rewards[] reward, int[] amount) {
        super("Death " + count,"Die " + count + " times.",Requirement.DEATHS, count, "deaths"+count, reward, amount);
    }

    @Override
    public void onDeath(Player deadPlayer) {
        if(TazPvP.statsManager.getDeaths(deadPlayer) >= count
                && !TazPvP.achievementsManager.playerCompletedAchievement(this, deadPlayer)) {
            onAchievement(deadPlayer);
        }
    }

    @Override
    public void onKill(Player killer) {
    }

    @Override
    public void onAchievement(Player player) {
        TazPvP.achievementsManager.addAchievement(this, player);
        player.sendMessage("You have completed the " + count + " deaths achievement!");
        redeemRewards(player);
    }
}
