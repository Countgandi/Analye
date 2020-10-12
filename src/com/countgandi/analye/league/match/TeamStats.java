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
	@NameAnnotation(name = "Baron Kills", space = true)
	public int baronKills;
	
	public int teamId;
	
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
	@NameAnnotation(name = "First Baron")
	public boolean firstBaron;
	
}
