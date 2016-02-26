package com.lwer0.justbybungee;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
{
  public static Main instance;
  final FileConfiguration config = getConfig();
  
  public void onEnable()
  {
    instance = this;
    getLogger().info("JustbyBungee v3 has been loaded correctly.");
    getServer().getPluginManager().registerEvents(new PlayerListener(instance), instance);
    
    File config = new File(getDataFolder() + File.separator + "config.yml");
    if (!config.exists())
    {
      getConfig().options().copyDefaults(true);
      saveConfig();
    }
  }
  
  public static String color(String message)
  {
    return ChatColor.translateAlternateColorCodes('&', message);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("justbybungee")) {
      if (args.length == 0)
      {
        if (!sender.hasPermission("justbybungee"))
        {
          sender.sendMessage(color("&cYou dont have permissions."));
        }
        else
        {
          sender.sendMessage(color("&c============================================="));
          sender.sendMessage(color("&4/justbybungee reload &e- &cTo reload the plugin"));
          sender.sendMessage(color("&c============================================="));
        }
      }
      else if (args[0].equalsIgnoreCase("reload")) {
        if (!sender.hasPermission("justbybungee.reload"))
        {
          sender.sendMessage(color("&cYou dont have permissions."));
        }
        else
        {
          reloadConfig();
          getLogger().info("The plugin has been reloaded correctly!");
          sender.sendMessage(color("&ePlugin reloaded!"));
        }
      }
    }
    return true;
  }
}
