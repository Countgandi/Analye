package com.countgandi.analye.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.countgandi.analye.Globals;
import com.countgandi.analye.league.LeagueHandler;
import com.countgandi.analye.league.SummonerAccount;
import com.countgandi.analye.league.match.Match;
import com.countgandi.analye.league.match.NameAnnotation;
import com.countgandi.analye.league.match.Player;
import com.countgandi.analye.league.match.TeamStats;

public class AnalyzePage extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();

	private JTabbedPane tabbedPane = new JTabbedPane();

	private Match match;
	private Player mainPlayer;

	public AnalyzePage(SummonerAccount.TempMatch match, String playerName) {
		this.match = LeagueHandler.getMatch(match);
		for (int i = 0; i < this.match.players.size(); i++) {
			if (playerName.equals(this.match.players.get(i).summonerName)) {
				mainPlayer = this.match.players.get(i);
				break;
			}
		}

		this.setTitle("Analysis of " + match.gameId);

		this.setPreferredSize(new Dimension(Globals.WIDTH, Globals.HEIGHT));
		this.setMaximumSize(this.getPreferredSize());
		this.setMinimumSize(this.getPreferredSize());

		this.setLocationRelativeTo(null);

		mainComponent();

		this.add(panel);
		this.setResizable(false);
		this.setVisible(true);

	}

	public void mainComponent() {

		tabbedPane.addTab("Team Stats", teamStatsPanel());

		tabbedPane.addTab("Timeline", new TimelineJPanel(match));

		tabbedPane.addTab("Player Stats", playerPanel());

		tabbedPane.setFont(new Font(Globals.FONT, Font.PLAIN, 12));

		panel.setLayout(new BorderLayout());
		panel.add(tabbedPane, BorderLayout.CENTER);
		// panel.add(new JLabel(Globals.getImage("/img/map/map11.png", 512)),
		// BorderLayout.EAST);
	}

	public JPanel playerPanel() {
		JComboBox<Player> playerSelection = new JComboBox<Player>();
		JComboBox<Player> playerSelection2 = new JComboBox<Player>();
		playerSelection.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		playerSelection2.setFont(new Font(Globals.FONT, Font.PLAIN, 12));

		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BorderLayout());

		for (Player p : match.players) {
			playerSelection.addItem(p);
		}
		for (Player p : match.players) {
			playerSelection2.addItem(p);
		}
		JPanel pSelect = new JPanel();
		pSelect.add(playerSelection);
		pSelect.add(playerSelection2);
		playerPanel.add(pSelect, BorderLayout.NORTH);

		JTable table = new JTable(loadPlayerValues(playerSelection, playerSelection2));
		table.setFont(new Font(Globals.FONT, Font.PLAIN, 12));
		JScrollPane tableParent = new JScrollPane(table);
		playerPanel.add(tableParent, BorderLayout.CENTER);

		playerSelection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getID() == ActionEvent.ACTION_PERFORMED) {
					table.setModel(loadPlayerValues(playerSelection, playerSelection2));
				}
			}
		});
		playerSelection2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getID() == ActionEvent.ACTION_PERFORMED) {
					table.setModel(loadPlayerValues(playerSelection, playerSelection2));
				}
			}
		});

		return playerPanel;
	}

	public JPanel teamStatsPanel() {
		JPanel teamStatsPanel = new JPanel();
		teamStatsPanel.setLayout(new BorderLayout());

		TeamStats blue = match.blueTeam;
		TeamStats red = match.redTeam;
		TeamStats[] order = new TeamStats[2];
		if (mainPlayer.teamId == Player.BLUE) {
			order[0] = blue;
			order[1] = red;
		} else {
			order[0] = red;
			order[1] = blue;
		}

		JPanel bluePanel = new JPanel();
		bluePanel.setBorder(BorderFactory.createTitledBorder("Blue Team"));
		bluePanel.setLayout(new GridLayout(1, 2));
		JPanel redPanel = new JPanel();
		redPanel.setBorder(BorderFactory.createTitledBorder("Red Team"));
		redPanel.setLayout(new GridLayout(1, 2));

		JPanel bluep = new JPanel();
		bluep.setLayout(new BoxLayout(bluep, BoxLayout.Y_AXIS));
		JPanel redp = new JPanel();
		redp.setLayout(new BoxLayout(redp, BoxLayout.Y_AXIS));

		redPanel.add(redp);
		redPanel.add(getSidePanelTeamStat(red));
		bluePanel.add(bluep);
		bluePanel.add(getSidePanelTeamStat(blue));

		String alighnmentb = "West", alighnmentr = "East";
		if (mainPlayer.teamId == Player.RED) {
			alighnmentb = "East";
			alighnmentr = "West";
		}

		for (int i = 0; i < 5; i++) {
			JPanel p = getTeamStatPlayerPanel(match.blue.get(i));
			p.setAlignmentX(Component.LEFT_ALIGNMENT);
			bluep.add(p);
			JPanel p2 = getTeamStatPlayerPanel(match.red.get(i));
			p2.setAlignmentX(Component.LEFT_ALIGNMENT);
			redp.add(p2);
		}

		JPanel centerPanel = new JPanel(new GridLayout(1, 2));

		JLabel win = new JLabel("Victory");
		JLabel fail = new JLabel("Defeat");
		win.setFont(new Font(Globals.FONT, Font.PLAIN, 24));
		fail.setFont(new Font(Globals.FONT, Font.PLAIN, 24));
		if (alighnmentb.equals("West")) {
			centerPanel.add(bluePanel, alighnmentr);
			centerPanel.add(redPanel, alighnmentb);
		} else {
			centerPanel.add(redPanel);
			centerPanel.add(bluePanel);
		}

		teamStatsPanel.add(centerPanel);

		return teamStatsPanel;
	}

	private JPanel getSidePanelTeamStat(TeamStats stat) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel win = new JLabel("Victory");
		JLabel fail = new JLabel("Defeat");
		win.setFont(new Font(Globals.FONT, Font.PLAIN, 48));
		fail.setFont(new Font(Globals.FONT, Font.PLAIN, 48));
		Font def = new Font(Globals.FONT, Font.PLAIN, 24);

		if (stat.win == true) {
			panel.add(win);
		} else {
			panel.add(fail);
		}
		try {
			for (Field field : stat.getClass().getDeclaredFields()) {
				if (field.isAnnotationPresent(NameAnnotation.class)) {
					Object obj = field.get(stat);
					
					if (obj instanceof Boolean) {
						JCheckBox c = new JCheckBox(field.getAnnotation(NameAnnotation.class).name(), (boolean)obj);
						c.setFont(def);
						panel.add(c);
					} else {
						JLabel l = new JLabel(field.getAnnotation(NameAnnotation.class).name() + ": " + obj);
						l.setFont(def);
						panel.add(l);
					}

					if (field.getAnnotation(NameAnnotation.class).space()) {
						JSeparator j = new JSeparator(JSeparator.HORIZONTAL);
						j.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
						panel.add(j);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Component cp : panel.getComponents()) {
			cp.setEnabled(false);
		}

		return panel;
	}

	private JPanel getTeamStatPlayerPanel(Player player) {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());

		JLabel icon = new JLabel(player.champion.img);
		JLabel name = new JLabel(
				player.summonerName + "    " + player.kills + "/" + player.deaths + "/" + player.assists);
		name.setFont(new Font(Globals.FONT, Font.PLAIN, 18));
		p.add(icon);
		p.add(name);

		return p;
	}

	public DefaultTableModel loadPlayerValues(JComboBox<Player> playerSelection, JComboBox<Player> playerSelection2) {

		Object[][] objects = new Object[80][5];

		int i = 0;
		Player p1 = (Player) playerSelection.getSelectedItem();
		Player p2 = (Player) playerSelection2.getSelectedItem();
		try {
			for (Field field : (p1).getClass().getDeclaredFields()) {
				if (field.isAnnotationPresent(NameAnnotation.class)) {
					objects[i][0] = field.getAnnotation(NameAnnotation.class).name();
					objects[i][1] = field.get(p1);
					if (field.getAnnotation(NameAnnotation.class).space()) {
						i++;
					}
					i++;
				}
			}
			i = 0;
			for (Field field : (p2).getClass().getDeclaredFields()) {
				if (field.isAnnotationPresent(NameAnnotation.class)) {
					objects[i][3] = field.getAnnotation(NameAnnotation.class).name();
					objects[i][4] = field.get(p2);
					if (field.getAnnotation(NameAnnotation.class).space()) {
						i++;
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DefaultTableModel model = new DefaultTableModel(objects, new String[] { p1.summonerName + " Name",
				p1.summonerName + " Value", "", p2.summonerName + " Name", p2.summonerName + " Value" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return model;
	}

}
