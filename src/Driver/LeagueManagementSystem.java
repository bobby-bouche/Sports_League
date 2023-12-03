package Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data_classes.League;
import data_classes.Player;
import data_classes.Team;

public class LeagueManagementSystem {
	
	// create database connection
	void ConnectDB() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sports_league_db","root", "Ronaldo");
			
			System.out.println("database connection success..\n");
			
			List<Player> players = retrievePlayers(con);
			List<Team>   teams   = retrieveTeams(con, players);
			List<League> leagues = retrieveLeagues(con, teams);
			
			con.close();
			
			// use data as needed
			
			for(Player p : players) {
				System.out.println(p.getLname());
			}

		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	// method to retrieve player data from database
	private static List<Player> retrievePlayers(Connection con) throws SQLException {
		
		List<Player> players = new ArrayList<>();
		
		Statement stmt = con.createStatement();
		ResultSet rs   = stmt.executeQuery("SELECT * FROM PLAYER");
		
		while(rs.next()) {
			
			Player player = new Player();
			
			player.setPlayerID(rs.getInt("player_id"));
			player.setFname(rs.getString("first_name"));
			player.setLname(rs.getString("last_name"));
			player.setAge(rs.getInt("age"));
			player.setPosition(rs.getString("position"));
			player.setTeamID(rs.getInt("team_id"));
			
			players.add(player);
		}
		return players;
	}
	
	// method to retrieve team data from database
	private static List<Team> retrieveTeams(Connection con, List<Player> players) throws SQLException{
		
		Team team;
		List<Team> teams = new ArrayList<>();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM TEAM");
		
		while(rs.next()) {
			
			int team_id   = rs.getInt("team_id");
			String name   = rs.getString("name");
			int league_id = rs.getInt("league_id");
			
			team = new Team(team_id,name,league_id);
			
			for(Player p : players) {
				if(p.getTeamID() == team.getTeamID()) {
					team.addPlayer(p);
				}
			}
			teams.add(team);
		}
		return teams;	
	}
	
	// method to retrieve league data from database
	private static List<League> retrieveLeagues(Connection con, List<Team> teams) throws SQLException {
		
		League league;
		List<League> leagues = new ArrayList<>();
		
		Statement stmt = con.createStatement();
		ResultSet rs   = stmt.executeQuery("SELECT * FROM LEAGUE");
		
		while(rs.next()) {
			
			int league_id = rs.getInt("league_id");
			String name   = rs.getString("name");
			
			league = new League(league_id,name);
			
			for(Team t : teams) {
				if(t.getLeagueID() == league.getLeague_id()) {
					league.addTeam(t);
				}
			}
			leagues.add(league);
		}
		return leagues;
	}
	
}
