package com.countgandi.analye.league;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

import com.countgandi.analye.Globals;

public class SummonerAccount {

	public ImageIcon icon;
	public String accountId;
	public int profileIconId = 0;
	public long revisionDate = 0;
	public String name;
	public String id;
	public String puuid;
	public long summonerLevel = 0;
	public ArrayList<TempMatch> matchHistory = new ArrayList<TempMatch>();

	public void init() {
		icon = Globals.getImage("/img/profileicon/" + profileIconId + ".png", 64);
		updateMatchHistory(0);
	}

	public void updateMatchHistory(int page) {
		JSONObject object = LeagueHandler.getMatchHistory(page * 20, (page + 1) * 20, accountId);

		JSONArray matches = object.getJSONArray("matches");
		for (int i = 0; i < 20; i++) {
			JSONObject obj = matches.getJSONObject(i);
			TempMatch match = new TempMatch();

			match.gameId = obj.getLong("gameId");
			match.season = obj.getInt("season");
			match.platformId = obj.getString("platformId");
			match.champion = obj.getInt("champion");
			match.queue = obj.getInt("queue");
			match.timestamp = obj.getLong("timestamp");
			match.gameRole = getRole(obj.getString("role"), obj.getString("lane"));
			
			if (new String(match.queue + "").startsWith("4")) {
				matchHistory.add(match);
			}
		}

	}

	private static String getRole(String role, String lane) {
		String name = "Error finding role data...";
		role = role.trim();
		lane = lane.trim();

		if (lane.equals("TOP")) {
			name = "Top";
		} else if (lane.equals("MID")) {
			name = "Mid";
		} else if (lane.equals("JUNGLE")) {
			name = "Jungle";
		} else if (role.equals("DUO_SUPPORT")) {
			name = "Support";
		} else if (role.equals("DUO") || role.equals("DUO_CARRY")) {
			name = "Adc";
		}

		if (name.equals("Error finding role data...")) {
			System.out.println(role + " " + lane);
		}

		return name;
	}

	public class TempMatch {
		public long gameId = 0;
		public String gameRole;
		public int season = 0;
		public String platformId;
		public int champion = 0;
		public int queue = 0;
		public long timestamp = 0;
	}
}