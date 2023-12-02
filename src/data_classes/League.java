package data_classes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class League {
	
	// league fields
	private int league_id;
	private String name;
	
	private static ArrayList<Team> leagueRoster;
	
	
	// connstructor
	public League(int leagueID, String name) {
		super();
		this.league_id = leagueID;
		this.name = name;
		leagueRoster = new ArrayList<>();
	}


	// getters
	public ArrayList<Team> getLeagueRoster() {
		return leagueRoster;
	}
	
	public int getLeague_id() {
		return league_id;
	}
	
	public String getLeagueName() {
		return name;
	}
	
	
	//method to add team to league
	public void addTeam(Team team) {
		
		if(leagueRoster.contains(team)) {
			JOptionPane.showInternalMessageDialog(null, "Error, Team already registered");
		}
		else {
			leagueRoster.add(team);
			JOptionPane.showInternalMessageDialog(null, "Team successfully registered to league");
		}
	}
	
	// method to remove team from league
	public void removeTeam(Team team) {
		
		if(leagueRoster.contains(team)) {
			leagueRoster.remove(team);
			JOptionPane.showInternalMessageDialog(null, "Player sucessfully unregistered");
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Invalid playerID");
		}
	}
	

}
