package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove_Walking_Listener implements Listener{

	private SleepyFeeling plugin;
	public PlayerMove_Walking_Listener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e){
		Player player = e.getPlayer();
		if(!player.hasPermission("SleepyFeeling.Bypass.Walking")){
			plugin.EARU.RemEnergy(player, plugin.cConfig.getCustomConfig().getString("Configuration.WaysToLoseEnergy.Walking.DecreaseAmount"));
		}
	}
}
