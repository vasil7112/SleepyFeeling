package me.vasil7112.SleepyFeeling.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import me.vasil7112.SleepyFeeling.SleepyFeeling;
import me.vasil7112.SleepyFeeling.Listeners.PlayerItemConsumeListener;

public class PotionCraftingUtil {
private SleepyFeeling plugin;
	
	public PotionCraftingUtil(SleepyFeeling plugin){
		this.plugin = plugin;
	}
	
	private void addCraftingRecipe(Material material, Integer amount, String displayName, String displayLore, String recipe) { 
		if(recipe.equalsIgnoreCase("NONE")){
			return;
		}
		String[] split = recipe.split(",");
        ItemStack i = new ItemStack(material, 1);
        ItemMeta meta = i.getItemMeta();
        
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', displayLore));
		meta.setLore(lore);
        i.setItemMeta(meta);
        ShapedRecipe g = new ShapedRecipe(i);
        g.shape("ABC", "DEF", "GHI");
        g.setIngredient('A', Material.getMaterial(split[0]));
        g.setIngredient('B', Material.getMaterial(split[1]));
        g.setIngredient('C', Material.getMaterial(split[2]));
        g.setIngredient('D', Material.getMaterial(split[3]));
        g.setIngredient('E', Material.getMaterial(split[4]));
        g.setIngredient('F', Material.getMaterial(split[5]));
        g.setIngredient('G', Material.getMaterial(split[6]));
        g.setIngredient('H', Material.getMaterial(split[7]));
        g.setIngredient('I', Material.getMaterial(split[8]));
        Bukkit.addRecipe(g);
    }
	
	public void addCraftingRecipes(){
		PlayerItemConsumeListener PICL = new PlayerItemConsumeListener(plugin);
		for(int i=0;i< this.plugin.cConfig.getCustomConfig().getConfigurationSection("Configuration.EnergyDrinksAndFood").getKeys(false).size();i++){
			addCraftingRecipe(Material.getMaterial(plugin.cConfig.getCustomConfig().getString("Configuration.EnergyDrinksAndFood."+i+".Material")), plugin.cConfig.getCustomConfig().getInt("Configuration.EnergyDrinksAndFood."+i+".Amount"), plugin.cConfig.getCustomConfig().getString("Configuration.EnergyDrinksAndFood."+i+".DisplayName"), plugin.cConfig.getCustomConfig().getString("Configuration.EnergyDrinksAndFood."+i+".Lore"), plugin.cConfig.getCustomConfig().getString("Configuration.EnergyDrinksAndFood."+i+".Recipe"));
			PICL.Imaterial.put(i, Material.getMaterial(plugin.cConfig.getCustomConfig().getString("Configuration.EnergyDrinksAndFood."+i+".Material")));
			PICL.Iamount.put(i, this.plugin.cConfig.getCustomConfig().getInt("Configuration.EnergyDrinksAndFood."+i+".Amount"));
			PICL.Ihept.put(i, this.plugin.cConfig.getCustomConfig().getInt("Configuration.EnergyDrinksAndFood."+i+".HEPT"));
			PICL.IdisplayName.put(i, this.plugin.cConfig.getCustomConfig().getString("Configuration.EnergyDrinksAndFood."+i+".DisplayName"));
			PICL.IdisplayLore.put(i, this.plugin.cConfig.getCustomConfig().getString("Configuration.EnergyDrinksAndFood."+i+".Lore"));
		}
	}
}
