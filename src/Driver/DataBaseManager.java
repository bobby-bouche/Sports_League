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

public class DatabaseManager {
	
	// LeagueManagementSystem fields
	private List<Player> players;
	private List<Team>   teams;
	private List<League> leagues;
	
	
	// constructor
	public DatabaseManager() {
		super();
	}
	
	
	// getters
	public List<Player> getPlayers() {
		return players;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public List<League> getLeagues() {
		return leagues;
	}
	
	
	// method to create database connection, retrieve data and populate static data lists
	void retrieveData() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sports_league_db","root", "Ronaldo");
			System.out.println("database connection success..\n");
			
			players = retrievePlayers(connection);
			teams   = retrieveTeams(connection, players);
			leagues = retrieveLeagues(connection, teams);
			
			connection.close();
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
		
		List<Team> teams = new ArrayList<>();
		Statement stmt   = con.createStatement();
		ResultSet rs     = stmt.executeQuery("SELECT * FROM TEAM");
		
		while(rs.next()) {
			
			Team team = new Team();
			team.setTeamID(rs.getInt("team_id"));
			team.setName(rs.getString("name"));
			team.setLeagueID(rs.getInt("league_id"));
	
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
		
		List<League> leagues = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs   = stmt.executeQuery("SELECT * FROM LEAGUE");
		
		while(rs.next()) {
			
			League league = new League();
			league.setLeague_id(rs.getInt("league_id"));
			league.setLeagueName(rs.getString("name"));
			
			for(Team t : teams) {
				if(t.getLeagueID() == league.getLeague_id()) {
					league.addTeam(t);
				}
			}
			leagues.add(league);
		}
		return leagues;
	}
	
	
	// method to add player to database
	void registerPlayer() {
		
		Player player = new Player();
		
	}
	
	
	void registerTeam() {
		// TODO
	}
	
	
	void registerLeague() {
		//TODO
	}
	
	
	void removePlayer(int playerID, String name) {
		//TODO
	}
	
	
	void removeTeam(int teamID) {
		//TODO
	}
	
	
	void removeLeague(int leagueID) {
		//TODO
	}
	
}
