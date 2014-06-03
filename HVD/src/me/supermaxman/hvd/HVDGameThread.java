package me.supermaxman.hvd;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HVDGameThread extends Thread {

private final HVDGame game;
private final HVD plugin;
public boolean end;

public HVDGameThread(HVD pl, HVDGame g){
    setName("HVD-Thread-"+getId());
    game = g;
    plugin = pl;
    end = false;
}
	synchronized public void run() {
    	try {
    	if(end)this.interrupt();
		game.setEnded(true);
        int start = game.getStartTime();
        this.wait(20*1000);
    	if(end)this.interrupt();
        while(HVD.players.size()<game.getMinPlayers()) {
            int i = game.getMinPlayers()-HVD.players.size();
            plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: Game requires " +ChatColor.GOLD+i+ChatColor.AQUA+" more players to begin!");
            this.wait(10*1000);
        	if(end)this.interrupt();
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: Game beginning in " +ChatColor.GOLD+start+ChatColor.AQUA+" seconds!");
     	start = start/2;
        this.wait(start*1000);
    	if(end)this.interrupt();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: Game beginning in " +ChatColor.GOLD+start+ChatColor.AQUA+" seconds!");
     	start = start/2;
     	this.wait(start*1000);
    	if(end)this.interrupt();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: Game beginning in " +ChatColor.GOLD+start+ChatColor.AQUA+" seconds!");
     	this.wait(start*1000);
    	if(end)this.interrupt();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.GOLD+"Game Start"+ChatColor.AQUA+"!");
        game.setHunter(game.chooseHunter());
        startGame();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.RED+game.getHunter()+ChatColor.AQUA+" is the Hunter! You have 10 seconds to run and hide before he is given his gear!");
        this.wait(10*1000);
    	if(end)this.interrupt();
        game.setEnded(false);
        equipHunter();
        equipDeer();
        startDrops();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.RED+game.getHunter()+ChatColor.AQUA+" is hunting!");
        //start
        
        int timer = game.getTimeLimit();
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain in this round!");
     	timer = timer/2;
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain!");
     	timer = timer/2;
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain!");
     	timer = timer/2;
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain!");
     	timer = timer/2;
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
     	this.wait(timer*1000/4);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
        	return;
        }
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: " +ChatColor.GOLD+timer+ChatColor.AQUA+" seconds remain!");
     	this.wait(timer*1000);
    	if(end)this.interrupt();
        if(game.isEnded()) {
        	if(HVD.players.contains(game.getHunter())) {
        		winGame();
        	}else {
        		loseGame();
        	}
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
		game.setEnded(true);
        stopDrops();
		game.setHunter("");
        for(Player p : plugin.getServer().getOnlinePlayers()) {
        	p.getInventory().clear();
        	ItemStack[] is = new ItemStack[4];
        	p.getInventory().setArmorContents(is);
            for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }
        	p.teleport(new Location(p.getWorld(), game.getLobyLocationX(), game.getLobyLocationY(), game.getLobyLocationZ()));
        	HVD.players = new ArrayList<String>();
        }
        HVD.startGame();
        this.interrupt();   
	}
	synchronized void startGame() {
        for(Player p : plugin.getServer().getOnlinePlayers()) {
        	if(HVD.players.contains(p.getName())) {
        		p.teleport(new Location(p.getWorld(), game.getGameLocationX(),game.getGameLocationY(),game.getGameLocationZ()));
        	}
        }
	}
	synchronized void loseGame() {
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.GOLD+"Game End"+ChatColor.AQUA+"!");
        plugin.getServer().broadcastMessage(ChatColor.AQUA+"[HVD]: "+ChatColor.GOLD+"Deer"+ChatColor.AQUA+" have won!!");
        endGame();
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
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, game.getTimeLimit()*20, 2, true));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, game.getTimeLimit()*20, 1, true));
		
	}
	synchronized void equipDeer() {
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			if(HVD.players.contains(p.getName())&&(!game.getHunter().equals(p.getName()))) {
				ItemStack i = new ItemStack(Material.LEATHER_HELMET);
				p.getInventory().setHelmet(i);
				i = new ItemStack(Material.LEATHER_CHESTPLATE);
				p.getInventory().setChestplate(i);
				i = new ItemStack(Material.LEATHER_LEGGINGS);
				p.getInventory().setLeggings(i);
				i = new ItemStack(Material.LEATHER_BOOTS);
				p.getInventory().setBoots(i);
				
				ArrayList<String> l = new ArrayList<String>();
				
				i = new ItemStack(Material.BLAZE_ROD);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Horn");
				l.add(ChatColor.AQUA + "Hits for 1 heart!");
				m.setLore(l);
				i.setItemMeta(m);
				p.getInventory().addItem(i);
				
				i = new ItemStack(Material.SUGAR);
				l = new ArrayList<String>();
				m = i.getItemMeta();
				m.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Sprint");
				l.add(ChatColor.AQUA + "Right click to sprint");
				l.add(ChatColor.AQUA +  "every 15 seconds");
				m.setLore(l);
				i.setItemMeta(m);
				p.getInventory().addItem(i);
				
				
			}
		}
	}
	synchronized void startDrops() {
		game.getThreadHandle().start();
	}
	synchronized void stopDrops() {
		game.getThreadHandle().go = false;
	}
	
}