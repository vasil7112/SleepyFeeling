package me.vasil7112.SleepyFeeling.Schedules;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by nikosgram13 on 3/3/14.
 */
public class EnergyDecreaseScheclule extends BukkitRunnable {

	private final SleepyFeeling sleepyFeeling;

	public EnergyDecreaseScheclule(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}

	@Override
	public void run() {
		for (String playerName : sleepyFeeling.Players) {
			Player player = Bukkit.getPlayerExact(playerName);
			sleepyFeeling.EARU.RemEnergy(player, String.valueOf(sleepyFeeling.EnergyDecreaseAmount));
			sleepyFeeling.SEU.ShowEnergyToPlayer(player);
		}
	}

}
