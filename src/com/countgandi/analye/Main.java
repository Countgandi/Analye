package com.countgandi.analye;

import javax.swing.UIManager;

import com.countgandi.analye.gui.Window;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {

	public Main() {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		
		Globals.loadChampions();
		
		new Window(Globals.WIDTH, Globals.HEIGHT, Globals.TITLE);
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
