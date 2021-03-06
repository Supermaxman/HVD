package me.supermaxman.hvd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;



public class HVD extends JavaPlugin {
    public static FileConfiguration conf;
	public static HVD plugin;
	public static final Logger log = Logger.getLogger("Minecraft");
	public static HVDGame game;
	
	public static ArrayList<String> players = new ArrayList<String>();
	static HashMap<String, Long> cooldowns = new HashMap<String, Long>();

	public void onEnable() {
		plugin = this;

		saveDefaultConfig();
        getCommand("hvd").setExecutor(new HVDExecutor(this));    
        
		getServer().getPluginManager().registerEvents(new HVDListener(), plugin);
		
		log.info(getName() + " has been enabled.");
		startGame();
	}
	
	

	static void startGame() {
		
		try {
			plugin.reloadConfig();
			conf = plugin.getConfig();
			int min = Integer.parseInt(HVD.conf.getString("settings.arena.minplayers"));
			int max = Integer.parseInt(HVD.conf.getString("settings.arena.maxplayers"));
			int x = Integer.parseInt(HVD.conf.getString("settings.arena.lobbylocationx"));
			int y = Integer.parseInt(HVD.conf.getString("settings.arena.lobbylocationy"));
			int z = Integer.parseInt(HVD.conf.getString("settings.arena.lobbylocationz"));
			int start = Integer.parseInt(HVD.conf.getString("settings.arena.starttime"));
			int limit = Integer.parseInt(HVD.conf.getString("settings.arena.timelimit"));
			int at = Integer.parseInt(HVD.conf.getString("settings.arena.appletimer"));
			int x2 = Integer.parseInt(HVD.conf.getString("settings.arena.gamelocationx"));
			int y2 = Integer.parseInt(HVD.conf.getString("settings.arena.gamelocationy"));
			int z2 = Integer.parseInt(HVD.conf.getString("settings.arena.gamelocationz"));
			
			int x3 = Integer.parseInt(HVD.conf.getString("settings.arena.applelocationx"));
			int y3 = Integer.parseInt(HVD.conf.getString("settings.arena.applelocationy"));
			int z3 = Integer.parseInt(HVD.conf.getString("settings.arena.applelocationz"));
			
			game = new HVDGame(min,max, x, y, z, start, limit, x2, y2, z2, x3, y3, z3, at);
			
			
		} catch (Exception e) {
			log.warning("[" + plugin.getName() + "] Settings are invalid in config.yml! Could not load the values.");
			e.printStackTrace();
		}
	}
	
	
	
	public void onDisable() {
		log.info(getName() + " has been disabled.");
		game.getThread().end = true;
		game.getThreadHandle().go = false;
		saveConfig();
	}
	
}