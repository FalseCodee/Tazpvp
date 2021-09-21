package net.ntdi.tazpvp.achievements;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Achievements {
    public Requirement requirement;
    public int count;
    public String prefix;
    public Rewards[] rewards;
    public int[] rewardAmount;

    public Achievements(Requirement req, int count, String prefix, Rewards[] rewards, int[] rewardAmount) {
        this.requirement = req;
        this.count = count;
        this.prefix = prefix;
        this.rewards = rewards;
        this.rewardAmount = rewardAmount;
    }

    public Achievements(Requirement req) {
        this.requirement = req;
    }

    public Requirement getRequirement() {
        return this.requirement;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public abstract void onDeath(Player deadPlayer);
    public abstract void onKill(Player killer);
    public abstract void onAchievement(Player player);
    public void redeemRewards(Player player) {
        for(int i = 0; i < rewards.length; i++) {
            switch (rewards[i]) {
                case MONEY:
                    player.sendMessage("+" + rewardAmount[i] + ChatColor.YELLOW + " Coins");
                    break;
                case POINTS:
                    player.sendMessage("+" + rewardAmount[i]+ ChatColor.BLUE +" Points");
                    break;
                case EXP:
                    player.sendMessage("+ " + rewardAmount[i] + ChatColor.GREEN + " EXP");
                    break;
            }
        }
    }
}
