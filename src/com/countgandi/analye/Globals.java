package com.countgandi.analye;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.JSONObject;

import com.countgandi.analye.league.game.Champion;

public class Globals {
	
	public static Champion[] CHAMPIONS;
	
	public static final String API_KEY = "RGAPI-6953a9c7-e686-4d5f-b3e8-7d8ce1a9f489";
	public static final String ROUTING_VALUE = ".api.riotgames.com";
	public static String REGION = "na1";
	
	public static final String FONT = "Comic Sans MS";
	public static final int WIDTH = 1280, HEIGHT = 720;
	public static final String TITLE = "Analye";

	public static final Color BLUE = new Color(0xFFcbe8f2), RED = new Color(0xFFf2cbcb);

	public static ImageIcon getImage(String path, int size) {
		Image img = null;
		try {
			Image nimg = ImageIO.read(Globals.class.getResource(path));
			img = nimg.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ImageIcon(img);
	}
	
	public static Champion getChampion(int id) {
		for(int i = 0; i < CHAMPIONS.length; i++) {
			if (CHAMPIONS[i].id == id) return CHAMPIONS[i];
		}
		return null;
	}
	
	public static void loadChampions() {
		String ss = "";
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(Globals.class.getResourceAsStream("/data/en_US/champion.json")));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				ss += inputLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject(ss);
		JSONObject data = obj.getJSONObject("data");
		
		ArrayList<Champion> champs = new ArrayList<Champion>();
		
		for(String key: data.keySet()) {
			JSONObject champ = data.getJSONObject(key);
			Champion c = new Champion();
			
			c.id = champ.getInt("key");
			c.name = champ.getString("name");
			c.nameId = champ.getString("id");
			c.img = getImage("/img/champion/" + c.nameId + ".png", 64);
			
			champs.add(c);
		}
		
		CHAMPIONS = new Champion[champs.size()];
		for(int j = 0; j < champs.size(); j++) {
			CHAMPIONS[j] = champs.get(j);
		}
		
	}
	
}
