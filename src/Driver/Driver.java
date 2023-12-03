package Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import data_classes.League;
import data_classes.Player;
import data_classes.Team;

public class Driver {
	
	
	// constructor
	public Driver() {
		super();
	}
	
	
	// create database connection
	void ConnectDB() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sports_league_db","root", "Ronaldo");
			
			System.out.println("database connection success..\n");
			
			List<Player> players = retrievePlayers(con);
			List<Team> teams     = retrieveTeams(con, players);
			List<League> leagues = retrieveLeagues(con, teams);
			
			con.close();
			
			// use data as needed
//			for(Player p : players) {
//				System.out.println(p.getLname());
//			}
			
//			for(Team t : teams) {
//				System.out.println(t.getName() + " " + t.getSquad());
//			}
//			
//			for(League l : leagues) {
//				System.out.println(l.getLeagueName() + l.getLeagueRoster());
//			}
			
			for(League l : leagues) {
				for(Team t : l.getLeagueRoster()) {
					System.out.println(l.getLeagueName() + " " + t.getName() + " " + t.getSquad());
				}
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
		
		Player player;
		List<Player> players = new ArrayList<>();
		
		Statement stmt = con.createStatement();
		ResultSet rs   = stmt.executeQuery("SELECT * FROM PLAYER");
		
		while(rs.next()) {
			
			int player_id   = rs.getInt("player_id");
			String f_name   = rs.getString("first_name");
			String l_name   = rs.getString("last_name");
			int age         = rs.getInt("age");
			String position = rs.getString("position");
			int team_id     = rs.getInt("team_id");
			
			player = new Player(player_id,f_name,l_name,age,position,team_id);
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
		ResultSet rs = stmt.executeQuery("SELECT * FROM LEAGUE");
		
		while(rs.next()) {
			
			int league_id = rs.getInt("league_id");
			String name = rs.getString("name");
			
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

	
	
	// main method
	public static void main(String[] args) {
		
		Driver driver;
		driver = new Driver();
		
		try {
		
			driver.ConnectDB();
			
		}catch(NullPointerException e) {
			System.out.println(e);
			
		}catch(IllegalArgumentException e) {
			System.out.println(e);
			
		}catch(Exception e) {
			System.out.println(e);
			
		}
	}

}
