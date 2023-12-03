package Driver;

public class Driver {
	
	// main method
	public static void main(String[] args) {
		
		LeagueManagementSystem system;
		system = new LeagueManagementSystem();
		
		try {
			
			system.ConnectDB();
			
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
