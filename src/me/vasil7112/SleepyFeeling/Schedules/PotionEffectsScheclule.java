package me.vasil7112.SleepyFeeling.Schedules;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by nikosgram13 on 3/3/14.
 */
public class PotionEffectsScheclule extends BukkitRunnable {

	private final SleepyFeeling sleepyFeeling;

	public PotionEffectsScheclule(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}

	@Override
	public void run() {
		for (String playerName : sleepyFeeling.Players) {
			Player player = Bukkit.getPlayerExact(playerName);
			if (sleepyFeeling.HEPT.get(player) > 0) {
				sleepyFeeling.HEPT.put(player, sleepyFeeling.HEPT.get(player) - 1);
				sleepyFeeling.PEU.addPotionEffectsToPlayer(player, true);
			} else {
				sleepyFeeling.PEU.addPotionEffectsToPlayer(player, false);
			}
		}
	}

}
