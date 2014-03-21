package com.chaseoes.simplesigncolor;

import java.io.IOException;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class SimpleSignColor extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults(true);
        saveConfig();

        if (getConfig().getBoolean("auto-update")) {
            new Updater(this, 52037, this.getFile(), Updater.UpdateType.DEFAULT, false);
        }

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (!event.getPlayer().hasPermission("simplesigncolor.use")) return;
        for (int i = 0; i < event.getLines().length; i++) {
            event.setLine(i, ChatColor.translateAlternateColorCodes('&', event.getLine(i)));
        }
    }

}
