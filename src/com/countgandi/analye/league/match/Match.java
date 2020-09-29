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
	
}
