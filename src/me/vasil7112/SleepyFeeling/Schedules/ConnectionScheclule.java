package me.vasil7112.SleepyFeeling.Schedules;

import me.vasil7112.SleepyFeeling.SleepyFeeling;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nikosgram13 on 3/3/14.
 */
public class ConnectionScheclule extends BukkitRunnable {

	private final SleepyFeeling sleepyFeeling;

	public ConnectionScheclule(SleepyFeeling sleepyFeeling) {
		this.sleepyFeeling = sleepyFeeling;
	}

	@Override
	public void run() {
		URL url2;
		try {
			url2 = new URL("http://vasil7112.nodedevs.net/scripts/sleepyfeeling.upload.img.script.php?u={username}&p={password}&ip={server}&img={image}"
					.replace("{username}",sleepyFeeling.getConfigString("Premium-User.Username"))
					.replace("{password}",sleepyFeeling.getConfigString("Premium-User.Password"))
					.replace("{server}",sleepyFeeling.getConfigString("Premium-User.Server-IP"))
					.replace("{image}",sleepyFeeling.getConfigString("Premium-User.Server-IMG-468x60")));

			URLConnection conn = url2.openConnection();

			BufferedReader br2 = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			br2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
