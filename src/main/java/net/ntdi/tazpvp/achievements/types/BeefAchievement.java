package net.ntdi.tazpvp.achievements.types;

import net.milkbowl.vault.chat.Chat;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.achievements.Requirement;
import net.ntdi.tazpvp.achievements.Rewards;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class BeefAchievement extends Achievements {

    public BeefAchievement(int count, Rewards[] reward, int[] amount) {
        super("Starting Beef","Kill a player with steak.",Requirement.KILLS,count,"StartingBeef"+count, reward, amount);
    }

    @Override
    public void onDeath(Player deadPlayer) {

    }

    @Override
    public void onKill(Player killer) {
        PlayerInventory inv = killer.getInventory();
        if(inv.getItemInHand().getType().equals(Material.COOKED_BEEF)
                && !TazPvP.achievementsManager.playerCompletedAchievement(this, killer)) {
            onAchievement(killer);
        }
    }

    @Override
    public void onAchievement(Player player) {
        TazPvP.achievementsManager.addAchievement(this, player);
        //player.sendMessage(ChatColor.AQUA + "You " + ChatColor.DARK_AQUA + "have completed the" + ChatColor.RED +" Starting Beef " + ChatColor.DARK_AQUA + " achievement!");
        player.sendMessage(ChatColor.AQUA + player.getName() + ChatColor.DARK_AQUA + " has completed the " + ChatColor.RED + "Starting Beef " + ChatColor.DARK_AQUA + "achievement!");
        redeemRewards(player);
    }
}
