package me.supermaxman.hvd;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HVDExecutor extends BaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if(player.isOp()){
        	if(args.length>=1) {
        		String s = args[0];
        		if(s.equalsIgnoreCase("setlobby")) {
        			int x = player.getLocation().getBlockX();
        			int y = player.getLocation().getBlockY();
        			int z = player.getLocation().getBlockZ();
        			HVD.conf.set("settings.arena.lobbylocationx", x);
        			HVD.conf.set("settings.arena.lobbylocationy", y);
        			HVD.conf.set("settings.arena.lobbylocationz", z);
        			HVD.game.setLobyLocationX(x);
        			HVD.game.setLobyLocationX(y);
        			HVD.game.setLobyLocationX(z);
        			HVD.plugin.saveConfig();
                	player.sendMessage(ChatColor.AQUA+"[HVD]: Lobby now set at current location.");
        		}else if(s.equalsIgnoreCase("setgame")) {
        			int x = player.getLocation().getBlockX();
        			int y = player.getLocation().getBlockY();
        			int z = player.getLocation().getBlockZ();
        			HVD.conf.set("settings.arena.gamelocationx", x);
        			HVD.conf.set("settings.arena.gamelocationy", y);
        			HVD.conf.set("settings.arena.gamelocationz", z);
        			HVD.game.setGameLocationX(x);
        			HVD.game.setGameLocationY(y);
        			HVD.game.setGameLocationZ(z);
        			HVD.plugin.saveConfig();
                	player.sendMessage(ChatColor.AQUA+"[HVD]: Game now set at current location.");
        		}else if(s.equalsIgnoreCase("starttime")&&(args.length>=2)) {
        			try {
            			int t = Integer.parseInt(args[1]);
            			HVD.conf.set("settings.arena.starttime", t);
            			HVD.plugin.saveConfig();
                    	player.sendMessage(ChatColor.AQUA+"[HVD]: Start time changed to "+ ChatColor.GOLD+t+ChatColor.AQUA+".");
        			}catch(Exception e) {
                    	player.sendMessage(ChatColor.RED+"[HVD]: Command used incorrectly, please only set numbers for time.");
        			}
        		}else if(s.equalsIgnoreCase("minplayers")&&(args.length>=2)) {
        			try {
            			int t = Integer.parseInt(args[1]);
            			HVD.conf.set("settings.arena.minplayers", t);
            			HVD.game.setMinPlayers(t);
            			HVD.plugin.saveConfig();
                    	player.sendMessage(ChatColor.AQUA+"[HVD]: Minimum player limit time changed to "+ ChatColor.GOLD+t+ChatColor.AQUA+".");
        			}catch(Exception e) {
                    	player.sendMessage(ChatColor.RED+"[HVD]: Command used incorrectly, please only set numbers for limits.");
        			}
        		}else if(s.equalsIgnoreCase("maxplayers")&&(args.length>=2)) {
        			try {
            			int t = Integer.parseInt(args[1]);
            			HVD.conf.set("settings.arena.maxplayers", t);
            			HVD.game.setMaxPlayers(t);
            			HVD.plugin.saveConfig();
                    	player.sendMessage(ChatColor.AQUA+"[HVD]: Maximum player limit time changed to "+ ChatColor.GOLD+t+ChatColor.AQUA+".");
        			}catch(Exception e) {
                    	player.sendMessage(ChatColor.RED+"[HVD]: Command used incorrectly, please only set numbers for limits.");
        			}
        		}else {
                	player.sendMessage(ChatColor.RED+"[HVD]: Command used incorrectly, use /hvd setlobby,setgame,starttime [time],minplayers [limit],maxplayers [limit].");
        		}
        	}else {
            	player.sendMessage(ChatColor.RED+"[HVD]: Command used incorrectly, use /hvd setlobby,setgame,starttime [time],minplayers [limit],maxplayers [limit].");
        	}
        }else {
        	player.sendMessage(ChatColor.RED+"[HVD]: You do not have permission to use this command.");
        }
    }

    public HVDExecutor(HVD pl) {
        super(pl);
    }
}
