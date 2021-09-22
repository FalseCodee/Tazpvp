package net.ntdi.tazpvp.achievements.types;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Requirement;
import net.ntdi.tazpvp.achievements.Rewards;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SlowPokeAchievement extends Achievements {

    public SlowPokeAchievement(int count, Rewards[] reward, int[] amount) {
        super(Requirement.KILLS,count,"SlowPoke"+count, reward, amount);
    }

    @Override
    public void onDeath(Player deadPlayer) {

    }

    @Override
    public void onKill(Player killer) {
        if(killer.isSneaking()
                && !TazPvP.achievementsManager.playerCompletedAchievement(this, killer)) {
            onAchievement(killer);
        }
    }

    @Override
    public void onAchievement(Player player) {
        TazPvP.achievementsManager.addAchievement(this, player);
        player.sendMessage(ChatColor.AQUA + "You have completed the SlowPoke achievement!");
        redeemRewards(player);
    }
}
