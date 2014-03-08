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
	
	public int getEnergyInt(Player player){
		Float energy = plugin.Energy.get(player);
		if((energy >= Float.valueOf("0.9") * plugin.MaxEnergy) && (energy <= Float.valueOf("1.0") * plugin.MaxEnergy)){
			return 10;
		}else if((energy >= Float.valueOf("0.8") * plugin.MaxEnergy) && (energy < Float.valueOf("0.9") * plugin.MaxEnergy)){
			return 9;
		}else if((energy >= Float.valueOf("0.7") * plugin.MaxEnergy) && (energy < Float.valueOf("0.8") * plugin.MaxEnergy)){
			return 8;
		}else if((energy >= Float.valueOf("0.6") * plugin.MaxEnergy) && (energy < Float.valueOf("0.7") * plugin.MaxEnergy)){
			return 7;
		}else if((energy >= Float.valueOf("0.5") * plugin.MaxEnergy) && (energy < Float.valueOf("0.6") * plugin.MaxEnergy)){
			return 6;
		}else if((energy >= Float.valueOf("0.4") * plugin.MaxEnergy) && (energy < Float.valueOf("0.5") * plugin.MaxEnergy)){
			return 5;
		}else if((energy >= Float.valueOf("0.3") * plugin.MaxEnergy) && (energy < Float.valueOf("0.4") * plugin.MaxEnergy)){
			return 4;
		}else if((energy >= Float.valueOf("0.2") * plugin.MaxEnergy) && (energy < Float.valueOf("0.3") * plugin.MaxEnergy)){
			return 3;
		}else if((energy >= Float.valueOf("0.1") * plugin.MaxEnergy) && (energy < Float.valueOf("0.2") * plugin.MaxEnergy)){
			return 2;
		}else if((energy >= Float.valueOf("0.0") * plugin.MaxEnergy) && (energy < Float.valueOf("0.1") * plugin.MaxEnergy)){
			return 1;
		}
		return 0;
	}
	/**
	public void showEnergyTop(final Player player, Integer i){
		Float energy = plugin.Energy.get(player);
		if(i == 10){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 100);
		}else if(i == 9){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 90);
		}else if(i == 8){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 80);
		}else if(i == 7){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 70);
		}else if(i == 6){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 60);
		}else if(i == 5){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 50);
		}else if(i == 4){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 40);
		}else if(i == 3){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 30);
		}else if(i == 2){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 20);
		}else if(i == 1){
			BarAPI.setMessage(player, "Your Energy:" + energy);
			BarAPI.setHealth(player, 10);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				if(BarAPI.hasBar(player)){
					BarAPI.removeBar(player);
				}
			}
        }, 20 * 3L);
	}**/
}
