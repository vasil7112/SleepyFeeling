package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener{

	private SleepyFeeling plugin;
	
	public PlayerDeathListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		Player player = e.getEntity().getPlayer();
		plugin.pConfig.getCustomConfig().set(player.getName()+".isDead", Boolean.valueOf(true));
		plugin.pConfig.saveCustomConfig();
	}
}
