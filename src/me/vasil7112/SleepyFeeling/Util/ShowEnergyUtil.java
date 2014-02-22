package me.vasil7112.SleepyFeeling.Util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

public class ShowEnergyUtil {

	private SleepyFeeling plugin;
	
	public ShowEnergyUtil(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	public void ShowEnergyToPlayer(Player player){
		Float energy = plugin.Energy.get(player);
		if((energy >= Float.valueOf("0.9") * plugin.MaxEnergy) && (energy <= Float.valueOf("1.0") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIIIIIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.8") * plugin.MaxEnergy) && (energy < Float.valueOf("0.9") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIIIIII"+ChatColor.RED+"I"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.7") * plugin.MaxEnergy) && (energy < Float.valueOf("0.8") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIIIII"+ChatColor.RED+"II"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.6") * plugin.MaxEnergy) && (energy < Float.valueOf("0.7") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIIII"+ChatColor.RED+"III"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.5") * plugin.MaxEnergy) && (energy < Float.valueOf("0.6") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIII"+ChatColor.RED+"IIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.4") * plugin.MaxEnergy) && (energy < Float.valueOf("0.5") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIII"+ChatColor.RED+"IIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.3") * plugin.MaxEnergy) && (energy < Float.valueOf("0.4") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"III"+ChatColor.RED+"IIIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.2") * plugin.MaxEnergy) && (energy < Float.valueOf("0.3") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"II"+ChatColor.RED+"IIIIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.1") * plugin.MaxEnergy) && (energy < Float.valueOf("0.2") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"I"+ChatColor.RED+"IIIIIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.0") * plugin.MaxEnergy) && (energy < Float.valueOf("0.1") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+"Your Energy: "+ChatColor.GOLD+"["+ChatColor.RED+"IIIIIIIII"+ChatColor.GOLD+"]");
		}
	}
	
	public void ShowEnergyOfPlayer(Player player, Player target){
		Float energy = plugin.Energy.get(target);
		if((energy >= Float.valueOf("0.9") * plugin.MaxEnergy) && (energy <= Float.valueOf("1.0") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIIIIIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.8") * plugin.MaxEnergy) && (energy < Float.valueOf("0.9") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIIIIII"+ChatColor.RED+"I"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.7") * plugin.MaxEnergy) && (energy < Float.valueOf("0.8") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIIIII"+ChatColor.RED+"II"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.6") * plugin.MaxEnergy) && (energy < Float.valueOf("0.7") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIIII"+ChatColor.RED+"III"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.5") * plugin.MaxEnergy) && (energy < Float.valueOf("0.6") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIIII"+ChatColor.RED+"IIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.4") * plugin.MaxEnergy) && (energy < Float.valueOf("0.5") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"IIII"+ChatColor.RED+"IIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.3") * plugin.MaxEnergy) && (energy < Float.valueOf("0.4") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"III"+ChatColor.RED+"IIIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.2") * plugin.MaxEnergy) && (energy < Float.valueOf("0.3") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"II"+ChatColor.RED+"IIIIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.1") * plugin.MaxEnergy) && (energy < Float.valueOf("0.2") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.GREEN+"I"+ChatColor.RED+"IIIIIIII"+ChatColor.GOLD+"]");
		}else if((energy >= Float.valueOf("0.0") * plugin.MaxEnergy) && (energy < Float.valueOf("0.1") * plugin.MaxEnergy)){
			player.sendMessage(ChatColor.GREEN+target.getName()+" Energy: "+ChatColor.GOLD+"["+ChatColor.RED+"IIIIIIIII"+ChatColor.GOLD+"]");
		}
	}
}
