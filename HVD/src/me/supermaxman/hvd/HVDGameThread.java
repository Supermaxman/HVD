package me.supermaxman.hvd;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HVDGameThread extends Thread {

private final HVDGame game;
private final HVD plugin;

public HVDGameThread(HVD pl, HVDGame g){
    setName("HVD-Thread-"+getId());
    game = g;
    plugin = pl;
}
	synchronized public void run() {
    	try {
		game.setEnded(true);
        int start = game.getStartTime();
        this.wait(20*1000);
        while(HVD.players.size()<game.getMinPlayers()) {
            int i = game.getMinPlayers()-HVD.players.size();
            plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: Game requires " +ChatColor.GOLD+i+ChatColor.AQUA+" more players to begin!");
            this.wait(10*1000);
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: Game beginning in " +ChatColor.GOLD+start+ChatColor.AQUA+" seconds!");
     	start = start/2;
        this.wait(start*1000);
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: Game beginning in " +ChatColor.GOLD+start+ChatColor.AQUA+" seconds!");
     	start = start/2;
     	this.wait(start*1000);
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: Game beginning in " +ChatColor.GOLD+start+ChatColor.AQUA+" seconds!");
     	this.wait(start*1000);
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.GOLD+"Game Start"+ChatColor.AQUA+"!");
        game.setEnded(false);
        game.setHunter(game.chooseHunter());
        startGame();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.RED+game.getHunter()+ChatColor.AQUA+" is the Hunter! You have 10 seconds to run and hide before he is given his gear!");
        this.wait(10*1000);
        equipHunter();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.RED+game.getHunter()+ChatColor.AQUA+" is hunting!");
        //start
        
        int timer = game.getTimeLimit();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain in this round!");
     	timer = timer/2;
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain!");
     	timer = timer/2;
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain!");
     	timer = timer/2;
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain!");
     	timer = timer/2;
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	this.wait(timer*1000/4);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain!");
     	this.wait(timer*1000);
        if(game.isEnded()) {
        	winGame();
        	return;
        }
     	
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.GOLD+"Game End"+ChatColor.AQUA+"!");
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.GOLD+"Deer"+ChatColor.AQUA+" have won!!");
        endGame();
		} catch (InterruptedException e) {
			HVD.log.warning("[" + plugin.getName() + "] Game interupted by server.");
		}
    	
        this.interrupt();   
    } 
    
	synchronized void winGame() {
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.GOLD+"Game End"+ChatColor.AQUA+"!");
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.GOLD+game.getHunter()+ChatColor.AQUA+" has won as Hunter!");
        endGame();
	}
	synchronized void endGame() {
        for(Player p : plugin.getServer().getOnlinePlayers()) {
        	p.getInventory().clear();
        	p.teleport(new Location(p.getWorld(), game.getLobyLocationX(), game.getLobyLocationY(), game.getLobyLocationZ()));
        	HVD.players = new ArrayList<String>();
        }
        HVD.startGame();
	}
	synchronized void startGame() {
        for(Player p : plugin.getServer().getOnlinePlayers()) {
        	if(HVD.players.contains(p.getName())) {
        		p.teleport(new Location(p.getWorld(), game.getGameLocationX(),game.getGameLocationY(),game.getGameLocationZ()));
        	}
        }
	}
	@SuppressWarnings("deprecation")
	synchronized void equipHunter() {
		Player p = plugin.getServer().getPlayer(game.getHunter());
		//give items
		ItemStack i = new ItemStack(Material.IRON_HELMET);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		p.getInventory().setHelmet(i);
		i = new ItemStack(Material.IRON_CHESTPLATE);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		p.getInventory().setChestplate(i);
		i = new ItemStack(Material.IRON_LEGGINGS);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		p.getInventory().setLeggings(i);
		i = new ItemStack(Material.IRON_BOOTS);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		p.getInventory().setBoots(i);
		i = new ItemStack(Material.DIAMOND_SWORD);
		i.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		p.setItemInHand(i);
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, game.getTimeLimit(), 2, true));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, game.getTimeLimit(), 1, true));
		
	}
}