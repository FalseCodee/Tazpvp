package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.listeners.function.ZombieLogic;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnZombieCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "Spawned new zombieLogic");
        new ZombieLogic().spawnZombie();
        return true;
    }
}
