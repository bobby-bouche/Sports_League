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
	
	
	// method to launch management system
	// this is a test fror now
	// this method will house the bulk of operations that will be written
	void runManagementSystem() {
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
	
	
	// main method
	public static void main(String[] args) {
		
		Driver driver;
		driver = new Driver();
			
		try {
		
	     	driver.loadDatabase();
			driver.runManagementSystem();

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
