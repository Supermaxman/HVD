package me.supermaxman.hvd;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

	
public class HVDListener implements Listener {
	
	
	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if(HVD.players.contains(p.getName())) {
	        p.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.RED+p.getName()+ChatColor.RED+" has died!");
			HVD.players.remove(p.getName());
        	p.getInventory().clear();
			if(HVD.game.getHunter().equals(p.getName())) {
				HVD.game.setEnded(true);
			}
			if(HVD.players.size()==1) {
				HVD.game.getThread().winGame();
			}
	        
		}
	}
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		e.setRespawnLocation(new Location(p.getWorld(), HVD.game.getLobyLocationX(), HVD.game.getLobyLocationY(), HVD.game.getLobyLocationZ()));
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).equalsIgnoreCase("HVD")&&sign.getLine(1).equalsIgnoreCase("join")) {
					if(HVD.game.isEnded()) {
						if(HVD.players.size()<HVD.game.getMaxPlayers()) {
							HVD.players.add(e.getPlayer().getName());
							e.getPlayer().sendMessage(ChatColor.AQUA+"[HVD]: Joined game, starting soon!");
						}else {
							e.getPlayer().sendMessage(ChatColor.RED+"[HVD]: Cannot join, game is full!");
						}
					}else {
						e.getPlayer().sendMessage(ChatColor.RED+"[HVD]: Cannot join, game is active!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerHunger(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			e.setCancelled(true);
			e.setFoodLevel(20);
		}
	}
	
	
}
