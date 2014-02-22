package me.vasil7112.SleepyFeeling.Listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.vasil7112.SleepyFeeling.SleepyFeeling;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerItemConsumeListener implements Listener{

	private SleepyFeeling plugin;
	public HashMap<Integer, Material> Imaterial = new HashMap<Integer, Material>();
	public HashMap<Integer, Integer> Iamount = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> Ihept = new HashMap<Integer, Integer>();
	public HashMap<Integer, String> IdisplayName = new HashMap<Integer, String>();
	public HashMap<Integer, String> IdisplayLore = new HashMap<Integer, String>();
	public PlayerItemConsumeListener(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent e){
		Player player = e.getPlayer();
		for(int i=0;i<Imaterial.size();i++){
			if(e.getItem().isSimilar(createItemStack(Imaterial.get(i), null, IdisplayName.get(i), IdisplayLore.get(i)))){
				plugin.HEPT.put(player, plugin.HEPT.get(player) + Ihept.get(i));
				plugin.PEU.addPotionEffectsToPlayer(player, true);
			}
			player.getInventory().addItem(createItemStack(Imaterial.get(i), null, IdisplayName.get(i), IdisplayLore.get(i)));
		}
	}
	
	public static ItemStack createItemStack(Material material, Integer amount, String displayName, String displayLore){
		ItemStack item;
		if(amount !=null){
			item = new ItemStack(material, amount);
		}else{
			item = new ItemStack(material, 1);
		}
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', displayLore));
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
}
