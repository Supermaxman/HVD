package me.supermaxman.hvd;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

	
public class HVDListener implements Listener {
	
	
	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e) {
		e.getDrops().clear();
		Player p = e.getEntity();
		if(HVD.players.contains(p.getName())) {
	        p.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.RED+p.getName()+ChatColor.RED+" has died!");
			HVD.players.remove(p.getName());
        	p.getInventory().clear();
			if(HVD.game.getHunter().equals(p.getName())) {
				HVD.game.setEnded(true);
			}
			if(HVD.players.size()==1) {
				HVD.game.setEnded(true);
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
	public void onPlayerMove(PlayerMoveEvent e) {
		if(e.getPlayer().getName().equals(HVD.game.getHunter()) && HVD.game.isEnded()) {
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
							if(!HVD.players.contains(e.getPlayer().getName())) {
								HVD.players.add(e.getPlayer().getName());
					            int i = HVD.game.getMinPlayers()-HVD.players.size();
								e.getPlayer().sendMessage(ChatColor.AQUA+"[HVD]: Joined game, needs "+ChatColor.GOLD+i+ChatColor.AQUA+" more players!");
							}else {
								e.getPlayer().sendMessage(ChatColor.RED+"[HVD]: You have already joined!");
							}
						}else {
							e.getPlayer().sendMessage(ChatColor.RED+"[HVD]: Cannot join, game is full!");
						}
					}else {
						e.getPlayer().sendMessage(ChatColor.RED+"[HVD]: Cannot join, game is active!");
					}
				}
			}
		}
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player p = e.getPlayer();
			if(p.getItemInHand()!=null) {
				ItemStack i = p.getItemInHand();
				if(i.hasItemMeta()) {
					if(i.getItemMeta().hasDisplayName()) {
						if(i.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Sprint")) {
							if(HVD.cooldowns.containsKey(p.getName())) {
								if(HVD.cooldowns.get(p.getName())+15000 < System.currentTimeMillis()) {
									HVD.cooldowns.put(p.getName(), System.currentTimeMillis());
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3, true));
									e.getPlayer().sendMessage(ChatColor.AQUA+"[HVD]: Sprinting!");
								}else {
									e.getPlayer().sendMessage(ChatColor.RED+"[HVD]: Sprint is on cooldown!");
								}
							}else {
								HVD.cooldowns.put(p.getName(), System.currentTimeMillis());
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3, true));
								e.getPlayer().sendMessage(ChatColor.AQUA+"[HVD]: Sprinting!");
							}							
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		ItemStack i = e.getItem().getItemStack();
		if(i.hasItemMeta()) {
			if(i.getItemMeta().hasDisplayName()) {
				if(i.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Apple")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 3, false));
					p.getInventory().remove(i);
					p.updateInventory();
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
	
	@EventHandler
	public void onItemDrop(ItemSpawnEvent e) {
		if(!(e.getEntity().getItemStack().getType()==Material.APPLE)) {
			e.setCancelled(true);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if(e instanceof EntityDamageByEntityEvent) {
			if(((EntityDamageByEntityEvent) e).getDamager() instanceof Player && e.getEntity() instanceof Player) {
				Player p = (Player) ((EntityDamageByEntityEvent) e).getDamager();
				Player d = (Player) e.getEntity();
				if(p.getItemInHand()!=null) {
					ItemStack i = p.getItemInHand();
					if(i.hasItemMeta()) {
						if(i.getItemMeta().hasDisplayName()) {
							if(i.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Horn")){
								e.setDamage(20);
							}
						}
					}
				}
				if(!p.getName().equals(HVD.game.getHunter()) && !d.getName().equals(HVD.game.getHunter())) {
					e.setCancelled(true);
				}
			}
		}
		if (e instanceof Player) {
			Player p = ((Player) e).getPlayer();
			if(p.getName().equals(HVD.game.getHunter()) && HVD.game.isEnded()) {
				e.setCancelled(true);
			}
		}
	}
	
	
}
