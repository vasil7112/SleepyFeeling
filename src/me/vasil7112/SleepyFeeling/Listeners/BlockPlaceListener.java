package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static me.vasil7112.SleepyFeeling.SleepyFeeling.hasPermission;

public class BlockPlaceListener implements Listener{

	private SleepyFeeling plugin;
	
	public BlockPlaceListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e){
		Player player = e.getPlayer();
		if(!hasPermission(player,"SleepyFeeling.Bypass.BlockPlacing")){
			plugin.EARU.RemEnergy(player, plugin.getConfigString("Configuration.WaysToLoseEnergy.BlockPlacing.DecreaseAmount"));
		}
	}

}
