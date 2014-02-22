package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener{

	private SleepyFeeling plugin;
	
	public PlayerQuitListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent e){
		final Player player = e.getPlayer();
		plugin.pConfig.getCustomConfig().set(player.getName()+".Energy", Double.valueOf(plugin.Energy.get(player)));
		plugin.pConfig.getCustomConfig().set(player.getName()+".HEPT", Integer.valueOf(plugin.HEPT.get(player)));
		PlayerSleepListener PSL = new PlayerSleepListener(plugin);
		PSL.exitBed(player);
		plugin.Players.remove(player.getName());
		plugin.Sleeping.remove(player);
		plugin.Energy.remove(player);
		plugin.HEPT.remove(player);
		plugin.pConfig.saveCustomConfig();
	}
}
