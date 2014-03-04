package me.vasil7112.SleepyFeeling.Util;

/**
 * Created by nikosgram13 on 3/4/14.
 */
public class ChatFormat {

	public static String withoutColor(String msg) {
		return msg.replaceAll("\u00A7([a-f0-9])", "").replaceAll("\u00A7([k-l])", "");
	}

	public static String withColor(String msg) {
		return msg.replaceAll("&([a-f0-9])", "\u00A7$1").replaceAll("&([k-l])", "\u00A7$1");
	}

}
