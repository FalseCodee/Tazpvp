package net.ntdi.tazpvp.utils;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final World spawnWorld = Bukkit.getWorld(TazPvP.configFile.getString("spawn.world"));
    public static final Location spawnLocation = new Location(spawnWorld,
            TazPvP.configFile.getDouble("spawn.x"),
            TazPvP.configFile.getDouble("spawn.y"),
            TazPvP.configFile.getDouble("spawn.z"),
    (float) TazPvP.configFile.getDouble("spawn.yaw"),
    (float) TazPvP.configFile.getDouble("spawn.x"));

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('§', s);
    }




    public static void teleportPlayer(Player player, Location location, int milliseconds) {
        long beginning = System.currentTimeMillis();
        Location origin = player.getLocation();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis() - beginning > milliseconds) {
                    player.teleport(location);
                    this.cancel();
                } else if (player.getLocation().distance(origin) > 2D) {
                    player.sendMessage(ChatColor.DARK_GREEN + "Teleportation canceled, you moved.");
                    this.cancel();
                }
                //Other checks will be added
                // added combat check :D

            }
        }.runTaskTimer(TazPvP.getInstance(), 0L, 10L);
    }

        public static List<String> readFile (File file){
            List<String> data = new ArrayList<>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String str;
                while ((str = br.readLine()) != null) {
                    data.add(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;

        }
}
