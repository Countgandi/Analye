package com.countgandi.analye.league;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.countgandi.analye.Constants;

public class LeagueHandler {

	public JSONObject getMatch(String matchID) {
		String data = readDataFromLeague(Constants.ROUTING_VALUE + "/lol/match/v4/matches/" + matchID);
		return new JSONObject(data);
	}

	private String readDataFromLeague(String url) {
		String data = "";
		try {
			URL line = new URL("https://" + url + "?api_key=" + Constants.API_KEY);
			BufferedReader in = new BufferedReader(new InputStreamReader(line.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				data += inputLine;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
