package me.vasil7112.SleepyFeeling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import com.earth2me.essentials.User;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.vasil7112.SleepyFeeling.Schedules.ConnectionScheclule;
import me.vasil7112.SleepyFeeling.Schedules.EnergyDecreaseScheclule;
import me.vasil7112.SleepyFeeling.Schedules.PotionEffectsScheclule;
import me.vasil7112.SleepyFeeling.Schedules.WaysToGetEnergySchecule;
import net.ess3.api.IEssentials;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
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
import org.bukkit.scheduler.BukkitScheduler;

public class SleepyFeeling extends JavaPlugin{

	public static Permission permissions = null;
	public static WorldGuardPlugin worldguardPlugin = null;
	public static IEssentials essentials = null;

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

	public void onLoad(){
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

		pConfig.getCustomConfig();
		cConfig.getCustomConfig();

		SEU = new ShowEnergyUtil(this);
		PEU = new PotionEffectsUtil(this);
		EARU = new EnergyAddRemUtil(this);
		EnergyDecreaseTick = getConfigInt("Configuration.Energy.EnergyDecreaseTick");
		EnergyDecreaseAmount = Float.valueOf(getConfigString("Configuration.Energy.EnergyDecreaseAmount"));
		MaxEnergy = Float.valueOf(getConfigString("Configuration.Energy.MaxEnergy"));

		if(getConfigBoolean("AutoUpdate")){	Updater updateCheck = new Updater(52648); }

		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) { e.printStackTrace(); }

		if(getConfigString("Premium-User")!=null) {
			if(getConfigString("Premium-User.Username")!=null && getConfigString("Premium-User.Password")!=null &&
					getConfigString("Premium-User.Server-IP")!=null && getConfigString("Premium-User.Server-IMG-468x60")!=null){
				URL url;

				try {
					url = new URL("http://vasil7112.nodedevs.net/scripts/sleepyfeeling.upload.img.script.php?u={username}p={password}ip={server}&img={image}"
							.replace("{username}",getConfigString("Premium-User.Username"))
							.replace("{password}", getConfigString("Premium-User.Password"))
							.replace("{server}", getConfigString("Premium-User.Server-IP"))
							.replace("{image}",getConfigString("Premium-User.Server-IMG-468x60")));

					URLConnection conn = url.openConnection();

					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					String inputLine;

					while ((inputLine = br.readLine()) != null) {
						if(inputLine.contains("not exist!")){ getLogger().info("This username doesn't exist!");	}
						else if(inputLine.contains("not found!")){ getLogger().info("Your password is wrong!");	}
						else if(inputLine.contains("Error3")){ getLogger().info("You forgot to enter your server IP!"); }
						else if(inputLine.contains("Error4")){ getLogger().info("You forgot to enter your server IMG link."); }
						else if(inputLine.contains("Error5")){ getLogger().info("The link must end in .png or .jpg!"); }
						else if(inputLine.contains("Error6")){ getLogger().info("The link must start with http:// or https://"); }
						else if(inputLine.contains("done!")){
							getLogger().info("Your Premium Features have been activated.");
							scheduler.runTaskTimer(this, new ConnectionScheclule(this), 20L, 20L * 15L);
						}
					}
					br.close();

				} catch (MalformedURLException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
			}
		}
	}

	public void onEnable() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

		setupPermissions();
		setupWorldGuard();
		setupIEssentials();

		PotionCraftingUtil PCU = new PotionCraftingUtil(this);
		PCU.addCraftingRecipes();

		if(getConfigBoolean("Configuration.WaysToLoseEnergy.Walking.Enabled")) {
			pluginManager.registerEvents(new PlayerMove_Walking_Listener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToLoseEnergy.Sprinting.Enabled")) {
			pluginManager.registerEvents(new PlayerMove_Sprinting_Listener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToLoseEnergy.Swimming.Enabled")) {
			pluginManager.registerEvents(new PlayerMove_Swimming_Listener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToLoseEnergy.BlockBreaking.Enabled")) {
			pluginManager.registerEvents(new BlockBreakListener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToLoseEnergy.BlockPlacing.Enabled")) {
			pluginManager.registerEvents(new BlockPlaceListener(this), this);
		}
		if(getConfigBoolean("Configuration.WaysToGetEnergy.Sleeping.Enabled")) {
			pluginManager.registerEvents(new PlayerSleepListener(this), this);
			scheduler.runTaskTimer(this, new WaysToGetEnergySchecule(this), 20L, 20L * 1L);
		}

		pluginManager.registerEvents(new PlayerJoinListener(this), this);
		pluginManager.registerEvents(new PlayerQuitListener(this), this);
		pluginManager.registerEvents(new PlayerDeathListener(this), this);
		pluginManager.registerEvents(new PlayerRespawnListener(this), this);
		pluginManager.registerEvents(new PlayerItemConsumeListener(this), this);
		pluginManager.registerEvents(new PlayerMove_Walking_Listener(this), this);

		getCommand("SleepyFeeling").setExecutor(new SleepyFeelingCMD(this));

		scheduler.runTaskTimer(this, new EnergyDecreaseScheclule(this), 20L, 20L * 60L * EnergyDecreaseTick);
		scheduler.runTaskTimer(this, new PotionEffectsScheclule(this), 20L, 20L * 15L);
	}

	public void onDisable() {
		PlayerSleepListener PSL = new PlayerSleepListener(this);
		for(int i=0;i<Players.size();i++){
			Player player = Bukkit.getPlayerExact(Players.get(i));
			pConfig.getCustomConfig().set(player.getName()+".Energy", Double.valueOf(Energy.get(player)));
			pConfig.getCustomConfig().set(player.getName()+".HEPT", Integer.valueOf(HEPT.get(player)));
			PSL.exitBed(player);
		}
		Players.clear();
		Sleeping.clear();
		Energy.clear();
		HEPT.clear();
		pConfig.saveCustomConfig();
		cConfig.saveCustomConfig();
	}

	public long getConfigLong(String link){ return cConfig.getCustomConfig().getLong(link); }

	public int getConfigInt(String link){ return cConfig.getCustomConfig().getInt(link); }

	public boolean getConfigBoolean(String link){ return cConfig.getCustomConfig().getBoolean(link); }

	public String getConfigString(String link){ return cConfig.getCustomConfig().getString(link); }

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		permissions = rsp.getProvider();
		return permissions != null;
	}

	private void setupWorldGuard() {
		Plugin wg = this.getServer().getPluginManager().getPlugin("WorldGuard");
		if(wg == null) {
			getLogger().info("WorldGuard was not found. Did you know that by using it, players won't be able to place tents on protected areas?");
		}else{
			worldguardPlugin = (WorldGuardPlugin)wg;
		}
	}

	private void setupIEssentials() {
		Plugin ess = this.getServer().getPluginManager().getPlugin("Essentials");
		if(ess == null) {
			getLogger().info("Essentials was not found. Did you know that by using it, players won't be able to place tents on protected areas?");
		}else{
			essentials = (IEssentials)ess;
		}
	}

	public static boolean hasPermission(Player player, String permission) {
		if (permissions != null){
			if(permissions.has(player,permission)||player.isOp()){ return true; }
		} else {
			if(player.hasPermission(permission)||player.isOp()){ return true; }
		}
		return false;
	}

	public static boolean hasGod(Player player) {
		if (essentials != null){
			User user = essentials.getUser(player);
			return user.isGodModeEnabled();
		} else {
			return false;
		}
	}

}
