package data_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;


public class Team {
	
	// team fields
	private List<Player> squad;

	private int teamID;
	private String name;
	private int leagueID;
	
	
	// constructor
	public Team(int teamID, String name, int leagueID) {
		super();
		this.teamID = teamID;
		this.name     = name;
		this.setLeagueID(leagueID);
		
		squad = new ArrayList<Player>();
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
	
	public int getLeagueID() {
		return leagueID;
	}

	public void setLeagueID(int leagueID) {
		this.leagueID = leagueID;
	}
	
	
	// method to add player to squad
	public void addPlayer(Player player) {
		
		if(squad.contains(player)) {
			JOptionPane.showInternalMessageDialog(null, "Player already registered to this squad");
		}
		else {
			squad.add(player);
			JOptionPane.showInternalMessageDialog(null, "Player sucessfully registered");
		}
	}
	
	// method to remove player to squad
	public void removePlayer(Player player) {
		
		if(squad.contains(player)) {
			squad.remove(player);
			JOptionPane.showInternalMessageDialog(null, "Player sucessfully unregistered");
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Invalid playerID");
		}
	}

}
