package data_classes;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;


public class Team {
	
	// team fields
	private static Map<Integer, Player> squad;

	private int teamID;
	private String name;
//	private String location;
//	private int gamesPlayed;
//	private int points;
//	private int goalsFor;
//	private int goalsAgainst;
	
	
	// symbolic constants
//	private static int DEFAULT_GAMES_PLAYED  = 0;
//	private static int DEFAULT_POINTS 		 = 0;
//	private static int DEFAULT_GOALSFOR 	 = 0;
//	private static int DEFAULT_GOALSAGAINST  = 0;
	
	
	// initializer
	{
//		gamesPlayed  = DEFAULT_GAMES_PLAYED;
//		points       = DEFAULT_POINTS;
//		goalsFor     = DEFAULT_GOALSFOR;
//		goalsAgainst = DEFAULT_GOALSAGAINST;
	}
	
	
	// constructors
	public Team() {
		super();
		squad = new HashMap<Integer, Player>();
	}
	
	public Team(int teamID, String name) {
		super();
		this.setTeamID(teamID);
		this.name     = name;
		
		squad = new HashMap<Integer, Player>();
	}


	public String getName() {
		return name;
	}
	
	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	
//	public String getLocation() {
//	return location;
//  }

//	public int getGamesPlayed() {
//		return gamesPlayed;
//	}
//
//	public void setGamesPlayed(int gamesPlayed) {
//		this.gamesPlayed = gamesPlayed;
//	}
//
//	public int getPoints() {
//		return points;
//	}
//
//	public void setPoints(int points) {
//		this.points = points;
//	}
//
//	public int getGoalsFor() {
//		return goalsFor;
//	}
//
//	public void setGoalsFor(int goalsFor) {
//		this.goalsFor = goalsFor;
//	}
//
//	public int getGoalsAgainst() {
//		return goalsAgainst;
//	}
//
//	public void setGoalsAgainst(int goalsAgainst) {
//		this.goalsAgainst = goalsAgainst;
//	}
	
	
	// method to add player to squad
	public void addPlayer(Player player) {
		
		if(squad.containsKey(player.getPlayerID())) {
			JOptionPane.showInternalMessageDialog(null, "Player already registered to this squad");
		}
		else {
			squad.put(player.getPlayerID(), player);
			JOptionPane.showInternalMessageDialog(null, "Player sucessfully registered");
		}
	}
	
	// method to remove player to squad
	public void removePlayer(Player player) {
		
		if(squad.containsKey(player.getPlayerID())) {
			squad.remove(player.getPlayerID());
			JOptionPane.showInternalMessageDialog(null, "Player sucessfully unregistered");
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Invalid playerID");
		}
	}
	

}
