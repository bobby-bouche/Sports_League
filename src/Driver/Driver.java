package Driver;

import InputValidation.Keyboard;
import data_classes.League;
import data_classes.Player;
import data_classes.Team;

public class Driver {
	
	// Driver fields
	private static DatabaseManager dbManager;
	private static Keyboard kb;
	
	
	// constructor
	public Driver() {
		
		dbManager = new DatabaseManager();
		kb        = new Keyboard();
	}
	

	// method to launch management system
	public void runSystem() {
		
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
				
					for(Player p : dbManager.getPlayers()) {
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
					
					for(Team t : dbManager.getTeams()) {
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
					for(League l : dbManager.getLeagues()) {
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
	
	
	// method to run registration menu
	void runRegistrationMenu() {
		
		int choice;
		boolean proceed = true;
		
		String promptMsg = "Make a selection:\n";
		String errorMsg  = "Invalid entry, enter an integer value in the range (1-7)\n";
		
		while(proceed) {
			
			System.out.println(
		            " ------------Registration Menu------------\n"
		            + "1. Register Player\n"
		            + "2. Register Team\n"
		            + "3. Register League\n"
		            + "4. Transfer Player\n"
		            + "5. Remove Player\n"
		            + "6. Back\n");
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 7);
			
			switch(choice) {
			
				case 1:
					dbManager.registerNewPlayer();
					break;
			
				case 2:
					dbManager.registerNewTeam();
					break;
					
				case 3:
					dbManager.registerNewLeague();
					break;
					
				case 4:
					int playerID;	
					String prompt = "enter playerID: ";
					String error  = "Invalid playerID";
					
					playerID = kb.readInteger(prompt, error);
					for(Player p : dbManager.getPlayers()) {
						if(playerID == p.getPlayerID()) {
							
							String prompt2 = "enter new teamID: ";
							String error2  = "Invalid teamID";
							
							int updatedTeamID = kb.readInteger(prompt2, error2);
							p.setTeamID(updatedTeamID);
							dbManager.updatePlayer(p);
							System.out.println("Munya");
							break;
						}
					}
					break;
					
				case 5:
					
					int deletePlayerID;
					String deletePrompt = "enter playerID: ";
					String deleteError  = "Invalid playerID";
					
					deletePlayerID = kb.readInteger(deletePrompt, deleteError);
					for(Player p : dbManager.getPlayers()) {
						if(deletePlayerID == p.getPlayerID()) {
				
							dbManager.removePlayer(p);
							System.out.println("Munya");
						}
					}
					break;
					
				case 6:
					proceed = false;
					break;
					
				default:
					System.out.println("Invalid entry");
			}
		}	
	}

	
	// main method
	public static void main(String[] args) {
		
		Driver driver;
		driver = new Driver();
			
		try {

	     	dbManager.retrieveData();
	     	
	     	// runs a seperate Thread parallel to main thread to continuously sync program and DB data.
			Thread listenerThread = new Thread(() -> dbManager.CDCListener());
			listenerThread.start();
			
			driver.runSystem();

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
