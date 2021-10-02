package net.ntdi.tazpvp.managers;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PapiManager extends PlaceholderExpansion implements Relational {

    private final TazPvP plugin;

    public PapiManager(TazPvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "main";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ntdi";
    }

    @Override
    public @NotNull String getVersion() {
        return "6.9";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onPlaceholderRequest(Player one, Player two, String s) {

        return null; // Placeholder is unknown by the Expansion
    }
}
