package com.countgandi.analye.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.countgandi.analye.Globals;

public class SearchPage extends JPanel {
	private static final long serialVersionUID = 1L;

	private JPanel searchBarPanel = new JPanel();
	private JButton searchButton = new JButton("Search");
	private JTextField summonerSearchField = new JTextField(50);

	private PlayerPage player;

	public SearchPage(Window window) {
		this.setLayout(new BorderLayout());
		JLabel searchLabel = new JLabel("Enter Summoner Name: ");
		searchLabel.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		searchBarPanel.add(searchLabel);
		searchBarPanel.add(summonerSearchField);
		searchBarPanel.add(searchButton);
		
		searchButton.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		summonerSearchField.setFont(new Font(Globals.FONT, Font.PLAIN, 12));

		this.add(searchBarPanel, BorderLayout.NORTH);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getID() == ActionEvent.ACTION_PERFORMED) {
					search(window);
				}
			}
		});
		
		summonerSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					search(window);
				}
			}
		});
	}

	public void search(Window window) {
		String sumName = summonerSearchField.getText().trim();
		
		sumName = sumName.replaceAll(" ", "%20");
		
		if (player == null) {
			player = new PlayerPage(sumName);
			this.add(player, BorderLayout.CENTER);
		} else {
			this.remove(player);
			player = new PlayerPage(sumName);
			this.add(player, BorderLayout.CENTER);
		}
		window.revalidate();
	}

}
