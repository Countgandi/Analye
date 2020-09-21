package com.countgandi.analye.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private SearchPage searchPage;

	public Window(int width, int height, String title) {
		searchPage = new SearchPage(this);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(1024, 576));
		this.setMaximumSize(new Dimension(1920, 1080));
		this.setPreferredSize(new Dimension(1280, 720));
		
		this.add(searchPage);
	
		this.setVisible(true);
		
	}
	
}
