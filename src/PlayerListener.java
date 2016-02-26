package com.lwer0.justbybungee;

import java.net.InetAddress;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

class PlayerListener
  implements Listener
{
  private final Main plugin;
  String proxyip;
  
  public PlayerListener(Main instance)
  {
    this.plugin = instance;
  }
  
  public static String color(String message)
  {
    return ChatColor.translateAlternateColorCodes('&', message);
  }
  
  @EventHandler
  public void onPJoin(PlayerLoginEvent event)
  {
    this.proxyip = event.getAddress().getHostAddress();
    if (!isProxied(this.proxyip)) {
      event.disallow(PlayerLoginEvent.Result.KICK_OTHER, color(this.plugin.getConfig().getString("Kick-MSG")));
    }
  }
  
  public List<String> getProxys()
  {
    return this.plugin.getConfig().getStringList("Proxy-IPs");
  }
  
  public boolean isProxied(String ip)
  {
    return getProxys().contains(ip);
  }
}
