package net.ntdi.tazpvp.achievements.types;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Requirement;
import net.ntdi.tazpvp.achievements.Rewards;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LivingOnEdgeAchievement extends Achievements {

    public LivingOnEdgeAchievement(int count, Rewards[] reward, int[] amount) {
        super("Living on the Edge","Kill a player at 1 heart.",Requirement.KILLS,count,"LivingOnEdge"+count, reward, amount);
    }

    @Override
    public void onDeath(Player deadPlayer) {

    }

    @Override
    public void onKill(Player killer) {
        if(killer.getHealth() <= 2
                && !TazPvP.achievementsManager.playerCompletedAchievement(this, killer)) {
            onAchievement(killer);
        }
    }

    @Override
    public void onAchievement(Player player) {
        TazPvP.achievementsManager.addAchievement(this, player);
        player.sendMessage(ChatColor.AQUA + "You have completed the Living on the Edge achievement!");
        redeemRewards(player);
    }
}
