package me.vasil7112.SleepyFeeling.Listeners;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener{

	private SleepyFeeling plugin;
	private String lm1;
	private String lm2;
	private String lm3;
	private String lm4;
	private String lm5;
	private String lm6;
	private String lm7;
	public PlayerChatListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent e){
		lm7=lm6;
		lm6=lm5;
		lm5=lm4;
		lm4=lm3;
		lm3=lm2;
		lm2=lm1;
		lm1=e.getMessage().toString();
		e.setCancelled(true);
		for(Player player : Bukkit.getOnlinePlayers()){
			sendEnergyMessage(player);
		}
	}
	
	public void sendEnergyMessage(Player player){
		player.sendMessage(" \n"+" \n"+" \n"+" \n"+" \n"+" \n"+" \n"+" \n"+" \n"+" \n"+ChatColor.BLUE+"########################\n"+
						   ChatColor.BLUE+"#Energy: "+plugin.Energy.get(player)+" / "+plugin.MaxEnergy+"\n"+
						   ChatColor.BLUE+"########################\n"+
						   ChatColor.BLUE+(lm7 !=null ? lm7 : " ")+"\n"+
						   ChatColor.BLUE+(lm6 !=null ? lm6 : " ")+"\n"+
						   ChatColor.BLUE+(lm5 !=null ? lm5 : " ")+"\n"+
						   ChatColor.BLUE+(lm4 !=null ? lm4 : " ")+"\n"+
						   ChatColor.BLUE+(lm3 !=null ? lm3 : " ")+"\n"+
						   ChatColor.BLUE+(lm2 !=null ? lm2 : " ")+"\n"+
						   ChatColor.BLUE+lm1+"\n");
	}
}
