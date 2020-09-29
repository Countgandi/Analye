package com.countgandi.analye.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;

import com.countgandi.analye.Globals;
import com.countgandi.analye.league.match.Match;
import com.countgandi.analye.league.match.MatchFrame;
import com.countgandi.analye.league.match.Player;

public class TimelineJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	private JSlider timeline = new JSlider();
	private Canvas canvas;
	
	public TimelineJPanel(Match match) {
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(512, 512));
		canvas.setMinimumSize(canvas.getPreferredSize());
		canvas.setMaximumSize(canvas.getPreferredSize());
		
		timeline.setMaximum(100);
		timeline.setMajorTickSpacing(10);
		timeline.setMinorTickSpacing(1);
		
		timeline.setPaintTicks(true);
		timeline.setSnapToTicks(true);
		
		JComboBox<Player> playerSelection = new JComboBox<Player>();
		JComboBox<Player> playerSelection2 = new JComboBox<Player>();
		playerSelection.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		playerSelection2.setFont(new Font(Globals.FONT, Font.PLAIN, 12));

		JPanel timelinePanel = new JPanel();
		timelinePanel.setLayout(new BorderLayout());

		JPanel pSelect = new JPanel();
		pSelect.add(playerSelection);
		pSelect.add(playerSelection2);
		timelinePanel.add(pSelect, BorderLayout.NORTH);
		
		timelinePanel.add(canvas, BorderLayout.EAST);

		JTable table = new JTable();
		table.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		JScrollPane tableParent = new JScrollPane(table);
		timelinePanel.add(tableParent, BorderLayout.CENTER);
		
		timelinePanel.add(timeline, BorderLayout.SOUTH);
		
		playerSelection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getID() == ActionEvent.ACTION_PERFORMED) {
					//table.setModel(loadTimelineValues(playerSelection, playerSelection2));
				}
			}
		});
		playerSelection2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getID() == ActionEvent.ACTION_PERFORMED) {
					//table.setModel(loadTimelineValues(playerSelection, playerSelection2));
				}
			}
		});
	}
	
	public void loadTimelineMatch(ArrayList<MatchFrame> frames) {
		
	}
	
}
