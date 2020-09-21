package com.countgandi.analye;

import com.countgandi.analye.league.LeagueHandler;

public class Main {

	public Main() {
		LeagueHandler lolHandler = new LeagueHandler();
		System.out.println(lolHandler.getMatch("3583773572"));
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
