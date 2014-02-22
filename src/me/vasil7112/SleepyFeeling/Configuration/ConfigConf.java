package me.vasil7112.SleepyFeeling.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigConf {
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	private SleepyFeeling plugin;
	
	public ConfigConf(SleepyFeeling plugin){
	  this.plugin = plugin;
	}

	public FileConfiguration getCustomConfig() {
	    if (customConfig == null) {
	        reloadCustomConfig();
	    }
	    return customConfig;
	}
	
	public void reloadCustomConfig() {
	    if (customConfigFile == null) {
	    	customConfigFile = new File(plugin.getDataFolder(), "Config.yml");
	    }
	    customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	 
	    InputStream defConfigStream = plugin.getResource("Config.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        customConfig.setDefaults(defConfig);
	    }
	    setupConfig();
	    saveCustomConfig();
	}
	    
    public void saveCustomConfig() {
	    if (customConfig == null || customConfigFile == null) {
	    	return;
	    }
	    try {
	        getCustomConfig().save(customConfigFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
    }
    
    public void saveDefaultConfig() {
        if (customConfigFile == null) {
            customConfigFile = new File(plugin.getDataFolder(), "Config.yml");
        }
        if (!customConfigFile.exists()) {            
        	plugin.saveResource("Config.yml", false);
        }
    }
    
    private void setupConfig(){
    	if(getCustomConfig().get("AutoUpdate")==null){
    		getCustomConfig().set("AutoUpdate", Boolean.valueOf(true));	
    	}
    	if(getCustomConfig().get("Premium-User")==null){
    		getCustomConfig().set("Premium-User.Username", String.valueOf(""));	
    		getCustomConfig().set("Premium-User.Password", String.valueOf(""));
    		getCustomConfig().set("Premium-User.Server-IP", String.valueOf(""));
    		getCustomConfig().set("Premium-User.Server-IMG-468x60", String.valueOf(""));
    	}
    	if(getCustomConfig().get("Configuration.Energy.MaxEnergy")==null){
    		getCustomConfig().set("Configuration.Energy.MaxEnergy", Float.valueOf("10000.0"));	
    	}
    	if(getCustomConfig().get("Configuration.Energy.EnergyDecreaseTick")==null){
    		getCustomConfig().set("Configuration.Energy.EnergyDecreaseTick", Integer.valueOf("1"));	
    	}
    	if(getCustomConfig().get("Configuration.Energy.EnergyDecreaseAmount")==null){
    		getCustomConfig().set("Configuration.Energy.EnergyDecreaseAmount", Float.valueOf("300"));	
    	}
    	if(getCustomConfig().get("Configuration.Energy.ShowEnergyLevel")==null){
    		getCustomConfig().set("Configuration.Energy.ShowEnergyLevel", Boolean.valueOf(true));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.Walking.Enabled")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.Walking.Enabled", Boolean.valueOf(true));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.Walking.DecreaseAmount")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.Walking.DecreaseAmount", Float.valueOf("0.1"));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.Sprinting.Enabled")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.Sprinting.Enabled", Boolean.valueOf(true));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.Sprinting.DecreaseAmount")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.Sprinting.DecreaseAmount", Float.valueOf("0.1"));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.Swimming.Enabled")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.Swimming.Enabled", Boolean.valueOf(true));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.Swimming.DecreaseAmount")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.Swimming.DecreaseAmount", Float.valueOf("0.2"));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.BlockBreaking.Enabled")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.BlockBreaking.Enabled", Boolean.valueOf(true));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.BlockBreaking.DecreaseAmount")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.BlockBreaking.DecreaseAmount", Float.valueOf("1.5"));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.BlockPlacing.Enabled")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.BlockPlacing.Enabled", Boolean.valueOf(true));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToLoseEnergy.BlockPlacing.DecreaseAmount")==null){
    		getCustomConfig().set("Configuration.WaysToLoseEnergy.BlockPlacing.DecreaseAmount", Float.valueOf("0.7"));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToGetEnergy.Sleeping.Enabled")==null){
    		getCustomConfig().set("Configuration.WaysToGetEnergy.Sleeping.Enabled", Boolean.valueOf(true));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToGetEnergy.Sleeping.IncreaseAmountPerSecond")==null){
    		getCustomConfig().set("Configuration.WaysToGetEnergy.Sleeping.IncreaseAmountPerSecond", Float.valueOf("80.5"));	
    	}
    	if(getCustomConfig().get("Configuration.WaysToGetEnergy.Sleeping.AutoWakeUp")==null){
    		getCustomConfig().set("Configuration.WaysToGetEnergy.Sleeping.AutoWakeUp", Long.valueOf("60"));	
    	}
    	if(getCustomConfig().get("Configuration.PotionEffects.0")==null){
    		getCustomConfig().set("Configuration.PotionEffects.0.Effect", String.valueOf("Regeneration"));	
    		getCustomConfig().set("Configuration.PotionEffects.0.EffectLevel", Integer.valueOf(1));
    		getCustomConfig().set("Configuration.PotionEffects.0.From", String.valueOf("10000"));	
    		getCustomConfig().set("Configuration.PotionEffects.0.To", String.valueOf("9500"));	
    		if(getCustomConfig().get("Configuration.PotionEffects.1")==null){
    			getCustomConfig().set("Configuration.PotionEffects.1.Effect", String.valueOf("FastDigging"));	
        		getCustomConfig().set("Configuration.PotionEffects.1.EffectLevel", Integer.valueOf(1));
        		getCustomConfig().set("Configuration.PotionEffects.1.From", String.valueOf("10000"));	
        		getCustomConfig().set("Configuration.PotionEffects.1.To", String.valueOf("9000"));	
    		}
    		if(getCustomConfig().get("Configuration.PotionEffects.2")==null){
    			getCustomConfig().set("Configuration.PotionEffects.2.Effect", String.valueOf("Speed"));	
        		getCustomConfig().set("Configuration.PotionEffects.2.EffectLevel", Integer.valueOf(2));
        		getCustomConfig().set("Configuration.PotionEffects.2.From", String.valueOf("10000"));	
        		getCustomConfig().set("Configuration.PotionEffects.2.To", String.valueOf("8500"));	
    		}
    		if(getCustomConfig().get("Configuration.PotionEffects.3")==null){
    			getCustomConfig().set("Configuration.PotionEffects.3.Effect", String.valueOf("Confusion"));	
        		getCustomConfig().set("Configuration.PotionEffects.3.EffectLevel", Integer.valueOf(1));
        		getCustomConfig().set("Configuration.PotionEffects.3.From", String.valueOf("1500"));	
        		getCustomConfig().set("Configuration.PotionEffects.3.To", String.valueOf("0"));	
    		}
    		if(getCustomConfig().get("Configuration.PotionEffects.4")==null){
    			getCustomConfig().set("Configuration.PotionEffects.4.Effect", String.valueOf("Blidness"));	
        		getCustomConfig().set("Configuration.PotionEffects.4.EffectLevel", Integer.valueOf(1));
        		getCustomConfig().set("Configuration.PotionEffects.4.From", String.valueOf("1000"));	
        		getCustomConfig().set("Configuration.PotionEffects.4.To", String.valueOf("0"));	
    		}
    		if(getCustomConfig().get("Configuration.PotionEffects.5")==null){
    			getCustomConfig().set("Configuration.PotionEffects.5.Effect", String.valueOf("Black-Out"));	
        		getCustomConfig().set("Configuration.PotionEffects.5.EffectLevel", Integer.valueOf(1));
        		getCustomConfig().set("Configuration.PotionEffects.5.From", String.valueOf("200"));	
        		getCustomConfig().set("Configuration.PotionEffects.5.To", String.valueOf("0"));	
    		}
    	}
    	if(getCustomConfig().get("Configuration.EnergyDrinksAndFood.0")==null){
    		getCustomConfig().set("Configuration.EnergyDrinksAndFood.0.Material", String.valueOf("POTION"));	
    		getCustomConfig().set("Configuration.EnergyDrinksAndFood.0.Amount", Integer.valueOf(1));
    		getCustomConfig().set("Configuration.EnergyDrinksAndFood.0.HEPT", Integer.valueOf(1));
    		getCustomConfig().set("Configuration.EnergyDrinksAndFood.0.DisplayName", String.valueOf("&eRedBull"));	
    		getCustomConfig().set("Configuration.EnergyDrinksAndFood.0.Lore", String.valueOf("&cUse this item to remove those bad sleepy effects!"));
    		getCustomConfig().set("Configuration.EnergyDrinksAndFood.0.Recipe", String.valueOf("DIAMOND_BLOCK,DIAMOND_BLOCK,DIAMOND_BLOCK,DIAMOND_BLOCK,POTION,DIAMOND_BLOCK,DIAMOND_BLOCK,DIAMOND_BLOCK,DIAMOND_BLOCK"));	
    	}
    }
}
