package com.countgandi.analye.league.match;

import java.util.HashMap;

import com.countgandi.analye.league.game.Champion;


/**
 * 
 * @author Count
 *
 * Have not made an attempt to implement post game rune stats, there is a lot I just don't know about the way Riot names stuff. Too much work
 *
 */
public class Player {
	
	public static final int RED = 200, BLUE = 100;

	public int profileIcon;
	public String summonerName;
	public int participantId;
	public Champion champion;
	public int teamId;
	public int spell1Id;
	public int spell2Id;
	public boolean win;
	
	public String role;
	public String lane;
	
	public HashMap<String, Double> csDiffPerMinDeltas;
	public HashMap<String, Double> damageTakenPerMinDeltas;
	public HashMap<String, Double> damageTakenDiffPerMinDeltas;
	public HashMap<String, Double> xpPerMinDeltas;
	public HashMap<String, Double> xpDiffPerMinDeltas;
	public HashMap<String, Double> creepsPerMinDeltas;
	public HashMap<String, Double> goldPerMinDeltas;

	public int[] items = new int[6];
	
	@NameAnnotation(name = "Champion Level", space = true)
	public int champLevel;
	@NameAnnotation(name = "Kills")
	public int kills;
	@NameAnnotation(name = "Deaths")
	public int deaths;
	@NameAnnotation(name = "Assists", space = true)
	public int assists;
	@NameAnnotation(name = "Gold Earned")
	public int goldEarned;
	@NameAnnotation(name = "Gold Spent", space = true)
	public int goldSpent;
	
	@NameAnnotation(name = "Total Minions Killed")
	public int totalMinionsKilled;
	@NameAnnotation(name = "Total Jungle Minions Killed")
	public int neutralMinionsKilled;
	@NameAnnotation(name = "Team Jungle Minions Killed")
	public int neutralMinionsKilledTeamJungle;
	@NameAnnotation(name = "Enemy Jungle Minions Killed", space = true)
	public int neutralMinionsKilledEnemyJungle;
	
	@NameAnnotation(name = "Vision Score")
	public long visionScore;
	@NameAnnotation(name = "Wards Placed")
	public int wardsPlaced;
	@NameAnnotation(name = "Wards Killed")
	public int wardsKilled;
	@NameAnnotation(name = "Vision Wards Bought")
	public int visionWardsBoughtInGame;
	@NameAnnotation(name = "Sight Wards Bought", space = true)
	public int sightWardsBoughtInGame;
	
	public int largestMultikill;
	@NameAnnotation(name = "Double Kills")
	public int doubleKills;
	@NameAnnotation(name = "Triple Kills")
	public int tripleKills;
	@NameAnnotation(name = "Quadra Kills")
	public int quadraKills;
	@NameAnnotation(name = "Penta Kills")
	public int pentaKills;
	@NameAnnotation(name = "Largest Killing Spree")
	public int largestKillingSpree;
	@NameAnnotation(name = "Killings Sprees")
	public int killingSprees;
	@NameAnnotation(name = "Unreal Kills")
	public int unrealKills;
	@NameAnnotation(name = "First Blood Kill")
	public boolean firstBloodKill;
	@NameAnnotation(name = "First Blood Assist", space = true)
	public boolean firstBloodAssist;
	
	@NameAnnotation(name = "Damage Dealt To Objectives")
	public long damageDealtToObjectives;
	@NameAnnotation(name = "Damage Dealt To Turrents", space = true)
	public long damageDealtToTurrets;
	
	@NameAnnotation(name = "First Tower Kill")
	public boolean firstTowerKill;
	@NameAnnotation(name = "First Tower Assist")
	public boolean firstTowerAssist;
	@NameAnnotation(name = "Tower Kills", space = true)
	public int turretKills;
	
	@NameAnnotation(name = "First Inhibitor Kill")
	public boolean firstInhibitorKill;
	@NameAnnotation(name = "First Inhibitor Assist")
	public boolean firstInhibitorAssist;
	@NameAnnotation(name = "Inhibitor Kills", space = true)
	public int inhibitorKills;
	
	@NameAnnotation(name = "Damage Self Mitigated")
	public long damageSelfMitigated;
	@NameAnnotation(name = "Objective Player Score")
	public int objectivePlayerScore;
	@NameAnnotation(name = "Combat Player Score")
	public int combatPlayerScore;
	@NameAnnotation(name = "Total Score Rank")
	public int totalScoreRank;
	@NameAnnotation(name = "Longest Time Spent Living", space = true)
	public int longestTimeSpentLiving;
	
	@NameAnnotation(name = "Largest Critical Strike", space = true)
	public int largestCriticalStrike;
	
	@NameAnnotation(name = "Total Time Crowd Control Dealt")
	public int totalTimeCrowdControlDealt;
	@NameAnnotation(name = "Time Crowd Controlling Others", space = true)
	public long timeCCingOthers;
	
	@NameAnnotation(name = "True Damage Taken")
	public long trueDamageTaken;
	@NameAnnotation(name = "True Damage Dealt")
	public long trueDamageDealt;
	@NameAnnotation(name = "True Damage Dealt To Champions", space = true)
	public long trueDamageDealtToChampions;
	
	@NameAnnotation(name = "Magical Damage Taken")
	public long magicalDamageTaken;
	@NameAnnotation(name = "Magic Damage Dealt")
	public long magicDamageDealt;
	@NameAnnotation(name = "Magic Damage Dealt To Champions", space = true)
	public long magicDamageDealtToChampions;
	
	@NameAnnotation(name = "Physical Damage Taken")
	public long physicalDamageTaken;
	@NameAnnotation(name = "Physical Damage Dealt")
	public long physicalDamageDealt;
	@NameAnnotation(name = "Physical Damage Dealt to Champions", space = true)
	public long physicalDamageDealtToChampions;
	
	@NameAnnotation(name = "Total Player Score")
	public int totalPlayerScore;
	@NameAnnotation(name = "Total Damage Taken")
	public long totalDamageTaken;
	@NameAnnotation(name = "Total Damage Dealt")
	public long totalDamageDealt;
	@NameAnnotation(name = "Total Damage Dealt To Champions")
	public long totalDamageDealtToChampions;
	@NameAnnotation(name = "Total Healing")
	public long totalHeal;
	@NameAnnotation(name = "Total Units Healed", space = true)
	public int totalUnitsHealed;

	public String toString() {
		return summonerName;
	}
	
}
