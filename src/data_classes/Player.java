package data_classes;

public class Player {
	
	// player fields
	private int playerID;
	private String fname;
	private String lname;
	private int age;
	private String position;
	private int teamID;
	

	// symbolic constants
	private static int DEFAULT_TEAM_ID = 0;

	
	// initializer
	{
		teamID = DEFAULT_TEAM_ID;
	}
	
	
	// constructors
	public Player() {
		super();
	}
	
	public Player(int playerID, String fname, String lname, int age, String position) {
		this(playerID,fname,lname,age,position,DEFAULT_TEAM_ID);	
	}
	
	public Player(int playerID, String fname, String lname, int age, String position, int teamID) {
		super();
		this.playerID = playerID;
		this.fname    = fname;
		this.lname    = lname;
		this.age      = age;
		this.position = position;
		this.teamID   = teamID;
	}
	
	
	// getters and setters
	public int getPlayerID() {
		return playerID;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getTeamID() {
		return teamID;
	}


}
