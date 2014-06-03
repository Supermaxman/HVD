package me.supermaxman.hvd;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HVDHandleThread extends Thread {

private final HVDGame game;
private final HVD plugin;
public boolean go;

public HVDHandleThread(HVD pl, HVDGame g){
    setName("HVD-Thread-Handle-"+getId());
    game = g;
    plugin = pl;
    go = false;
}
	synchronized public void run() {
		go = true;
    	while (go) {
		 	try {
				this.wait(30000);
				if(!game.isEnded() && go) {
					dropApple();
				}
			} catch (InterruptedException e) {
				HVD.log.warning("[" + plugin.getName() + "] Game interupted by server.");
			}
    	}
    	this.interrupt();
	}
	
	@SuppressWarnings("deprecation")
	synchronized void dropApple() {
		ItemStack i = new ItemStack(Material.APPLE);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Apple");
		i.setItemMeta(m);
		Player p = plugin.getServer().getPlayer(game.getHunter());
		p.getWorld().dropItem(new Location(p.getWorld(), game.getAppleLocationX(), game.getAppleLocationY(), game.getAppleLocationZ()), i);
	}

	
}