package com.countgandi.analye.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPage extends JPanel {
	private static final long serialVersionUID = 1L;

	private JPanel searchBarPanel = new JPanel();
	private JButton searchButton = new JButton("Search");
	private JTextField summonerSearchField = new JTextField(50);
	
	private PlayerPage player;
	
	public SearchPage(Window window) {
		this.setLayout(new BorderLayout());
		searchBarPanel.add(new JLabel("Enter Summoner Name: "));
		searchBarPanel.add(summonerSearchField);
		searchBarPanel.add(searchButton);
		
		this.add(searchBarPanel, BorderLayout.NORTH);
		
		SearchPage page = this;
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getID() == ActionEvent.ACTION_PERFORMED) {
					if (player == null) {
						player = new PlayerPage(summonerSearchField.getText().trim());
						page.add(player, BorderLayout.CENTER);
					} else {
						page.remove(player);
						player = new PlayerPage(summonerSearchField.getText().trim());
						page.add(player, BorderLayout.CENTER);
					}
					window.revalidate();
				}
			}
		});
	}
	
}
