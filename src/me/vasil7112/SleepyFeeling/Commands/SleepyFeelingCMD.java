package me.vasil7112.SleepyFeeling.Commands;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import me.vasil7112.SleepyFeeling.Listeners.PlayerSleepListener;

import static me.vasil7112.SleepyFeeling.Util.ChatFormat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.vasil7112.SleepyFeeling.SleepyFeeling.hasPermission;


public class SleepyFeelingCMD implements CommandExecutor {

	private final SleepyFeeling sleepyFeeling;

	public SleepyFeelingCMD(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = ((Player) sender).getPlayer();
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("check")) {
					if(hasPermission(player, "SleepyFeeling.check.self")){
						sleepyFeeling.SEU.ShowEnergyToPlayer(Bukkit.getPlayerExact(player.getName()));
						return true;
					} else {
						player.sendMessage(withColor("&4You don't have enough permissions to perform this command!"));
						return true;
					}
				} else if(args[0].equalsIgnoreCase("wakeup")) {
						PlayerSleepListener PIL = new PlayerSleepListener(sleepyFeeling);
						PIL.exitBed(Bukkit.getPlayerExact(player.getName()));
						return true;
				} else {
					player.sendMessage(withColor("&4This command doesn't exist!"));
					return true;
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("check")) {
					if(hasPermission(player, "SleepyFeeling.check.other")) {
						if(Bukkit.getPlayerExact(args[1]) !=null) {
							sleepyFeeling.SEU.ShowEnergyOfPlayer(Bukkit.getPlayerExact(player.getName()), Bukkit.getPlayerExact(args[1]));
							return true;
						} else {
							player.sendMessage(withColor("&4The player you are looking for is not online!"));
							return true;
						}
					} else {
						player.sendMessage(withColor("&4You don't have enough permissions to perform this command!"));
						return true;
					}
				} else if(args[0].equalsIgnoreCase("add")) {
					if(hasPermission(player, "SleepyFeeling.addEnergy")) {
						sleepyFeeling.EARU.AddEnergy(Bukkit.getPlayerExact(player.getName()), args[1]);
						player.sendMessage(withColor("&2You've added &6{energypoint} &2energy points to yourself"
								.replace("{energypoint}",args[1])));
						return true;
					} else {
						player.sendMessage(withColor("&4You don't have enough permissions to perform this command!"));
						return true;
					}
				} else if(args[0].equalsIgnoreCase("rem")) {
					if(hasPermission(player, "SleepyFeeling.remEnergy")) {
						sleepyFeeling.EARU.RemEnergy(Bukkit.getPlayerExact(player.getName()), args[1]);
						player.sendMessage(withColor("&2You've removed &6{energypoint} &2energy points from yourself"
								.replace("{energypoint}", args[1])));
						return true;
					} else {
						player.sendMessage(withColor("&4You don't have enough permissions to perform this command!"));
						return true;
					}
				} else {
					player.sendMessage(withColor("&4This command doesn't exist!"));
					return true;
				}
			} else if(args.length == 3){
				if(args[0].equalsIgnoreCase("add")) {
					if(hasPermission(player,"SleepyFeeling.addEnergy")) {
						if(Bukkit.getPlayerExact(args[1]) !=null) {
							sleepyFeeling.EARU.AddEnergy(Bukkit.getPlayerExact(args[1]), args[2]);
							player.sendMessage(withColor("&2You've added &6{energypoint} &2energy points to &6{player}"
									.replace("{energypoint}",args[2])
									.replace("{player}",args[1])));
							return true;
						} else {
							player.sendMessage(withColor("&4The player you are looking for is not online!"));
							return true;
						}
					}else{
						player.sendMessage(withColor("&4You don't have enough permissions to perform this command!"));
						return true;
					}
				} else if(args[0].equalsIgnoreCase("rem")) {
					if(hasPermission(player, "SleepyFeeling.remEnergy")) {
						if(Bukkit.getPlayerExact(args[1]) !=null){
							sleepyFeeling.EARU.RemEnergy(Bukkit.getPlayerExact(args[1]), args[2]);
							player.sendMessage(withColor("&2You've removed &6{energypoint} &2energy points from &6{player}"
									.replace("{energypoint}",args[2])
									.replace("{player}",args[1])));
							return true;
						} else {
							player.sendMessage(withColor("&4The player you are looking for is not online!"));
							return true;
						}
					} else {
						player.sendMessage(withColor("&4You don't have enough permissions to perform this command!"));
						return true;
					}
				} else {
					player.sendMessage(withColor("&4This command doesn't exist!"));
					return true;
				}
				
			} else {
				player.sendMessage(sleepyFeelingMessage(player));
				return true;
			}
		} else {
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("check")){
					if(Bukkit.getPlayerExact(args[1]) !=null) {
						sleepyFeeling.SEU.ShowEnergyOfPlayer(Bukkit.getPlayerExact(sender.getName()), Bukkit.getPlayerExact(args[1]));
						return true;
					} else {
						sender.sendMessage(withColor("&4The player you are looking for is not online!"));
						return true;
					}
				} else {
					sender.sendMessage(withColor("&4This command doesn't exist!"));
					return true;
				}
			} else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("add")){
					if(Bukkit.getPlayerExact(args[1]) !=null) {
						sleepyFeeling.EARU.AddEnergy(Bukkit.getPlayerExact(args[1]), args[2]);
						sender.sendMessage(withColor("&2You've added &6{energypoint} &2energy points to &6{player}"
								.replace("{energypoint}",args[2])
								.replace("{player}",args[1])));
						return true;
					} else {
						sender.sendMessage(withColor("&4The player you are looking for is not online!"));
						return true;
					}
				} else if(args[0].equalsIgnoreCase("rem")) {
					if(Bukkit.getPlayerExact(args[1]) !=null){
						sleepyFeeling.EARU.RemEnergy(Bukkit.getPlayerExact(args[1]), args[2]);
						sender.sendMessage(withColor("&2You've removed &6{energypoint} &2energy points from &6{player}"
								.replace("{energypoint}",args[2])
								.replace("{player}",args[1])));
						return true;
					} else {
						sender.sendMessage(withColor("&4The player you are looking for is not online!"));
						return true;
					}
				} else {
					sender.sendMessage(withColor("&4This command doesn't exist!"));
					return true;
				}

			}
		}
		return false;
	}

	private String sleepyFeelingMessage(Player player){
		String message = "";
		message = message+"&4SleepyFeeling Commands: \n";
		message = message+"&4/SleepyFeeling wakeup - Wakes you up if you are sleeping\n";
		if(hasPermission(player,"SleepyFeeling.check.self")){ message = message+"&4/SleepyFeeling check - \n"; }
		if(hasPermission(player,"SleepyFeeling.addEnergy")){ message = message+"&4/SleepyFeeling add (playername) <energy>- \n"; }
		if(hasPermission(player,"SleepyFeeling.remEnergy")){ message = message+"&4/SleepyFeeling rem (playername) <energy> - \n"; }
		message = message+"&4------\"()\" Optional - \"<>\" Required------";
		return withColor(message);
	}

}
