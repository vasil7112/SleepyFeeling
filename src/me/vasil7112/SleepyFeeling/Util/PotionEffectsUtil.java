package me.vasil7112.SleepyFeeling.Util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import me.vasil7112.SleepyFeeling.SleepyFeeling;
import me.vasil7112.SleepyFeeling.Listeners.PlayerSleepListener;

public class PotionEffectsUtil {

	private SleepyFeeling sleepyFeeling;
	private HashMap<Integer, String> PotionEffect = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> EffectLevel = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> From = new HashMap<Integer, String>();
	private HashMap<Integer, String> To = new HashMap<Integer, String>();
	public PotionEffectsUtil(SleepyFeeling sleepyFeeling){
		this.sleepyFeeling = sleepyFeeling;
		for(int i=0;i< this.sleepyFeeling.cConfig.getCustomConfig().getConfigurationSection("Configuration.PotionEffects").getKeys(false).size();i++){
			PotionEffect.put(i, this.sleepyFeeling.cConfig.getCustomConfig().getString("Configuration.PotionEffects."+i+".Effect"));
			EffectLevel.put(i, this.sleepyFeeling.cConfig.getCustomConfig().getInt("Configuration.PotionEffects."+i+".EffectLevel"));
			From.put(i, this.sleepyFeeling.cConfig.getCustomConfig().getString("Configuration.PotionEffects."+i+".From"));
			To.put(i, this.sleepyFeeling.cConfig.getCustomConfig().getString("Configuration.PotionEffects."+i+".To"));
		}
	}
	
	public void addPotionEffectsToPlayer(Player player, Boolean drink){
		for (PotionEffect effect : player.getActivePotionEffects()){
			if(effect.getType() != PotionEffectType.DAMAGE_RESISTANCE || effect.getType() != PotionEffectType.FAST_DIGGING || effect.getType() != PotionEffectType.FIRE_RESISTANCE || effect.getType() != PotionEffectType.HEAL || effect.getType() != PotionEffectType.HEALTH_BOOST || effect.getType() != PotionEffectType.INCREASE_DAMAGE || effect.getType() != PotionEffectType.JUMP || effect.getType() != PotionEffectType.NIGHT_VISION || effect.getType() != PotionEffectType.REGENERATION || effect.getType() != PotionEffectType.SPEED || effect.getType() != PotionEffectType.WATER_BREATHING){
				 player.removePotionEffect(effect.getType());
			}
		}
		for(int i=0;i<PotionEffect.size();i++){
			if(sleepyFeeling.Energy.get(player) <= Float.valueOf(From.get(i)) && sleepyFeeling.Energy.get(player) >= Float.valueOf(To.get(i))){
				if(drink){
					if(PotionEffect.get(i).equalsIgnoreCase("Death") || PotionEffect.get(i).equalsIgnoreCase("Black-Out")){
						getEffect(player, PotionEffect.get(i), i);
					}
				}else{
					getEffect(player, PotionEffect.get(i), i);
				}
			}
		}
	}
	
	private void getEffect(final Player player, String Effect, int i){
		if(Effect.equalsIgnoreCase("Death")){
			player.setHealth(0.0);
		}else if(Effect.equalsIgnoreCase("Black-Out")){
			final PlayerSleepListener PSL = new PlayerSleepListener(sleepyFeeling);
			PSL.setSleeping(player, player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(sleepyFeeling, new Runnable() {
				@Override
				public void run() {
					if(sleepyFeeling.Sleeping.contains(player)){
						PSL.exitBed(player);
					}
				}
	        }, 20L * sleepyFeeling.cConfig.getCustomConfig().getLong("Configuration.WaysToGetEnergy.Sleeping.AutoWakeUp"));
		}else{
			player.addPotionEffect(new PotionEffect(getPotionEffect(PotionEffect.get(i)), 20*60, EffectLevel.get(i)-1));
		}
	}
	private PotionEffectType getPotionEffect(String Effect){
		if(Effect.equalsIgnoreCase("Blindness")){ return PotionEffectType.BLINDNESS; }
		else if(Effect.equalsIgnoreCase("Absorption")){ return PotionEffectType.ABSORPTION; }
		else if(Effect.equalsIgnoreCase("Confusion")){ return PotionEffectType.CONFUSION; }
		else if(Effect.equalsIgnoreCase("DamageResistance")){ return PotionEffectType.DAMAGE_RESISTANCE; }
		else if(Effect.equalsIgnoreCase("FastDigging")){ return PotionEffectType.FAST_DIGGING; }
		else if(Effect.equalsIgnoreCase("FireResistance")){ return PotionEffectType.FIRE_RESISTANCE; }
		else if(Effect.equalsIgnoreCase("Harm")){ return PotionEffectType.HARM; }
		else if(Effect.equalsIgnoreCase("Heal")){ return PotionEffectType.HEAL; }
		else if(Effect.equalsIgnoreCase("HealthBoost")){ return PotionEffectType.HEALTH_BOOST; }
		else if(Effect.equalsIgnoreCase("Hunger")){ return PotionEffectType.HUNGER; }
		else if(Effect.equalsIgnoreCase("IncreaseDamage")){ return PotionEffectType.INCREASE_DAMAGE; }
		else if(Effect.equalsIgnoreCase("Invisibility")){ return PotionEffectType.INVISIBILITY; }
		else if(Effect.equalsIgnoreCase("Jump")){ return PotionEffectType.JUMP; }
		else if(Effect.equalsIgnoreCase("NightVision")){ return PotionEffectType.NIGHT_VISION; }
		else if(Effect.equalsIgnoreCase("Poison")){ return PotionEffectType.POISON; }
		else if(Effect.equalsIgnoreCase("Regeneration")){ return PotionEffectType.REGENERATION; }
		else if(Effect.equalsIgnoreCase("Saturation")){ return PotionEffectType.SATURATION; }
		else if(Effect.equalsIgnoreCase("Slow")){ return PotionEffectType.SLOW; }
		else if(Effect.equalsIgnoreCase("SlowDigging")){ return PotionEffectType.SLOW_DIGGING; }
		else if(Effect.equalsIgnoreCase("Speed")){ return PotionEffectType.SPEED; }
		else if(Effect.equalsIgnoreCase("WaterBreathing")){ return PotionEffectType.WATER_BREATHING; }
		else if(Effect.equalsIgnoreCase("Weakness")){ return PotionEffectType.WEAKNESS; }
		else if(Effect.equalsIgnoreCase("Wither")){ return PotionEffectType.WITHER; }
		return PotionEffectType.BLINDNESS;
	}

}
