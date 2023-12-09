package Driver;

import java.util.List;
import InputValidation.Keyboard;
import data_classes.League;
import data_classes.Player;
import data_classes.Team;

public class Driver {
	
	// Driver fields
	private static DatabaseManager dbManager;
	private Keyboard kb;
	
	private static List<League> leagues;
	private static List<Team>   teams;
	private static List<Player> players;
	
	
	// constructor
	public Driver() {
		
		dbManager = new DatabaseManager();
		kb        = new Keyboard();
	}
	

	// method to load data from database and populate static class lists
	void loadDatabase(){
	
		dbManager.retrieveData();
		leagues = dbManager.getLeagues();
		teams   = dbManager.getTeams();
		players = dbManager.getPlayers();
	}
	
	
	
	/*
	 *  The following section contains the methods for the text based system
	 */
	// method to launch text management system
	public void runTextBasedSystem() {
		
		int choice;
		boolean proceed  = true;
		
		String promptMsg = "Make a selection:\n";
		String errorMsg  = "Invalid entry, enter an integer value in the range (1-3)\n";
		
		while(proceed) {
			
			System.out.println("------Sports League Management System------\n"
							   + "--------------Main Menu--------------\n"
							   + "1. Searches\n"
							   + "2. Registration\n"
							   + "3. Exit\n");
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 3);
			
			switch(choice) {
			
				case 1:
					runSearchMenu();
					break;
					
				case 2:
					runRegistrationMenu();
					break;
				
				case 3:
					System.out.println("Bye for now!");
					proceed = false;
					break;
					
				default:
					System.out.println(errorMsg +": "+ choice);
			}
		}
	}
	
	
	void runSearchMenu() {
		
		int choice;
		boolean proceed = true;
		String strInput;
		
		String promptMsg = "Make a selection:\n";
		String errorMsg  = "Invalid entry, enter an integer value in the range (1-4)\n";
		
		while(proceed) {
			
			System.out.println(
		            " -------------Search Menu-------------\n"
		            + "1. Players\n"
		            + "2. Teams\n"
		            + "3. Leagues\n"
		            + "4. Back");
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 4);
			
			switch(choice) {
			
				case 1:
					String prompt = "Enter player name: ";
					String error  = "player does not exists in database";
					strInput = kb.readString(prompt, error);	
				
					for(Player p : players) {
						if(strInput.equalsIgnoreCase(p.getLname())) {
							System.out.println(p.toString() + "\n");
							proceed = false;
							break;
						}
					}
					break;
					
				case 2:
					String teamPromptMsg = "Enter team name: ";
					String teamErrorMsg  = "team does not exist in database";
					strInput = kb.readString(teamPromptMsg, teamErrorMsg);
					
					for(Team t : teams) {
						if(strInput.equalsIgnoreCase(t.getName())) {
							System.out.println(t.toString() + "\n");
							proceed = false;
							break;
						}
					}
					break;
						
				case 3:
					String leaguePromptMsg = "Enter league name: ";
					String leagueErrorMsg  = "league does not exist in database";
					strInput = kb.readString(leaguePromptMsg, leagueErrorMsg);
					for(League l : leagues) {
						if(strInput.equalsIgnoreCase(l.getLeagueName())) {
							System.out.println(l.getLeagueRoster());
							proceed = false;
							break;
						}
					}
					break;
					
				case 4:
					proceed = false;
					break;
					
				default:
					System.out.println(errorMsg);
					break;	
			}
		}
	}
	
	
	void runRegistrationMenu() {
		
		int choice;
		boolean proceed = true;
		String strInput;
		
		String promptMsg = "Make a selection:\n";
		String errorMsg  = "Invalid entry, enter an integer value in the range (1-7)\n";
		
		while(proceed) {
			
			System.out.println(
		            " ------------Registration Menu------------\n"
		            + "1. Register Player\n"
		            + "2. Register Team\n"
		            + "3. Register League\n"
		            + "4. Remove Player\n"
		            + "5. Remove Team\n"
		            + "6. Remove League\n"
		            + "7. Back\n");
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 7);
			
			switch(choice) {
			
				case 1:
					dbManager.registerNewPlayer();
			
				case 2:
					dbManager.registerNewTeam();
					
				case 3:
					dbManager.registerNewLeague();
					
				case 4:
					
					
				case 5:
					
					
				case 6:
					
					
				case 7:
					proceed = false;
					break;
					
					
				default:
					
				
			}
		}	
	}
	
	
	/*
	 *  The following section contains the methods for the GUI based system
	 */
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
	     	//System.out.println(players);
	
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
