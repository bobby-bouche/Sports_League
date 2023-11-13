package data_classes;


import java.util.Map;


public class Team {
	
	// team fields
	private Map<String, Player> squad;

	private String name;
	private String location;
	private int gamesPlayed;
	private int points;
	private int goalsFor;
	private int goalsAgainst;
	
	
	// symbolic constants
	private static int DEFAULT_GAMES_PLAYED = 0;
	private static int DEFAULT_POINTS 		= 0;
	private static int DEFAULT_GOALSFOR 	= 0;
	private static int DEFAULT_GOALSAGAINST = 0;
	
	
	// initializer
	{
		gamesPlayed  = DEFAULT_GAMES_PLAYED;
		points       = DEFAULT_POINTS;
		goalsFor     = DEFAULT_GOALSFOR;
		goalsAgainst = DEFAULT_GOALSAGAINST;
	}
	
	
	// constructors
	public Team() {
		super();
	}
	
	public Team() {
		
	}
	

}
