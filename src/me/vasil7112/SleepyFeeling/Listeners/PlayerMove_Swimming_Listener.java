package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove_Swimming_Listener implements Listener{

	private SleepyFeeling plugin;
	public PlayerMove_Swimming_Listener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e){
		Player player = e.getPlayer();
		Location location = player.getLocation();
		Block block = location.getBlock();
		if(block.getRelative(0,-1,0).getType() == Material.WATER || block.getRelative(0,-1,0).getType() == Material.STATIONARY_WATER){
			if(!player.hasPermission("SleepyFeeling.Bypass.Swimming")){
				plugin.EARU.RemEnergy(player, plugin.cConfig.getCustomConfig().getString("Configuration.WaysToLoseEnergy.Swimming.DecreaseAmount"));
			}
		}
	}

}
