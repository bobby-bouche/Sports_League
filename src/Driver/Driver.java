package Driver;

import java.util.List;

import data_classes.League;
import data_classes.Player;
import data_classes.Team;

public class Driver {
	
	// Driver fields
	private static DatabaseManager dbManager;
	private static List<League> leagues;
	private static List<Team>   teams;
	private static List<Player> players;
	
	
	// constructor
	public Driver() {
		
		dbManager = new DatabaseManager();
	}
	

	// method to load data from database and populate static class lists
	void loadDatabase() {
		
		dbManager.retrieveData();
		leagues = dbManager.getLeagues();
		teams   = dbManager.getTeams();
		players = dbManager.getPlayers();
	}
	
	
	// method to launch text management system
	void runTextBasedSystem() {
		
		int choice;
		boolean proceed;
		
		while(proceed) {
			
			System.out.println("------Sports League Management System------\n"
							   + "Main Menu\n------------------\n"
							   + "1. players"
							   + "2. Teams"
							   + "3. Leagues"
							   + "4. Exit");
			
			choice = 
		}
	}
	
	
	// method to launch GUI management system
	void runGUIBasedSystem() {
		
		// TODO this method will run the frames needed for a GUI based system
		
	}
	
	
	// main method
	public static void main(String[] args) {
		
		Driver driver;
		driver = new Driver();
			
		try {
		
	     	driver.loadDatabase();
			driver.runTextBasedSystem();

		}
		catch(NullPointerException e) {
			System.out.println(e);	
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);	
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
}
