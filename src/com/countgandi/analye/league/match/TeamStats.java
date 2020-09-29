package com.countgandi.analye.league.match;

import com.countgandi.analye.league.game.Champion;

public class TeamStats {

	public Champion[] bans = new Champion[5];
	
	@NameAnnotation(name = "Tower Kills")
	public int towerKills;
	@NameAnnotation(name = "Inhibitor Kills")
	public int inhibitorKills;
	@NameAnnotation(name = "Rift Herald Kills")
	public int riftHeraldKills;
	@NameAnnotation(name = "Dragon Kills")
	public int dragonKills;
	@NameAnnotation(name = "Baron Kills")
	public int baronKills;
	
	public int teamId;
	
}
