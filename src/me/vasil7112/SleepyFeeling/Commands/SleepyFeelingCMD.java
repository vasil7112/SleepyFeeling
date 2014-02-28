package me.vasil7112.SleepyFeeling.Commands;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import me.vasil7112.SleepyFeeling.Listeners.PlayerSleepListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class SleepyFeelingCMD implements CommandExecutor {
	private SleepyFeeling plugin;
	
	public SleepyFeelingCMD(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("SleepyFeeling")){
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("check")){
					if(sender.hasPermission("SleepyFeeling.check.self")){
						plugin.SEU.ShowEnergyToPlayer(Bukkit.getPlayerExact(sender.getName().toString()));
						return true;
					}else{
						sender.sendMessage(ChatColor.RED+"You don't have enough permissions to perform this command!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("wakeup")){
						PlayerSleepListener PIL = new PlayerSleepListener(plugin);
						PIL.exitBed(Bukkit.getPlayerExact(sender.getName()));
						return true;
				}else{
					sender.sendMessage(ChatColor.RED+"This command doesn't exist!");
					return true;
				}
			}else if(args.length == 2){
				if(args[0].equalsIgnoreCase("check")){
					if(sender.hasPermission("SleepyFeeling.check.other")){
						if(Bukkit.getPlayerExact(args[1]) !=null){
							plugin.SEU.ShowEnergyOfPlayer(Bukkit.getPlayerExact(sender.getName().toString()), Bukkit.getPlayerExact(args[1]));
							return true;
						}else{
							sender.sendMessage(ChatColor.RED+"The player you are looking for is not online!");
							return true;
						}
					}else{
						sender.sendMessage(ChatColor.RED+"You don't have enough permissions to perform this command!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("add")){
					if(sender.hasPermission("SleepyFeeling.addEnergy")){
						plugin.EARU.AddEnergy(Bukkit.getPlayerExact(sender.getName()), args[1]);
						sender.sendMessage("You've added "+args[1]+" Energy points to yourself");
						return true;
					}else{
						sender.sendMessage(ChatColor.RED+"You don't have enough permissions to perform this command!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("rem")){
					if(sender.hasPermission("SleepyFeeling.remEnergy")){
						plugin.EARU.RemEnergy(Bukkit.getPlayerExact(sender.getName()), args[1]);
						sender.sendMessage("You've removed "+args[1]+" Energy points from yourself");
						return true;
					}else{
						sender.sendMessage(ChatColor.RED+"You don't have enough permissions to perform this command!");
						return true;
					}
				}else{
					sender.sendMessage(ChatColor.RED+"This command doesn't exist!");
					return true;
				}
			}else if(args.length == 3){
				if(args[0].equalsIgnoreCase("add")){
					if(sender.hasPermission("SleepyFeeling.addEnergy")){
						if(Bukkit.getPlayerExact(args[1]) !=null){
							plugin.EARU.AddEnergy(Bukkit.getPlayerExact(args[1]), args[2]);
							sender.sendMessage("You've added "+args[2]+" Energy points to player: "+args[1]);
							return true;
						}else{
							sender.sendMessage(ChatColor.RED+"The player you are looking for is not online!");
							return true;
						}
					}else{
						sender.sendMessage(ChatColor.RED+"You don't have enough permissions to perform this command!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("rem")){
					if(sender.hasPermission("SleepyFeeling.remEnergy")){
						if(Bukkit.getPlayerExact(args[1]) !=null){
							plugin.EARU.RemEnergy(Bukkit.getPlayerExact(args[1]), args[2]);
							sender.sendMessage("You've removed "+args[2]+" Energy points to player: "+args[1]);
							return true;
						}else{
							sender.sendMessage(ChatColor.RED+"The player you are looking for is not online!");
							return true;
						}
					}else{
						sender.sendMessage(ChatColor.RED+"You don't have enough permissions to perform this command!");
						return true;
					}
				}else{
					sender.sendMessage(ChatColor.RED + "This command doesn't exist!");
					return true;
				}
				
			}else{
				sender.sendMessage(ChatColor.RED+"SleepyFeeling Commands: \n"+
								   ChatColor.RED+"/SleepyFeeling wakeup - Wakes you up if you are sleeping\n"+
								   ChatColor.RED+"/SleepyFeeling check - \n"+
								   ChatColor.RED+"/SleepyFeeling add (playername) <energy>- \n"+
								   ChatColor.RED+"/SleepyFeeling rem (playername) <energy> - \n"+
								   ChatColor.RED+"------\"()\" Optional - \"<>\" Required------");
				return true;
			}
		}
		return false;
	}
}
