package me.vasil7112.SleepyFeeling.Util;

import org.bukkit.entity.Player;
import static me.vasil7112.SleepyFeeling.Util.ChatFormat.*;
import me.vasil7112.SleepyFeeling.SleepyFeeling;

public class ShowEnergyUtil {

	private final SleepyFeeling sleepyFeeling;

	public ShowEnergyUtil(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}
	
	public void ShowEnergyToPlayer(Player player){
		Float energy = sleepyFeeling.Energy.get(player);
		player.sendMessage(getwEnergy(energy,"Your"));
	}

	public void ShowEnergyOfPlayer(Player player, Player target){
		Float energy = sleepyFeeling.Energy.get(target);
		player.sendMessage(getwEnergy(energy,target.getName()+"'s"));
	}

	private String getwEnergy(Float energy, String targetName){
		String energyTexture = null;
		float maxEnergy = sleepyFeeling.MaxEnergy,n10 = Float.valueOf("1.0")*maxEnergy,n9 = Float.valueOf("0.9")*maxEnergy,
				n8 = Float.valueOf("0.8")*maxEnergy,n7 = Float.valueOf("0.7")*maxEnergy,n6 = Float.valueOf("0.6")*maxEnergy,
				n5 = Float.valueOf("0.5")*maxEnergy,n4 = Float.valueOf("0.4")*maxEnergy,n3 = Float.valueOf("0.3")*maxEnergy,
				n2 = Float.valueOf("0.2")*maxEnergy,n1 = Float.valueOf("0.1")*maxEnergy,n0 = Float.valueOf("0.0")*maxEnergy;

		if(checkEnergy(energy,n9,n10)){ 	energyTexture = "&2IIIIIIIIII&4"; }
		else if(checkEnergy(energy,n8,n9)){ energyTexture = "&2IIIIIIIII&4I"; }
		else if(checkEnergy(energy,n7,n8)){ energyTexture = "&2IIIIIIII&4II"; }
		else if(checkEnergy(energy,n6,n7)){ energyTexture = "&2IIIIIII42III"; }
		else if(checkEnergy(energy,n5,n6)){ energyTexture = "&2IIIIII&4IIII"; }
		else if(checkEnergy(energy,n4,n5)){ energyTexture = "&2IIIII&4IIIII"; }
		else if(checkEnergy(energy,n3,n4)){ energyTexture = "&2III&4IIIIIII"; }
		else if(checkEnergy(energy,n2,n3)){ energyTexture = "&2II&4IIIIIIII"; }
		else if(checkEnergy(energy,n1,n2)){ energyTexture = "&2I&4IIIIIIIII"; }
		else if(checkEnergy(energy,n0,n1)){ energyTexture = "&4IIIIIIIIII&2"; }

		return withColor("&2{username} energy: &6[{energy}&6]"
				.replace("username",targetName)
				.replace("{energy}",energyTexture));
	}

	private boolean checkEnergy(float energy,float n1Energy,float n2Energy){
		if((energy >= n1Energy) && (energy <= n2Energy)){
			return true;
		} else {
			return false;
		}
	}

}
