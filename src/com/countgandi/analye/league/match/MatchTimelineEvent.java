package com.countgandi.analye.league.match;

public class MatchTimelineEvent {
	//for type: CHAMPION_KILL, WARD_PLACED, WARD_KILL, BUILDING_KILL, ELITE_MONSTER_KILL, ITEM_PURCHASED, ITEM_SOLD, ITEM_DESTROYED, ITEM_UNDO, SKILL_LEVEL_UP, ASCENDED_EVENT, CAPTURE_POINT, PORO_KING_SUMMON
	
	public long timestamp;
	
	public int participantId;
	
	public int x, y;
	public String laneType;
	public int skillSlot;
	public String ascendedType;
	public int creatorId;
	public int afterId;
	public String eventType;
	public String type;
	public String levelUpType;
	public String wardType;
	public String towerType;
	public int itemId;
	public int beforeId;
	public String monsterType;
	public String monsetSubType;
	public int teamId;
	public int killerId;
	public String buildingType;
	public int victimId;
	
}
