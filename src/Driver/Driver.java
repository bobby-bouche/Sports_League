package Driver;

import java.util.List;

import data_classes.Player;

public class Driver {
	
	// Driver fields
	private static DataBaseManager dbManager;
	
	
	// constructor
	public Driver() {
		super();
	}
	
	
	// main method
	public static void main(String[] args) {
		
		Driver driver;
		driver    = new Driver();
		dbManager = new DataBaseManager();
	
		try {
			
			dbManager.LoadDataBase();
			driver.displayPlayers(dbManager.getPlayers());
			
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
	
	
	void displayPlayers(List<Player> players) {
		for(Player p : players) {
			System.out.println(p.getLname());
		}
	}
	
}
