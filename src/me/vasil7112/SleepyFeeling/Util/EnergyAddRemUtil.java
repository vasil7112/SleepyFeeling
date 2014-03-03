package me.vasil7112.SleepyFeeling.Util;

import org.bukkit.entity.Player;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

public class EnergyAddRemUtil {

	private final SleepyFeeling sleepyFeeling;

	public EnergyAddRemUtil(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}
	
	public void AddEnergy(Player player, String energy){
		if(sleepyFeeling.Energy.get(player) + Float.valueOf(energy) > sleepyFeeling.MaxEnergy){
			sleepyFeeling.Energy.put(player, sleepyFeeling.MaxEnergy);
		}else{
			sleepyFeeling.Energy.put(player, sleepyFeeling.Energy.get(player) + Float.valueOf(energy));
		}
	}
	
	public void RemEnergy(Player player, String energy){
		if(sleepyFeeling.Energy.get(player) - Float.valueOf(energy) < Float.valueOf(0.0f)){
			sleepyFeeling.Energy.put(player, Float.valueOf(0.0F));
		}else{
			sleepyFeeling.Energy.put(player, sleepyFeeling.Energy.get(player) - Float.valueOf(energy));
		}
	}

}
