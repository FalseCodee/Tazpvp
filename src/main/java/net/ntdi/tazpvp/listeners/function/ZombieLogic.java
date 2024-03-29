package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ZombieLogic implements Listener {

    final World world = Bukkit.getWorld("grind");

    final double xzomb = 37.5;
    final double yzomb = 41;
    final double zzomb = -96.5;

    final double xskeleton = 46.5;
    final double yskeleton = 41;
    final double zskeleton = -121.5;

    final double xopskeleton = 65.5;
    final double yopskeleton = 41;
    final double zopskeleton = -112.5;

    final double xbaby = 59.5;
    final double ybaby = 42;
    final double zbaby = -88.5;

    @EventHandler
    public void onZombieDie(EntityDeathEvent event){
        if (event.getEntity() instanceof Zombie && event.getEntity().getName().equals(ChatColor.RED + "" + "Ntdi's Henchmen")){

            Random rand = new Random();
            if (event.getEntity().getKiller() != null){
                TazPvP.statsManager.addExp(event.getEntity().getKiller(), 3);
                TazPvP.statsManager.checkLevelUp(event.getEntity().getKiller());
            }

            new BukkitRunnable(){
                @Override
                public void run() {
                    spawnZombie();
                }
            }.runTaskLater(TazPvP.getInstance(), rand.nextInt(30) * 20);
        }
    }

    public void spawnZombie(){
        //System.out.println("Spawn zombieLogic method called");
        Location loc = new Location(world, xzomb, yzomb, zzomb);
        Zombie zom = (Zombie) world.spawnEntity(loc, EntityType.ZOMBIE);
        zom.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        zom.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        zom.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        zom.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        zom.setCustomName(ChatColor.RED + "" + "Ntdi's Henchmen");
        zom.setCustomNameVisible(true);
        zom.setVillager(false);
        zom.setBaby(false);
        zom.setMaxHealth(30);
    }

    public void initZombies(Integer amount, Integer delay){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < amount; i++) {
                    spawnZombie();
                }
            }
        }.runTaskLater(TazPvP.getInstance(), 200L);
    }



    @EventHandler
    public void onSkeletonDie(EntityDeathEvent event){
        if (event.getEntity() instanceof Skeleton && event.getEntity().getName().equals(ChatColor.BLUE + "" + "Rownox's Archers")){

            Random rand = new Random();
            if (event.getEntity().getKiller() != null){
                TazPvP.statsManager.addExp(event.getEntity().getKiller(), 5);
                TazPvP.statsManager.checkLevelUp(event.getEntity().getKiller());
            }
            new BukkitRunnable(){
                @Override
                public void run() {
                    spawnSkeleton();
                }
            }.runTaskLater(TazPvP.getInstance(), rand.nextInt(45)*20);
        }
    }

    public void spawnSkeleton(){
        Location loc = new Location(world, xskeleton, yskeleton, zskeleton);
        Skeleton zom = (Skeleton) world.spawnEntity(loc, EntityType.SKELETON);
        zom.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
        zom.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
        zom.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        zom.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
        zom.setCustomName(ChatColor.BLUE + "" + "Rownox's Archers");
        zom.setCustomNameVisible(true);
        zom.setMaxHealth(25);
    }

    public void initSkelton(Integer amount){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < amount; i++) {
                    spawnSkeleton();
                }
            }
        }.runTaskLater(TazPvP.getInstance(), 200L);
    }




    @EventHandler
    public void onOpSkeletonDie(EntityDeathEvent event){
        if (event.getEntity() instanceof Skeleton && event.getEntity().getName().equals(ChatColor.AQUA + "" + "Rownox's Super Archers")){

            Random rand = new Random();
            if (event.getEntity().getKiller() != null){
                TazPvP.statsManager.addExp(event.getEntity().getKiller(), 6);
                TazPvP.statsManager.checkLevelUp(event.getEntity().getKiller());
            }
            new BukkitRunnable(){
                @Override
                public void run() {
                    spawnOpSkeleton();
                }
            }.runTaskLater(TazPvP.getInstance(), rand.nextInt(90)*20);
        }
    }

    public void spawnOpSkeleton(){
        Location loc = new Location(world, xopskeleton, yopskeleton, zopskeleton);
        Skeleton zom = (Skeleton) world.spawnEntity(loc, EntityType.SKELETON);
        zom.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
        zom.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        zom.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
        zom.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
        zom.setCustomName(ChatColor.AQUA + "" + "Rownox's Super Archers");
        zom.setCustomNameVisible(true);
        zom.setMaxHealth(35);
    }

    public void initOpSkeleton(Integer amount){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < amount; i++) {
                    spawnOpSkeleton();
                }
            }
        }.runTaskLater(TazPvP.getInstance(), 200L);
    }

    @EventHandler
    public void onBabyDie(EntityDeathEvent event){
        if (event.getEntity() instanceof Skeleton && event.getEntity().getName().equals(ChatColor.GREEN + "" + "Falsecode's Newborn Son")){

            Random rand = new Random();
            if (event.getEntity().getKiller() != null){
                TazPvP.statsManager.addExp(event.getEntity().getKiller(), 15);
            }
            new BukkitRunnable(){
                @Override
                public void run() {
                    spawnBaby();
                }
            }.runTaskLater(TazPvP.getInstance(), rand.nextInt(135)*20);
        }
    }

    public void spawnBaby(){
        Location loc = new Location(world, xbaby, ybaby, zbaby);
        Skeleton zom = (Skeleton) world.spawnEntity(loc, EntityType.SKELETON);
        zom.setSkeletonType(Skeleton.SkeletonType.WITHER);
        zom.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        zom.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        zom.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        zom.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        zom.setCustomName(ChatColor.GREEN + "" + "Falsecode's Newborn Son");
        zom.setCustomNameVisible(true);
        zom.setMaxHealth(50);
        zom.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
    }

    public void initBaby(Integer amount){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < amount; i++) {
                    spawnBaby();
                }
            }
        }.runTaskLater(TazPvP.getInstance(), 200L);
    }

}
