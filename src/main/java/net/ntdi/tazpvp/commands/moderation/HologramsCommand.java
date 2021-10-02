package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class HologramsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        World spawn = Bukkit.getWorld("spawn");
        Location hg1 = new Location(spawn, 27.5, 51, -16.5);

        ArmorStand as = (ArmorStand) spawn.spawnEntity(hg1, EntityType.ARMOR_STAND); //Spawn the ArmorStand

        as.setGravity(false); //Make sure it doesn't fall
        as.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
        as.setCustomName(ChatColor.AQUA + "burger king foot lettuce"); //Set this to the text you want
        as.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
        as.setVisible(false); //Makes the ArmorStand invisible
        as.setSmall(true);

        return true;
    }
}
