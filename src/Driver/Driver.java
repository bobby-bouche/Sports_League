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
	// this is just a test for now to check loaded data
	// this method will run the methods and procedures needed for a text based system
	void runTextBasedSystem() {
		for(League l : leagues) {
			for(Team t : teams) {
				if(l.getLeague_id() == t.getLeagueID()) {
					for(Player p : players) {
						if(p.getTeamID() == t.getTeamID()) {
							System.out.println(p.getLname() + " " + t.getName() + " " + l.getLeagueName());
						}
					}
				}
			}
		}	
	}
	
	
	// method to launch GUI management system
	void runGUIBasedSystem() {
		
		// this method will run the frames needed for a GUI based system
		
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
