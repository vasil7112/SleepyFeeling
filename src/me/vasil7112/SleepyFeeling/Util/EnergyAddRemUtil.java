package me.vasil7112.SleepyFeeling.Util;

import org.bukkit.entity.Player;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

public class EnergyAddRemUtil {

	private SleepyFeeling plugin;
	
	public EnergyAddRemUtil(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	public void AddEnergy(Player player, String energy){
		if(plugin.Energy.get(player) + Float.valueOf(energy) > plugin.MaxEnergy){
			plugin.Energy.put(player, plugin.MaxEnergy);
		}else{
			plugin.Energy.put(player, plugin.Energy.get(player) + Float.valueOf(energy));
		}
	}
	
	public void RemEnergy(Player player, String energy){
		if(plugin.Energy.get(player) - Float.valueOf(energy) < Float.valueOf(0.0f)){
			plugin.Energy.put(player, Float.valueOf(0.0F));
		}else{
			plugin.Energy.put(player, plugin.Energy.get(player) - Float.valueOf(energy));
		}
	}
}
