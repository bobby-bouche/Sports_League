package data_classes;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;


public class Team {
	
	// team fields
	private static Map<Integer, Player> squad;

	private int teamID;
	private String name;
	
	
	// constructor
	public Team(int teamID, String name) {
		super();
		this.setTeamID(teamID);
		this.name     = name;
		
		squad = new HashMap<Integer, Player>();
	}


	// getters and setters
	public String getName() {
		return name;
	}
	
	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	
	
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
