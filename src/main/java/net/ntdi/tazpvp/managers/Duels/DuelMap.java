package net.ntdi.tazpvp.managers.Duels;


import org.bukkit.Location;

public class DuelMap {
    public final Location player1Spawn;
    public final Location player2Spawn;
    public final boolean isActive;
    public final String identifier;

    public DuelMap(String identifier, Location loc1, Location loc2) {
        this.identifier = identifier;
        this.player1Spawn = loc1;
        this.player2Spawn = loc2;
        this.isActive = false;
    }
}
