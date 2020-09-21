package com.countgandi.analye;

import javax.swing.UIManager;

import com.countgandi.analye.gui.Window;
import com.countgandi.analye.league.LeagueHandler;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {

	public Main() {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		//LeagueHandler lolHandler = new LeagueHandler();
		//System.out.println(lolHandler.getMatch("3583773572"));
		new Window(Constants.WIDTH, Constants.HEIGHT, Constants.TITLE);
	}

	public static void main(String[] args) {
		new Main();
	}

}
