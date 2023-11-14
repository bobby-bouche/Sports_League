package data_classes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class League {
	
	// league fields
	private static ArrayList<Team> leagueRoster;
	
	
	// connstructor
	public League() {
		super();
		leagueRoster = new ArrayList<>();
	}


	// getter
	public ArrayList<Team> getLeagueRoster() {
		return leagueRoster;
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
