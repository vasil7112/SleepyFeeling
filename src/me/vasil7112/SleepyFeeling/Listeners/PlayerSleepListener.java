package me.vasil7112.SleepyFeeling.Listeners;

import java.lang.reflect.InvocationTargetException;

import me.vasil7112.SleepyFeeling.Schedules.BedExitScheclule;
import me.vasil7112.SleepyFeeling.SleepyFeeling;
import static me.vasil7112.SleepyFeeling.Util.ChatFormat.*;
import net.minecraft.server.v1_7_R1.PacketPlayOutAnimation;
import net.minecraft.server.v1_7_R1.PacketPlayOutBed;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class PlayerSleepListener implements Listener{

	private final SleepyFeeling sleepyFeeling;

	public PlayerSleepListener(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}

	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent e){
		e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) throws InvocationTargetException {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    	Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
        	if(e.getClickedBlock().getType() == Material.BED_BLOCK){
        		setSleeping(e.getPlayer(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ());
            	player.setSleepingIgnored(true);
				scheduler.scheduleSyncDelayedTask(sleepyFeeling, new BedExitScheclule(sleepyFeeling,player),
						20L * sleepyFeeling.getConfigLong("Configuration.WaysToGetEnergy.Sleeping.AutoWakeUp"));
        	}
        }
	}
	
	 public void setSleeping(Player c, int x, int y, int z) {
	        PacketPlayOutBed bedpacket = new PacketPlayOutBed(((CraftPlayer) c).getHandle(), x, y, z);
	        c.sendMessage(withColor("&2============[&6SleepyFeeling&2]=============\n"+
					"&2You are currently sleeping and fueling up with energy.\n" +
					"&2You will automaticly leave the bed in " + " seconds.\n" +
					"&2If you want to leave the bed earlier, just use the following command: \n"+
					"&2/SleepyFeeling wakeup"));
	        for (Player player : Bukkit.getOnlinePlayers()) {
	            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bedpacket);
	        }
		 sleepyFeeling.Sleeping.add(c);
	    }

	public void exitBed(Player player) {
		PacketPlayOutAnimation aniPacket = new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), 2);
		for (Player o : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) o).getHandle().playerConnection.sendPacket(aniPacket);
		}
		sleepyFeeling.Sleeping.remove(player);
		sleepyFeeling.SEU.ShowEnergyToPlayer(player);
	}
}
