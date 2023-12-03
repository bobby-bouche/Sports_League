package Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import data_classes.Player;

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
	
	private static List<Player> retrievePlayers(Connection con){
		
		Player player;
		List<Player> players = new ArrayList<>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER");
			
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
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
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
