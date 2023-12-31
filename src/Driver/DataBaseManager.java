package Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import InputValidation.Keyboard;
import data_classes.League;
import data_classes.Player;
import data_classes.Team;

public class DatabaseManager {
	
	// LeagueManagementSystem fields
	private static List<Player> players;
	private static List<Team>   teams;
	private static List<League> leagues;
	
	private Keyboard kb;
	Connection connection;
	
	// constructor
	public DatabaseManager() {
		super();
		
		kb = new Keyboard();
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
		
			connection = connectDB();
	
			players = retrievePlayers(connection);
			teams   = retrieveTeams(connection, players);
			leagues = retrieveLeagues(connection, teams);
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	// method to continuously refresh data lists every 5 seconds
	public void CDCListener() {
		
		while(true) {
			
			try {
				retrieveData();
				Thread.sleep(5000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// method to create database connection
	Connection connectDB() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sports_league_db","root", "Ronaldo");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	// method to retrieve player data from database
	public static List<Player> retrievePlayers(Connection con) throws SQLException {
		
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
		rs.close();
		stmt.close();
		
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
		rs.close();
		stmt.close();
		
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
		rs.close();
		stmt.close();
		
		return leagues;
	}
	
	
	// method to add new player to database
	public void registerNewPlayer() {
		
		Player player = new Player();
		
		String fname    = kb.readString("enter first name: \n", "Invalid name, try again: \n");
		String lname    = kb.readString("enter last name: \n", "Invalid name, try again: \n");
		int age         = kb.readInteger("enter age: \n", "Invalid age, try again: \n");
		String position = kb.readString("enter position: \n", "Invalid position, try again: \n");
		int teamID      = kb.readInteger("enter teamID: \n", "Invalid teamID, try again: \n");
		
		player.setFname(fname);
		player.setLname(lname);
		player.setAge(age);
		player.setPosition(position);
		player.setTeamID(teamID);
		
		try {
			
			connection = connectDB();
			PreparedStatement ps1 = connection.prepareStatement("INSERT INTO PLAYER (first_name, last_name, age, position, team_id) VALUES(?,?,?,?,?)");
			ps1.setString(1, fname.toUpperCase());
			ps1.setString(2, lname.toUpperCase());
			ps1.setInt(3, age);
			ps1.setString(4, position.toUpperCase());
			ps1.setInt(5, teamID);
			
			ps1.execute();
			ps1.close();
			System.out.println("player " + player.getLname() + " is now registered");
			connection.close();
	
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}
	
	public void updatePlayer(Player player) {
		try {
			
			connection = connectDB();
			PreparedStatement ps = connection.prepareStatement("UPDATE PLAYER SET first_name = ?, last_name = ?, age = ?, position = ?, team_id = ? WHERE player_id = ?");
			
			ps.setString(1, player.getFname());
			ps.setString(2, player.getLname());
			ps.setInt(3, player.getAge());
			ps.setString(4, player.getPosition());
			ps.setInt(5, player.getTeamID());
			ps.setInt(6, player.getPlayerID());
			
			ps.executeUpdate();
			ps.close();
			connection.close();
			
			System.out.println("Player information updated successfully.");
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
	}
	
	public void removePlayer(Player player) {
		
		try {
			
			connection = connectDB();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM PLAYER WHERE player_id = ?");
			
			ps.setInt(1, player.getPlayerID());
			
			ps.executeUpdate();
			ps.close();
			connection.close();
			
			System.out.println("Player deleted successfully.");
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}
	
	
	public void registerNewTeam() {
		
		Team team = new Team();
		
		String name   = kb.readString("enter team name: \n", "Invalid name, try again: \n");
		int league_id = kb.readInteger("enter leagueID: \n", "Invalid leagueID, try again: \n");
		
		team.setName(name);
		team.setLeagueID(league_id);
		
		try {
			
			connection = connectDB();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO TEAM (name, league_id) VALUES (?,?)");
			ps.setString(1, name.toUpperCase());
			ps.setInt(2, league_id);
			ps.execute();
			ps.close();
			
			System.out.println("Team has been registerd");
			connection.close();		
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
	}
	
	
	public void registerNewLeague() {
		
		League league = new League();
		
		String name = kb.readString("enter league name: ", "Invalid name");
		
		league.setLeagueName(name);
		
		try {
			
			connection = connectDB();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO LEAGUE (name) VALUES (?)");
			ps.setString(1, name);
			ps.execute();
			ps.close();
			
			System.out.println("league registered");
			connection.close();
				
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
	}

		
//	public void updateTeam(Team team) {
//		//TODO
//	}
//	
//	public void updateLeague(League league) {
//		//TODO
//	}
//	
//	public  void removeTeam(int teamID) {
//		//TODO
//	}
//	
//	public void removeLeague(int leagueID) {
//		//TODO
//	}
	
}
