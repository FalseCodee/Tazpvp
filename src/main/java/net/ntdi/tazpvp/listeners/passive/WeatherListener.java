package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WeatherListener implements Listener {

    @EventHandler
    public void onWeatherChange(org.bukkit.event.weather.WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}
