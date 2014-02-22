package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener{

	private SleepyFeeling plugin;
	
	public PlayerRespawnListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e){
		Player player = e.getPlayer();
		plugin.pConfig.getCustomConfig().set(player.getName()+".isDead", Boolean.valueOf(false));
		plugin.Energy.put(player, plugin.MaxEnergy);
		plugin.HEPT.put(player, 0);
		plugin.pConfig.saveCustomConfig();
	}
}
