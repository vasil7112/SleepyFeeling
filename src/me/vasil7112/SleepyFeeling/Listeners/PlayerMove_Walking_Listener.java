package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import static me.vasil7112.SleepyFeeling.SleepyFeeling.hasGod;
import static me.vasil7112.SleepyFeeling.SleepyFeeling.hasPermission;

public class PlayerMove_Walking_Listener implements Listener{

	private final SleepyFeeling sleepyFeeling;

	public PlayerMove_Walking_Listener(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e){
		Player player = e.getPlayer();
		if(!hasPermission(player, "SleepyFeeling.Bypass.Walking") && !hasGod(player)){
			sleepyFeeling.EARU.RemEnergy(player, sleepyFeeling.getConfigString("Configuration.WaysToLoseEnergy.Walking.DecreaseAmount"));
		}
	}

}
