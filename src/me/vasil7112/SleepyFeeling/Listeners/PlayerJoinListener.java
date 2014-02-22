package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener{

	private SleepyFeeling plugin;
	
	public PlayerJoinListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e){
		final Player player = e.getPlayer();
		if(plugin.pConfig.getCustomConfig().get(player.getName()) == null){
			plugin.pConfig.getCustomConfig().set(player.getName()+".Energy", Float.valueOf(plugin.MaxEnergy));
			plugin.pConfig.getCustomConfig().set(player.getName()+".isDead", Boolean.valueOf(false));
			plugin.pConfig.getCustomConfig().set(player.getName()+".HEPT", Integer.valueOf(0));
		}	
		plugin.pConfig.saveCustomConfig();
		plugin.Players.add(player.getName());
		plugin.Energy.put(player, Float.valueOf(plugin.pConfig.getCustomConfig().getString(player.getName()+".Energy")));
		plugin.HEPT.put(player, plugin.pConfig.getCustomConfig().getInt(player.getName()+".HEPT", Integer.valueOf(0)));
		if(plugin.HEPT.get(player) > 0){
			plugin.HEPT.put(player, plugin.HEPT.get(player) - 1);
			plugin.PEU.addPotionEffectsToPlayer(player, true);
		}else{
			plugin.PEU.addPotionEffectsToPlayer(player, false);
		}
	}
}
