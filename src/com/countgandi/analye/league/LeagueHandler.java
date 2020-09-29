package com.countgandi.analye.league;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.countgandi.analye.Globals;
import com.countgandi.analye.league.match.Match;
import com.countgandi.analye.league.match.MatchFrame;
import com.countgandi.analye.league.match.Player;
import com.countgandi.analye.league.match.TeamStats;

public class LeagueHandler {

	/**
	 * https://developer.riotgames.com/apis#match-v4/GET_getMatch
	 * 
	 * @param matchID
	 * @return
	 */
	public static Match getMatch(SummonerAccount.TempMatch m) {
		JSONObject data = new JSONObject(readDataFromLeague("/lol/match/v4/matches/" + m.gameId));
		Match match = new Match();

		match.gameId = data.getLong("gameId");
		match.queueId = data.getInt("queueId");
		match.gameType = data.getString("gameType");
		match.gameDuration = data.getLong("gameDuration");
		match.platformId = data.getString("platformId");
		match.gameCreation = data.getLong("gameCreation");
		match.seasonId = data.getInt("seasonId");
		match.gameVersion = data.getString("gameVersion");
		match.mapId = data.getInt("mapId");
		match.gameMode = data.getString("gameMode");

		JSONArray teamArray = data.getJSONArray("teams");
		System.out.println(teamArray);
		for (int i = 0; i < 2; i++) {
			JSONObject obj = teamArray.getJSONObject(i);
			TeamStats stat = new TeamStats();
			stat.towerKills = obj.getInt("towerKills");
			stat.riftHeraldKills = obj.getInt("riftHeraldKills");
			stat.inhibitorKills = obj.getInt("inhibitorKills");
			stat.dragonKills = obj.getInt("dragonKills");
			stat.baronKills = obj.getInt("baronKills");
			stat.teamId = obj.getInt("teamId");
			
			boolean blue = true;
			if (stat.teamId == Player.RED)
				blue = false;

			if (blue) {
				if (obj.getBoolean("firstBlood")) {
					
				}
				stat.firstBlood = obj.getBoolean("firstBlood");
				stat.firstBaron = obj.getBoolean("firstBaron");
				stat.firstDragon = obj.getBoolean("firstDragon");
				stat.firstInhibitor = obj.getBoolean("firstInhibitor");
				stat.firstTower = obj.getBoolean("firstTower");
				stat.firstRiftHerald = obj.getBoolean("firstRiftHerald");

				if (obj.getString("win").equals("Win")) {
					stat.win = true;
				} else {
					stat.win = false;
				}
			}

			if (obj.getInt("teamId") == Player.BLUE) { // BLUE
				match.blueTeam = stat;
			} else { // RED
				match.redTeam = stat;
			}
		}

		JSONArray players = data.getJSONArray("participants");
		JSONArray playersIndentities = data.getJSONArray("participantIdentities");
		for (int k = 0; k < players.length(); k++) {
			JSONObject p = players.getJSONObject(k);
			JSONObject pident = playersIndentities.getJSONObject(k).getJSONObject("player");
			Player player = new Player();

			player.participantId = p.getInt("participantId");
			player.champion = Globals.getChampion(p.getInt("championId"));
			player.teamId = p.getInt("teamId");
			player.spell1Id = p.getInt("spell1Id");
			player.spell2Id = p.getInt("spell2Id");

			player.profileIcon = pident.getInt("profileIcon");
			player.summonerName = pident.getString("summonerName");

			JSONObject stats = p.getJSONObject("stats");
			for (int i = 0; i < 6; i++) {
				player.items[i] = stats.getInt("item" + i);
			}
			player.totalUnitsHealed = stats.getInt("totalUnitsHealed");
			player.largestMultikill = stats.getInt("largestMultiKill");
			player.goldEarned = stats.getInt("goldEarned");
			if (stats.has("firstInhibitorKill"))
				player.firstInhibitorKill = stats.getBoolean("firstInhibitorKill");
			player.physicalDamageTaken = stats.getLong("physicalDamageTaken");
			player.totalPlayerScore = stats.getInt("totalPlayerScore");
			player.champLevel = stats.getInt("champLevel");
			player.damageDealtToObjectives = stats.getLong("damageDealtToObjectives");
			player.totalDamageTaken = stats.getLong("totalDamageTaken");
			player.neutralMinionsKilled = stats.getInt("neutralMinionsKilled");
			player.deaths = stats.getInt("deaths");
			player.magicDamageDealtToChampions = stats.getLong("magicDamageDealtToChampions");
			if (stats.has("wardsKilled"))
				player.wardsKilled = stats.getInt("wardsKilled");
			player.damageSelfMitigated = stats.getLong("damageSelfMitigated");
			player.largestCriticalStrike = stats.getInt("largestCriticalStrike");
			player.totalTimeCrowdControlDealt = stats.getInt("totalTimeCrowdControlDealt");
			if (stats.has("firstTowerKill"))
				player.firstTowerKill = stats.getBoolean("firstTowerKill");
			player.magicDamageDealt = stats.getLong("magicDamageDealt");
			player.totalScoreRank = stats.getInt("totalScoreRank");
			if (stats.has("wardsPlaced"))
				player.wardsPlaced = stats.getInt("wardsPlaced");
			player.totalDamageDealt = stats.getLong("totalDamageDealt");
			player.timeCCingOthers = stats.getLong("timeCCingOthers");
			player.magicalDamageTaken = stats.getLong("magicalDamageTaken");
			player.largestKillingSpree = stats.getInt("largestKillingSpree");
			player.totalDamageDealtToChampions = stats.getLong("totalDamageDealtToChampions");
			player.physicalDamageDealtToChampions = stats.getLong("physicalDamageDealtToChampions");
			if (stats.has("neutralMinionsKilledTeamJungle"))
				player.neutralMinionsKilledTeamJungle = stats.getInt("neutralMinionsKilledTeamJungle");
			player.totalMinionsKilled = stats.getInt("totalMinionsKilled");
			if (stats.has("firstInhibitorAssist"))
				player.firstInhibitorAssist = stats.getBoolean("firstInhibitorAssist");
			player.visionWardsBoughtInGame = stats.getInt("visionWardsBoughtInGame");
			player.objectivePlayerScore = stats.getInt("objectivePlayerScore");
			player.kills = stats.getInt("kills");
			if (stats.has("firstTowerAssist"))
				player.firstTowerAssist = stats.getBoolean("firstTowerAssist");
			player.combatPlayerScore = stats.getInt("combatPlayerScore");
			if (stats.has("inhibitorKills"))
				player.inhibitorKills = stats.getInt("inhibitorKills");
			if (stats.has("turretKills"))
				player.turretKills = stats.getInt("turretKills");
			player.trueDamageTaken = stats.getLong("trueDamageTaken");
			player.firstBloodAssist = stats.getBoolean("firstBloodAssist");
			player.assists = stats.getInt("assists");
			player.goldSpent = stats.getInt("goldSpent");
			player.damageDealtToTurrets = stats.getLong("damageDealtToTurrets");
			player.win = stats.getBoolean("win");
			player.totalHeal = stats.getLong("totalHeal");
			player.unrealKills = stats.getInt("unrealKills");
			player.visionScore = stats.getLong("visionScore");
			player.physicalDamageDealt = stats.getLong("physicalDamageDealt");
			if (stats.has("firstBloodKill"))
				player.firstBloodKill = stats.getBoolean("firstBloodKill");
			player.longestTimeSpentLiving = stats.getInt("longestTimeSpentLiving");
			player.killingSprees = stats.getInt("killingSprees");
			player.sightWardsBoughtInGame = stats.getInt("sightWardsBoughtInGame");
			player.trueDamageDealtToChampions = stats.getLong("trueDamageDealtToChampions");
			if (stats.has("neutralMinionsKilledEnemyJungle"))
				player.neutralMinionsKilledEnemyJungle = stats.getInt("neutralMinionsKilledEnemyJungle");
			player.doubleKills = stats.getInt("doubleKills");
			player.tripleKills = stats.getInt("tripleKills");
			player.quadraKills = stats.getInt("quadraKills");
			player.pentaKills = stats.getInt("pentaKills");

			match.players.add(player);
		}

		for (int i = 0; i < 10; i++) {
			if (match.players.get(i).teamId == Player.BLUE) {
				match.blue.add(match.players.get(i));
			} else {
				match.red.add(match.players.get(i));
			}
		}

		match.frames = getTimeline(m);

		return match;
	}

	public static ArrayList<MatchFrame> getTimeline(SummonerAccount.TempMatch m) {
		ArrayList<MatchFrame> frames = new ArrayList<MatchFrame>();
		JSONObject time = new JSONObject(readDataFromLeague("/lol/match/v4/timelines/by-match/" + m.gameId));
		JSONArray line = time.getJSONArray("frames");
		for (int i = 0; i < line.length(); i++) {
			MatchFrame frame = new MatchFrame();
		}

		return frames;
	}

	/**
	 * https://developer.riotgames.com/apis#match-v4/GET_getMatchlist
	 * 
	 * @param encryptedAccountId
	 * @return
	 */
	public static JSONObject getMatchHistory(int start, int end, String encryptedAccountId) {
		String data = readDataFromLeague("/lol/match/v4/matchlists/by-account/" + encryptedAccountId,
				"&endIndex=" + end + "&beginIndex=" + start);
		return new JSONObject(data);
	}

	public static SummonerAccount getSummoner(String summonerName) {
		String data = readDataFromLeague("/lol/summoner/v4/summoners/by-name/" + summonerName);
		JSONObject obj = new JSONObject(data);
		SummonerAccount acc = new SummonerAccount();

		acc.name = obj.getString("name");
		acc.accountId = obj.getString("accountId");
		acc.profileIconId = obj.getInt("profileIconId");
		acc.revisionDate = obj.getLong("revisionDate");
		acc.id = obj.getString("id");
		acc.puuid = obj.getString("puuid");
		acc.summonerLevel = obj.getLong("summonerLevel");

		return acc;
	}

	private static String readDataFromLeague(String url) {
		return readDataFromLeague(url, "");
	}

	private static String readDataFromLeague(String url, String extra) {
		String data = "";
		try {
			URL line = new URL(
					"https://" + Globals.REGION + Globals.ROUTING_VALUE + url + "?api_key=" + Globals.API_KEY + extra);
			// RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 *
			// 1000).build();
			HttpURLConnection con = (HttpURLConnection) line.openConnection();

			if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			}

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				data += inputLine;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
