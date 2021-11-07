package net.ntdi.tazpvp.achievements.types;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Requirement;
import net.ntdi.tazpvp.achievements.Rewards;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class SmartPlayerAchievement extends Achievements {

    public SmartPlayerAchievement(int count, Rewards[] reward, int[] amount) {
        super("Smart Player","Kill a player by punching them with a bow.",Requirement.KILLS,count,"SmartPlayer"+count, reward, amount);
    }

    @Override
    public void onDeath(Player deadPlayer) {

    }

    @Override
    public void onKill(Player killer) {
        PlayerInventory inv = killer.getInventory();
        if(inv.getItemInHand().getType().equals(Material.BOW)
                && !TazPvP.achievementsManager.playerCompletedAchievement(this, killer)) {
            onAchievement(killer);
        }
    }

    @Override
    public void onAchievement(Player player) {
        TazPvP.achievementsManager.addAchievement(this, player);
        player.sendMessage(ChatColor.AQUA + player.getName() + ChatColor.DARK_AQUA + " has completed the " + ChatColor.RED + "Smart Player" + ChatColor.DARK_AQUA + " achievement!");

        redeemRewards(player);
    }
}
