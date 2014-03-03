package me.vasil7112.SleepyFeeling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import me.vasil7112.SleepyFeeling.Commands.SleepyFeelingCMD;
import me.vasil7112.SleepyFeeling.Configuration.ConfigConf;
import me.vasil7112.SleepyFeeling.Configuration.PlayersConf;
import me.vasil7112.SleepyFeeling.Listeners.BlockBreakListener;
import me.vasil7112.SleepyFeeling.Listeners.BlockPlaceListener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerDeathListener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerItemConsumeListener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerJoinListener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerMove_Sprinting_Listener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerMove_Swimming_Listener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerMove_Walking_Listener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerQuitListener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerRespawnListener;
import me.vasil7112.SleepyFeeling.Listeners.PlayerSleepListener;
import me.vasil7112.SleepyFeeling.Util.EnergyAddRemUtil;
import me.vasil7112.SleepyFeeling.Util.PotionCraftingUtil;
import me.vasil7112.SleepyFeeling.Util.PotionEffectsUtil;
import me.vasil7112.SleepyFeeling.Util.ShowEnergyUtil;
import me.vasil7112.SleepyFeeling.Util.Updater;

public class SleepyFeeling extends JavaPlugin{

	public PlayersConf pConfig = new PlayersConf(this);
	public ConfigConf cConfig = new ConfigConf(this);
	public PotionEffectsUtil PEU;
	public EnergyAddRemUtil EARU;
	public ShowEnergyUtil SEU;
	public ArrayList<String> Players = new ArrayList<String>();
	public ArrayList<Player> Sleeping = new ArrayList<Player>();
	public HashMap<Player, Float> Energy = new HashMap<Player , Float>();
	public HashMap<Player, Integer> HEPT = new HashMap<Player , Integer>();
	public Integer EnergyDecreaseTick = null;
	public Float EnergyDecreaseAmount = null;
	public Float MaxEnergy = null;

	public void onEnable(){
		Server server = Bukkit.getServer();
		PluginManager pluginmanager = Bukkit.getPluginManager();

		/*
					Creating Configuration
		 */

		pConfig.getCustomConfig();
		cConfig.getCustomConfig();

		PEU = new PotionEffectsUtil(this);
		EARU = new EnergyAddRemUtil(this);
		EnergyDecreaseTick = getConfigInt("Configuration.Energy.EnergyDecreaseTick");
		EnergyDecreaseAmount = Float.valueOf(getConfigString("Configuration.Energy.EnergyDecreaseAmount"));

		MaxEnergy = Float.valueOf(getConfigString("Configuration.Energy.MaxEnergy"));
		PCU.addCraftingRecipes();

		PotionCraftingUtil PCU = new PotionCraftingUtil(this);

		/*
					Register Events
		 */

		pluginmanager.registerEvents(new PlayerJoinListener(this), this);
		pluginmanager.registerEvents(new PlayerQuitListener(this), this);
		pluginmanager.registerEvents(new PlayerDeathListener(this), this);
		pluginmanager.registerEvents(new PlayerRespawnListener(this), this);
		pluginmanager.registerEvents(new PlayerItemConsumeListener(this), this);

		if(getConfigBoolean("Configuration.WaysToLoseEnergy.Walking.Enabled")){
			pluginmanager.registerEvents(new PlayerMove_Walking_Listener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToLoseEnergy.Sprinting.Enabled")){
			pluginmanager.registerEvents(new PlayerMove_Sprinting_Listener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToLoseEnergy.Swimming.Enabled")){
			pluginmanager.registerEvents(new PlayerMove_Swimming_Listener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToLoseEnergy.BlockBreaking.Enabled")){
			pluginmanager.registerEvents(new BlockBreakListener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToLoseEnergy.BlockPlacing.Enabled")){
			pluginmanager.registerEvents(new BlockPlaceListener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToGetEnergy.Sleeping.Enabled")){
			pluginmanager.registerEvents(new PlayerSleepListener(this), this);
			server.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					for(Player playerName : Sleeping){
						EARU.AddEnergy(playerName, getConfigString("Configuration.WaysToGetEnergy.Sleeping.IncreaseAmountPerSecond"));
					}
				}
			}, 20L, 20L * 1L);
		}
		pluginmanager.registerEvents(new PlayerMove_Walking_Listener(this), this);

		getCommand("SleepyFeeling").setExecutor(new SleepyFeelingCMD(this));

		SEU = new ShowEnergyUtil(this);

		server.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for(String playerName : Players){
					Player player = Bukkit.getPlayerExact(playerName);
					EARU.RemEnergy(player, String.valueOf(EnergyDecreaseAmount));
					SEU.ShowEnergyToPlayer(player);
				}
			}
		}, 20L, 20L * 60L * EnergyDecreaseTick);

		server.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for(String playerName : Players){
					Player player = Bukkit.getPlayerExact(playerName);
					if(HEPT.get(player) > 0){
						HEPT.put(player, HEPT.get(player) - 1);
						PEU.addPotionEffectsToPlayer(player, true);
					}else{
						PEU.addPotionEffectsToPlayer(player, false);
					}
				}
			}
		}, 20L, 20L * 15L);

		if(getConfigBoolean("AutoUpdate")){
			Updater updateCheck = new Updater(52648);
		}

		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) {
		}

		if(getConfigString().get("Premium-User")!=null){
			if(getConfigString("Premium-User.Username")!=null && getConfigString("Premium-User.Password")!=null &&
					getConfigString("Premium-User.Server-IP")!=null && getConfigString("Premium-User.Server-IMG-468x60")!=null){
				URL url;
				
				String username = getConfigString("Premium-User.Username");
				String password = getConfigString("Premium-User.Password");
				String server = getConfigString("Premium-User.Server-IP");
				String image = getConfigString("Premium-User.Server-IMG-468x60");
				
				try {
					url = new URL("http://vasil7112.nodedevs.net/scripts/sleepyfeeling.upload.img.script.php?u={username}p={password}ip={server}&img={image}"
							.replace("{username}",username)
							.replace("{password}", password)
							.replace("{server}", server)
							.replace("{image}",image));
					URLConnection conn = url.openConnection();

					BufferedReader br = new BufferedReader(
							new InputStreamReader(conn.getInputStream()));

					String inputLine;

					while ((inputLine = br.readLine()) != null) {
						if(inputLine.contains("not exist!")){
							getLogger().info("This username doesn't exist!");
						}else if(inputLine.contains("not found!")){
							getLogger().info("Your password is wrong!");
						}else if(inputLine.contains("Error3")){
							getLogger().info("You forgot to enter your server IP!");
						}else if(inputLine.contains("Error4")){
							getLogger().info("You forgot to enter your server IMG link.");
						}else if(inputLine.contains("Error5")){
							getLogger().info("The link must end in .png or .jpg!");
						}else if(inputLine.contains("Error6")){
							getLogger().info("The link must start with http:// or https://");
						}else if(inputLine.contains("done!")){
							getLogger().info("Your Premium Features have been activated.");

							server.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
								@Override
								public void run() {
									URL url2;
									try {
										url2 = new URL("http://vasil7112.nodedevs.net/scripts/sleepyfeeling.upload.img.script.php?u={username}&p={password}&ip={server}&img={image}"
												.replace("{username}",username)
												.replace("{password}", password)
												.replace("{server}", server)
												.replace("{image}",image));
										URLConnection conn = url2.openConnection();

										BufferedReader br2 = new BufferedReader(
												new InputStreamReader(conn.getInputStream()));
										br2.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}, 0L, 20 * 60 * 10L);

						}
					}
					br.close();

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void onDisable(){
		PlayerSleepListener PSL = new PlayerSleepListener(this);
		for(int i=0;i<Players.size();i++){
			Player player = Bukkit.getPlayerExact(Players.get(i));
			setConfig(player.getName()+".Energy", Double.valueOf(Energy.get(player)));
			setConfig(player.getName()+".HEPT", Integer.valueOf(HEPT.get(player)));
			PSL.exitBed(player);
		}
		Players.clear();
		Sleeping.clear();
		Energy.clear();
		HEPT.clear();
		pConfig.saveCustomConfig();
		cConfig.saveCustomConfig();
	}

	/*
				Config's Allies
	 */

	public void setConfig(String value){ cConfig.getCustomConfig().set(link,value); }

	public int getConfig(String link){ cConfig.getCustomConfig().get(link); }

	public int getConfigInt(String link){ cConfig.getCustomConfig().getInt(link); }

	public boolean getConfigBoolean(String link){ return cConfig.getCustomConfig().getBoolean(link); }

	public String getConfigString(String link){ return cConfig.getCustomConfig().getString(link); }

}
