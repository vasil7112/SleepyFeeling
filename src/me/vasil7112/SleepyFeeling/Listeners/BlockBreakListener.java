package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener{

	private SleepyFeeling plugin;
	
	public BlockBreakListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e){
		Player player = e.getPlayer();
		if(!player.hasPermission("SleepyFeeling.Bypass.BlockBreaking")){
			plugin.EARU.RemEnergy(player, plugin.cConfig.getCustomConfig().getString("Configuration.WaysToLoseEnergy.BlockBreaking.DecreaseAmount"));
		}
	}
}
