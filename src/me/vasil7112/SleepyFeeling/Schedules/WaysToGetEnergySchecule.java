package me.vasil7112.SleepyFeeling.Schedules;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by nikosgram13 on 3/3/14.
 */
public class WaysToGetEnergySchecule extends BukkitRunnable {

	private final SleepyFeeling sleepyFeeling;

	public WaysToGetEnergySchecule(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}

	@Override
	public void run() {
		for (Player playerName : sleepyFeeling.Sleeping) {
			sleepyFeeling.EARU.AddEnergy(playerName, sleepyFeeling.getConfigString("Configuration.WaysToGetEnergy.Sleeping.IncreaseAmountPerSecond"));
		}
	}

}
