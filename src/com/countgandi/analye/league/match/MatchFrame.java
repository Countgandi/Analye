package com.countgandi.analye.league.match;

import java.util.ArrayList;

public class MatchFrame {
	
	public long timestamp;
	
	public ArrayList<MatchTimelineEvent> events = new ArrayList<MatchTimelineEvent>();
	public ArrayList<MatchPlayerFrame> players = new ArrayList<MatchPlayerFrame>();

}
