package net.ntdi.tazpvp.achievements;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Achievements {
    public final Requirement requirement;
    public int count;
    public String prefix;
    public String name;
    public String description;
    public Rewards[] rewards;
    public int[] rewardAmount;

    public Achievements(String name, String description, Requirement req, int count, String prefix, Rewards[] rewards, int[] rewardAmount) {
        this.requirement = req;
        this.count = count;
        this.prefix = prefix;
        this.rewards = rewards;
        this.rewardAmount = rewardAmount;
        this.name = name;
        this.description = description;
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
    //public abstract void onSmack(Player smacker);
    public abstract void onAchievement(Player player);
    public void redeemRewards(Player player) {
        for(int i = 0; i < rewards.length; i++) {
            switch (rewards[i]) {
                case MONEY:
                    player.sendMessage(ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "$" + rewardAmount[i]);
                    TazPvP.statsManager.addMoney(player, rewardAmount[i]);
                    break;
                case POINTS:
                    player.sendMessage(ChatColor.GRAY + "Reward: " + ChatColor.BLUE + rewardAmount[i] + "Points");
                    TazPvP.statsManager.addPoints(player, rewardAmount[i]);
                    break;
                case EXP:
                    player.sendMessage(ChatColor.GRAY + "Reward: " + ChatColor.DARK_AQUA + rewardAmount[i] + "EXP");
                    player.giveExp(rewardAmount[i]);
                    break;
            }
        }
    }
    public String[] getStringRewards(){
        String[] rewardString = new String[rewards.length];
        for(int i = 0; i < rewards.length; i++){
            switch(rewards[i]){
                case MONEY:
                    rewardString[i] = ChatColor.WHITE + "" + rewardAmount[i] + ChatColor.YELLOW + " Coins";
                    break;
                case EXP:
                    rewardString[i] = ChatColor.WHITE + "" + rewardAmount[i] + ChatColor.GREEN + " EXP";
                    break;
                case POINTS:
                    rewardString[i] = ChatColor.WHITE + "" + rewardAmount[i] + ChatColor.BLUE + " Points";
                    break;
            }
        }
        return rewardString;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
