package com.countgandi.analye.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPage extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel titlePanel = new JPanel();
	private JPanel matchHistoryPanel = new JPanel();
	
	public PlayerPage(String summonerName) {
		this.setLayout(new BorderLayout());
		titlePanel.add(new JLabel(summonerName));
		
		
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(matchHistoryPanel, BorderLayout.CENTER);
	}

}
