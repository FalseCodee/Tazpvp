package net.ntdi.tazpvp.achievements.types;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Requirement;
import net.ntdi.tazpvp.achievements.Rewards;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpeakToYourManagerAchievement extends Achievements {

    public SpeakToYourManagerAchievement(int count, Rewards[] reward, int[] amount) {
        super("Speak to your manager","Kill the manager.", Requirement.KILLS,count,"Manager"+count, reward, amount);
    }

    @Override
    public void onDeath(Player deadPlayer) {
        if (deadPlayer.getDisplayName().equalsIgnoreCase("ntdi") && !TazPvP.achievementsManager.playerCompletedAchievement(this, deadPlayer.getKiller())){
            onAchievement(deadPlayer.getKiller());
        }
    }

    @Override
    public void onKill(Player killer) {

    }

    @Override
    public void onAchievement(Player player) {
        TazPvP.achievementsManager.addAchievement(this, player);
        player.sendMessage(ChatColor.AQUA + player.getName() + ChatColor.DARK_AQUA + " has completed the " + ChatColor.RED + "Can I Speak to Your Manager?" + ChatColor.DARK_AQUA + " achievement!");

        redeemRewards(player);
    }
}
