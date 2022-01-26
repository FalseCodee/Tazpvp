package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.commands.functions.BountyCommand;
import net.ntdi.tazpvp.listeners.passive.combatLog;
import net.ntdi.tazpvp.managers.Duels.DuelManager;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DeathListener implements Listener {

    private final Random rand = new Random();
    public static HashMap<Player, Player> revenge = new HashMap<>();

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (!event.getEntity().getWorld().getName().equals("spawn")) {
                Player p = (Player) event.getEntity();

                if (p.getHealth()-event.getFinalDamage() <= 0) {
                    event.setCancelled(true);
                    Location deadLoc = p.getLocation();

                    p.setGameMode(GameMode.SPECTATOR);
                    p.playSound(p.getLocation(), Sound.WOLF_WHINE, 1, 1);

                    if (event instanceof EntityDamageByEntityEvent) {
                        if (isDueling(p)) {
                            TazPvP.duelManager.endDuel(p, Bukkit.getPlayer(new DuelManager().getOpponent(p)));
                            return;
                        } else {
                            deathFunction(p, ((EntityDamageByEntityEvent) event).getDamager());
                        }
                    }
                    dropInv(p, deadLoc);
                    p.getInventory().clear();


                    p.setMetadata("respawning", new FixedMetadataValue(TazPvP.getInstance(), true));
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            p.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 50, 0.5, 180, 0));
                            p.setGameMode(GameMode.ADVENTURE);
                            p.setHealth(20);
                            p.setFoodLevel(20);
                            rsInv(p);
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                            if (combatLog.combatLog.containsKey(p)) {
                                combatLog.combatLog.remove(p);
                                p.sendMessage(ChatColor.RED + "You are no longer in combat.");
                            }
                            p.setMetadata("respawning", new FixedMetadataValue(TazPvP.getInstance(), false));
                        }
                    }.runTaskLater(TazPvP.getInstance(), 60);
                }
            }
        }
    }

    public void deathFunction(Player p, Entity killee) {
        if (killee instanceof Player) {
            Player killer = (Player) killee;
            if (p != null) {
                if (killer != p) {
                    TazPvP.achievementsManager.onDeath(p);
                    Location loc = p.getLocation();
                    TazPvP.achievementsManager.onKill(killer);
                    if (BountyCommand.bounties.get(p.getUniqueId()) != null) {
                        TazPvP.statsManager.addMoney(killer, BountyCommand.bounties.get(p.getUniqueId()));
                        TazPvP.statsManager.checkLevelUp(killer);
                        killer.sendMessage(ChatColor.YELLOW + "You have claimed " + p.getDisplayName() + "'s bounty for " + ChatColor.WHITE + "$" + BountyCommand.bounties.get(p.getUniqueId()));
                        BountyCommand.bounties.remove(p.getUniqueId());
                    }
                    boolean fat = TazPvP.perkManager.getFat(killer);
                    boolean rebirthed = TazPvP.statsManager.getRebirths(killer) > 0;

                    if (fat && rebirthed) {
                        if (killer.getMaxHealth() != 30){
                            killer.setMaxHealth(killer.getMaxHealth()+2);
                        }
                    } else if (fat || rebirthed) {
                        if (killer.getMaxHealth() != 28){
                            killer.setMaxHealth(killer.getMaxHealth()+2);
                        }
                    } else {
                        if (killer.getMaxHealth() != 26){
                            killer.setMaxHealth(killer.getMaxHealth()+2);
                        }
                    }

                    p.setMaxHealth(20);


                    p.playSound(p.getLocation(), Sound.FIRE, 5, 1);
                    TazPvP.statsManager.addStreak(killer, 1);
                    TazPvP.statsManager.addKills(killer, 1);
                    TazPvP.statsManager.addMoney(killer, 7);

                    if ((TazPvP.statsManager.getStreak(killer) % 5) == 0) {
                        Bukkit.broadcastMessage(ChatColor.GOLD + killer.getDisplayName() + ChatColor.YELLOW + " has a kill streak of " + ChatColor.GOLD + TazPvP.statsManager.getStreak(killer));
                        TazPvP.statsManager.addMoney(killer, 25);
                        killer.sendMessage(ChatColor.GOLD + "+ $" + 25);
                    }


                    if (TazPvP.statsManager.getRebirths(killer) > 0) {
                        TazPvP.statsManager.addExp(killer, 8);
                        killer.playSound(killer.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1 );
                        killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, true, false));
                        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true, false));
                        killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*4, 2, true, false));
                    } else {
                        TazPvP.statsManager.addExp(killer, 5);
                        killer.playSound(killer.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1 );
                        killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*4, 2, true, false));

                        if (rand.nextInt(10) == 1) {
                            if (TazPvP.perkManager.getButter(killer)){
                                killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*4, 0));
                                killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*4, 0));
                                //killer.sendMessage(ChatColor.GRAY + "Butter Perk activated!");
                            }
                        }
                        if (rand.nextInt(10) == 2) {
                            if (TazPvP.perkManager.getAgility(killer)){
                                killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*5, 0));
                                //killer.sendMessage(ChatColor.GRAY + "Agility Perk activated!");
                            }
                        }
                        if (rand.nextInt(10) == 3) {
                            if (TazPvP.perkManager.getExtinguish(killer)){
                                killer.setFireTicks(0);
                                //killer.sendMessage(ChatColor.GRAY + "Extinguish Perk activated!");
                            }
                        }
                        if (rand.nextInt(10) == 4) {
                            if (TazPvP.perkManager.getHunger(killer)){
                                killer.setFoodLevel(20);
                                //killer.sendMessage(ChatColor.GRAY + "Hunger Perk activated!");
                            }
                        }
                        if(rand.nextInt(10) == 5){
                            if (TazPvP.perkManager.getStrength(killer)){
                                //killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1, true, false));
                            }
                        }

                    }
                    if (Bukkit.getOnlinePlayers().size() < 20) {
                        for (Player d : Bukkit.getOnlinePlayers()) {
                            if (Objects.equals(d.getName(), killer.getName())) {
                                if (TazPvP.statsManager.getRebirths(killer) > 0) {
                                    killer.sendMessage(ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + "" + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 8 Experience");
                                } else {
                                    killer.sendMessage(ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + "" + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 5 Experience");
                                }
                            } else {
                                d.sendMessage(ChatColor.GRAY + killer.getName() + ChatColor.DARK_GRAY + " killed " + ChatColor.GRAY + p.getName());
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.DARK_GRAY + "You were killed by " + ChatColor.GRAY + "" + killer.getName());
                    }
                    TazPvP.statsManager.setStreak(p, 0);
                    TazPvP.statsManager.addDeaths(p, 1);
                    //loc.getWorld().playEffect(loc, Effect.LARGE_SMOKE, Material.REDSTONE_BLOCK);
                }
            }
        } else if (killee instanceof Arrow){
            Arrow arrow = (Arrow) killee;
            Player killer = (Player) arrow.getShooter();
            if (killer != null){
                if (p != null) {
                    if (killer != p) {
                        TazPvP.achievementsManager.onDeath(p);
                        Location loc = p.getLocation();
                        TazPvP.achievementsManager.onKill(killer);
                        if (BountyCommand.bounties.get(p.getUniqueId()) != null) {
                            TazPvP.statsManager.addMoney(killer, BountyCommand.bounties.get(p.getUniqueId()));
                            TazPvP.statsManager.checkLevelUp(killer);
                            killer.sendMessage(ChatColor.YELLOW + "You have claimed " + p.getDisplayName() + "'s bounty for " + ChatColor.WHITE + "$" + BountyCommand.bounties.get(p.getUniqueId()));
                            BountyCommand.bounties.remove(p.getUniqueId());
                        }

                        if (combatLog.combatLog.containsKey(p)) {
                            combatLog.combatLog.remove(p);
                            p.sendMessage(ChatColor.GREEN + "You have been removed from combat log.");
                        }
                        if (combatLog.combatLog.containsKey(killer)) {
                            combatLog.combatLog.remove(killer);
                            killer.sendMessage(ChatColor.GREEN + "You have been removed from combat log.");
                        }

                        boolean fat = TazPvP.perkManager.getFat(killer);
                        boolean rebirthed = TazPvP.statsManager.getRebirths(killer) > 0;

                        if (fat && rebirthed) {
                            if (killer.getMaxHealth() != 30){
                                killer.setMaxHealth(killer.getMaxHealth()+2);
                            }
                        } else if (fat || rebirthed) {
                            if (killer.getMaxHealth() != 28){
                                killer.setMaxHealth(killer.getMaxHealth()+2);
                            }
                        } else {
                            if (killer.getMaxHealth() != 26){
                                killer.setMaxHealth(killer.getMaxHealth()+2);
                            }
                        }

                        p.setMaxHealth(20);

                        p.playSound(p.getLocation(), Sound.FIRE, 5, 1);
                        TazPvP.statsManager.addStreak(killer, 1);
                        TazPvP.statsManager.addKills(killer, 1);
                        TazPvP.statsManager.addMoney(killer, 7);

                        if ((TazPvP.statsManager.getStreak(killer) % 5) == 0) {
                            Bukkit.broadcastMessage(ChatColor.GOLD + killer.getDisplayName() + ChatColor.YELLOW + " has a kill streak of " + ChatColor.GOLD + TazPvP.statsManager.getStreak(killer));
                            TazPvP.statsManager.addMoney(killer, 25);
                            killer.sendMessage(ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "$" + 25);
                        }


                        if (TazPvP.statsManager.getRebirths(killer) > 0) {
                            TazPvP.statsManager.addExp(killer, 8);
                            killer.playSound(killer.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1 );
                            killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, true, false));
                            killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true, false));
                            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 92, 2, true, false));
                        } else {
                            TazPvP.statsManager.addExp(killer, 5);
                            killer.playSound(killer.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1 );
                            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 92, 2, true, false));

                            if (TazPvP.perkManager.getButter(killer)){
                                killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*4, 0));
                                killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*3, 0));
                                //killer.sendMessage(ChatColor.GRAY + "Butter Perk activated!");
                            }
                            if (TazPvP.perkManager.getAgility(killer)){
                                killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*3, 0));
                                //killer.sendMessage(ChatColor.GRAY + "Agility Perk activated!");
                            }
                            if (TazPvP.perkManager.getExtinguish(killer)){
                                killer.setFireTicks(0);
                                //killer.sendMessage(ChatColor.GRAY + "Extinguish Perk activated!");
                            }
                            if (TazPvP.perkManager.getHunger(killer)){
                                killer.setFoodLevel(20);
                                //killer.sendMessage(ChatColor.GRAY + "Hunger Perk activated!");
                            }
                            if (TazPvP.perkManager.getStrength(killer)){
                                killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 7, 1, true, false));
                            }

                        }
                        if (Bukkit.getOnlinePlayers().size() < 20){
                            Bukkit.broadcastMessage(ChatColor.GRAY + killer.getName() + ChatColor.DARK_GRAY + " has killed " + ChatColor.GRAY + p.getName() );
                        }
                        TazPvP.statsManager.setStreak(p, 0);
                        TazPvP.statsManager.addDeaths(p, 1);
                        //loc.getWorld().playEffect(loc, Effect.LARGE_SMOKE, Material.REDSTONE_BLOCK);
                    }
                }
            }
        }
    }

    public void dropInv(Player p, Location loc) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null) {
                p.getWorld().dropItemNaturally(loc, item);
            }
        }
        for (ItemStack item : p.getInventory().getArmorContents()) {
            if (item != null) {
                if (item.getType() != Material.AIR) {
                    p.getWorld().dropItemNaturally(loc, item);
                }
            }
        }
    }
    //clear inv cuz why not
    public void rsInv(Player player){
        ItemStack armor1 = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack armor2 = new ItemStack(Material.LEATHER_HELMET);
        ItemStack armor3 = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack armor4 = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
        ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack blocks = new ItemStack(Material.WOOD, 8);
        ItemStack arrow = new ItemStack(Material.ARROW, 5);

        ItemMeta meta1 = armor1.getItemMeta();
        meta1.spigot().setUnbreakable(false);
        armor1.setItemMeta(meta1);

        ItemMeta meta2 = armor2.getItemMeta();
        meta2.spigot().setUnbreakable(false);
        armor2.setItemMeta(meta2);

        ItemMeta meta3 = armor3.getItemMeta();
        meta3.spigot().setUnbreakable(false);
        armor3.setItemMeta(meta3);

        ItemMeta meta4 = armor4.getItemMeta();
        meta4.spigot().setUnbreakable(false);
        armor4.setItemMeta(meta4);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.spigot().setUnbreakable(false);
        sword.setItemMeta(swordMeta);

        ItemMeta pickaxeMeta = sword.getItemMeta();
        pickaxeMeta.spigot().setUnbreakable(false);
        pickaxe.setItemMeta(pickaxeMeta);

        ItemMeta fishingMeta = fishingrod.getItemMeta();
        fishingMeta.spigot().setUnbreakable(false);
        fishingrod.setItemMeta(fishingMeta);

        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.spigot().setUnbreakable(false);
        bow.setItemMeta(bowMeta);


        PlayerInventory inv = player.getInventory();
        inv.setLeggings(armor4);
        inv.setChestplate(armor3);
        inv.setHelmet(armor2);
        inv.setBoots(armor1);
        inv.addItem(sword);
        inv.addItem(fishingrod);
        inv.addItem(bow);
        inv.addItem(steak);
        inv.addItem(blocks);
        inv.setItem(9, arrow);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Player killer = p.getKiller();
        if (killer != null) {
            if (killer != p) {
               deathFunction(p, killer);
                TazPvP.statsManager.setStreak(p, 0);
                TazPvP.statsManager.addDeaths(p, 1);
                //loc.getWorld().playEffect(loc, Effect.LARGE_SMOKE, Material.REDSTONE_BLOCK);
            }
        }
    }



    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player && e.getEntity().getWorld().getName().equals("arena")) {
            Player whoWasHit = (Player) e.getEntity();
            Player whoHit = (Player) e.getDamager();
            // Isn't this supposed to only be when they punch each other?
            if(whoHit.getItemInHand().getType() == Material.AIR) {
                TazPvP.achievementsManager.onSmack(whoHit);
                TazPvP.statsManager.addSmacks(whoHit, 1);
            }

        }
    }

    public boolean isDueling(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("dueling");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }

    public boolean isRespawning(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("respawning");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }

    public String whatMap(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("map");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asString();
        }
        return "";
    }

    public String getOpponent(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("opponent");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asString();
        }
        return "";
    }

    public String getCombatee(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("combat");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asString();
        }
        return "";
    }
}
