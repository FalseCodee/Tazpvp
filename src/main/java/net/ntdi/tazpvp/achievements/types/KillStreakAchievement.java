package net.ntdi.tazpvp.achievements.types;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Requirement;
import net.ntdi.tazpvp.achievements.Rewards;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KillStreakAchievement extends Achievements {

    public KillStreakAchievement(int count, Rewards[] reward, int[] amount) {
        super("Kill Streak " + count, "Get a kill streak of " + count + ".", Requirement.KILLS, count, "KillStreak"+count, reward, amount);
    }


    @Override
    public void onDeath(Player deadPlayer) {

    }

    @Override
    public void onKill(Player killer) {
        if (TazPvP.statsManager.getStreak(killer) >= count && !TazPvP.achievementsManager.playerCompletedAchievement(this, killer)) {
            onAchievement(killer);
        }
    }

    @Override
    public void onAchievement(Player player) {
        TazPvP.achievementsManager.addAchievement(this, player);
        player.sendMessage(ChatColor.AQUA + player.getName() + ChatColor.DARK_AQUA + " has completed the " + ChatColor.RED + count + " kill streak" + ChatColor.DARK_AQUA + " achievement!");

        redeemRewards(player);
    }
}
