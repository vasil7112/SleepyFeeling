package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.vasil7112.SleepyFeeling.SleepyFeeling.hasGod;
import static me.vasil7112.SleepyFeeling.SleepyFeeling.hasPermission;

public class PlayerMove_Sprinting_Listener implements Listener{

	private SleepyFeeling plugin;
	public PlayerMove_Sprinting_Listener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e){
		Player player = e.getPlayer();
		if(player.isSprinting()){
			if(!hasPermission(player,"SleepyFeeling.Bypass.Sprinting") && !hasGod(player)){
				plugin.EARU.RemEnergy(player, plugin.getConfigString("Configuration.WaysToLoseEnergy.Sprinting.DecreaseAmount"));
			}
		}
	}

}
