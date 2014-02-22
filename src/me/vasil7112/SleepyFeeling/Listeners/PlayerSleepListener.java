package me.vasil7112.SleepyFeeling.Listeners;

import java.lang.reflect.InvocationTargetException;
import me.vasil7112.SleepyFeeling.SleepyFeeling;
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

public class PlayerSleepListener implements Listener{

	private SleepyFeeling plugin;
	
	public PlayerSleepListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	

	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent e){
		e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) throws InvocationTargetException {
    	final Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
        	if(e.getClickedBlock().getType() == Material.BED_BLOCK){
        		setSleeping(e.getPlayer(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ());
            	player.setSleepingIgnored(true);
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    				@Override
    				public void run() {
    					if(plugin.Sleeping.contains(player)){
    						exitBed(player);
    					}
    				}
    	        }, 20L * plugin.cConfig.getCustomConfig().getLong("Configuration.WaysToGetEnergy.Sleeping.AutoWakeUp"));
        	}
        }
	}
	
	 public void setSleeping(Player c, int x, int y, int z) {
	        PacketPlayOutBed bedpacket = new PacketPlayOutBed(((CraftPlayer) c).getHandle(), x, y, z);
	        c.sendMessage(ChatColor.GREEN+"============["+ChatColor.GOLD+"SleepyFeeling"+ChatColor.GREEN+"]=============\n"+
	        			  ChatColor.GREEN+"You are currently sleeping and fueling up with energy.\n" +
	        			  ChatColor.GREEN+"You will automaticly leave the bed in " + " seconds.\n" + 
	        			  ChatColor.GREEN+"If you want to leave the bed earlier, just use the following command: \n"+
	        			  ChatColor.GREEN+"/SleepyFeeling wakeup");
	        for (Player p : Bukkit.getOnlinePlayers()) {
	            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bedpacket);
	        }
	        plugin.Sleeping.add(c);
	    }
	 
	    public void exitBed(Player p) {
	        PacketPlayOutAnimation aniPacket = new PacketPlayOutAnimation(((CraftPlayer) p).getHandle(), 2);
	        for (Player o : Bukkit.getOnlinePlayers()) {
	            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(aniPacket);
	        }
	        plugin.Sleeping.remove(p);
	        plugin.SEU.ShowEnergyToPlayer(p);
	    }
}
