package me.vasil7112.SleepyFeeling.Schedules;

import me.vasil7112.SleepyFeeling.Listeners.PlayerSleepListener;
import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by nikosgram13 on 3/4/14.
 */
public class BedExitScheclule extends BukkitRunnable {

	private final SleepyFeeling sleepyFeeling;
	private Player player;

	public BedExitScheclule(SleepyFeeling sleepyFeeling, Player player) {
		this.sleepyFeeling = sleepyFeeling;
		this.player = player;
	}

	@Override
	public void run() {
		for (String playerName : sleepyFeeling.Players) {
			if(sleepyFeeling.Sleeping.contains(player)){
				new PlayerSleepListener(sleepyFeeling).exitBed(player);
			}
		}
	}

}
