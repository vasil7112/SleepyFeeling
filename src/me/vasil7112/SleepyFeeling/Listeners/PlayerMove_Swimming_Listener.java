package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.vasil7112.SleepyFeeling.SleepyFeeling.hasGod;
import static me.vasil7112.SleepyFeeling.SleepyFeeling.hasPermission;

public class PlayerMove_Swimming_Listener implements Listener{

	private final SleepyFeeling sleepyFeeling;

	public PlayerMove_Swimming_Listener(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e){
		Player player = e.getPlayer();
		Location location = player.getLocation();
		Block block = location.getBlock();
		if(block.getRelative(0,-1,0).getType() == Material.WATER || block.getRelative(0,-1,0).getType() == Material.STATIONARY_WATER){
			if(!hasPermission(player,"SleepyFeeling.Bypass.Swimming") && !hasGod(player)){
				sleepyFeeling.EARU.RemEnergy(player, sleepyFeeling.cConfig.getCustomConfig().getString("Configuration.WaysToLoseEnergy.Swimming.DecreaseAmount"));
			}
		}
	}

}
