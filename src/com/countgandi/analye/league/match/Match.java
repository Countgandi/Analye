package com.countgandi.analye.league.match;

import java.util.ArrayList;

public class Match {

	public ArrayList<Player> players = new ArrayList<Player>(10), red = new ArrayList<Player>(5), blue = new ArrayList<Player>(5);
	public ArrayList<MatchFrame> frames = new ArrayList<MatchFrame>();
	public TeamStats blueTeam, redTeam;
	public long gameId;
	public int queueId;
	public String gameType;
	public long gameDuration;
	public String platformId;
	public long gameCreation;
	public int seasonId;
	public String gameVersion;
	public int mapId;
	public String gameMode;
	
	// blue = true
	@NameAnnotation(name = "Win", space = true)
	public boolean win;
	@NameAnnotation(name = "First Blood")
	public boolean firstBlood;
	@NameAnnotation(name = "First Tower")
	public boolean firstTower;
	@NameAnnotation(name = "First Inhibitor")
	public boolean firstInhibitor;
	@NameAnnotation(name = "First Rift Herald")
	public boolean firstRiftHerald;
	@NameAnnotation(name = "First Dragon")
	public boolean firstDragon;
	@NameAnnotation(name = "First Baron", space = true)
	public boolean firstBaron;
	
}
