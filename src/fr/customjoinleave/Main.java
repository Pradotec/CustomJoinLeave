package fr.customjoinleave;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    private FileConfiguration config = getConfig();
    private static Boolean EnableJoinMessage;
    private static Boolean EnableLeaveMessage;
    private static String JoinMessage;
    private static String LeaveMessage;

    public void onEnable() {
        this.config.addDefault("EnableJoinMessage", Boolean.valueOf(true));
        this.config.addDefault("EnableLeaveMessage", Boolean.valueOf(true));
        this.config.addDefault("JoinMessage", "%player% joined the server");
        this.config.addDefault("LeaveMessage", "%player% left the server");
        EnableJoinMessage = this.config.getBoolean("EnableJoinMessage");
        EnableLeaveMessage = this.config.getBoolean("EnableLeaveMessage");
        JoinMessage = this.config.getString("JoinMessage");
        LeaveMessage = this.config.getString("LeaveMessage");
        this.config.options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (EnableJoinMessage) {
        	e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', JoinMessage.replace("%player%", p.getDisplayName())));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (EnableLeaveMessage) {
        	e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', LeaveMessage.replace("%player%", p.getDisplayName())));
        }
    }
}