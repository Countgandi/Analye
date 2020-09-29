package com.countgandi.analye.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;

import com.countgandi.analye.Globals;
import com.countgandi.analye.league.LeagueHandler;
import com.countgandi.analye.league.SummonerAccount;

public class PlayerPage extends JPanel {
	private static final long serialVersionUID = 1L;
	private int MatchPanelHeight = 100, previousSize, pageNum;
	
	private JPanel titlePanel = new JPanel();
	private JPanel matchHistoryPanel = new JPanel();
	
	private Dimension MatchHistorySize = new Dimension(1024, 0);
	
	private SummonerAccount account;
	
	public PlayerPage(String summonerName) {
		account = LeagueHandler.getSummoner(summonerName);
		account.init();
		
		this.setLayout(new BorderLayout());
		titlePanel.add(new JLabel(account.icon));
		JLabel name = new JLabel(account.name);
		name.setFont(new Font(Globals.FONT, Font.PLAIN, 36));
		titlePanel.add(name);
		
		JScrollPane matchScrollPane = new JScrollPane(matchHistoryPanel);
		matchScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		matchScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(matchScrollPane, BorderLayout.CENTER);
		
		for(int i = 0; i < account.matchHistory.size(); i++) {
			matchHistoryPanel.add(getMatchPanel(account.matchHistory.get(i)), BorderLayout.NORTH);
			MatchHistorySize.height += MatchPanelHeight;
		}
		previousSize = account.matchHistory.size();
		matchHistoryPanel.setPreferredSize(MatchHistorySize);
		matchHistoryPanel.setMaximumSize(getPreferredSize());
		
		matchScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
		    @Override
		    public void adjustmentValueChanged(AdjustmentEvent ae) {
		        int extent = matchScrollPane.getVerticalScrollBar().getModel().getExtent();
		        if(matchScrollPane.getVerticalScrollBar().getValue()+extent == matchScrollPane.getVerticalScrollBar().getMaximum()) {
		        	pageNum++;
		        	account.updateMatchHistory(pageNum);
		        	for(int i = 0; i < account.matchHistory.size() - previousSize; i++) {
		    			matchHistoryPanel.add(getMatchPanel(account.matchHistory.get(previousSize + i)), BorderLayout.NORTH);
		    			MatchHistorySize.height += MatchPanelHeight;
		    		}
		        	previousSize = account.matchHistory.size();
		        }
		    }
		});
	}
	
	public JPanel getMatchPanel(SummonerAccount.TempMatch match) {
		JPanel panel = new JPanel();
		JPanel center = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JButton viewMatch = new JButton("Analyze");
		viewMatch.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		
		JLabel lane = new JLabel("Lane: " + match.gameRole);
		lane.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		center.add(lane);
		
		panel.add(new JLabel(Globals.getChampion(match.champion).img), BorderLayout.WEST);
		panel.add(viewMatch, BorderLayout.EAST);
		JLabel gameId = new JLabel("Game ID: " + match.gameId);
		gameId.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		panel.add(gameId, BorderLayout.NORTH);
		
		Dimension size = new Dimension(1024, MatchPanelHeight);
		panel.setPreferredSize(size);
		panel.setMaximumSize(panel.getPreferredSize());
		panel.add(center, BorderLayout.CENTER);
		
		viewMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getID() == ActionEvent.ACTION_PERFORMED) {
					new AnalyzePage(match, account.name);
				}
			}
		});
		
		panel.add(new JSeparator(), BorderLayout.SOUTH);
		
		return panel;
	}

}
