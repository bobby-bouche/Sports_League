package data_classes;

public class Player {
	
	// player fields
	private int playerID;
	private String fname;
	private String lname;
	private int age;
	private String position;
	private int teamID;
	
	// player attribute fields
	private int physical;
	private int mentality;
	private int tackling;
	private int passing;
	private int dribbling;
	private int skill;
	private int shooting;
	

	// symbolic constants
	private static int DEFAULT_TEAM_ID = 0;
	
	private static int DEFAULT_PLAYER_PHY = 0;
	private static int DEFAULT_PLAYER_MEN = 0;
	private static int DEFAULT_PLAYER_TKL = 0;
	private static int DEFAULT_PLAYER_PAS = 0;
	private static int DEFAULT_PLAYER_DBL = 0;
	private static int DEFAULT_PLAYER_SKL = 0;
	private static int DEFAULT_PLAYER_SHT = 0;
	
	
	// initializer
	{
		teamID = DEFAULT_TEAM_ID;
		
		physical   = DEFAULT_PLAYER_PHY;
		mentality  = DEFAULT_PLAYER_MEN;
		tackling   = DEFAULT_PLAYER_TKL;
		passing    = DEFAULT_PLAYER_PAS;
		dribbling  = DEFAULT_PLAYER_DBL;
		skill      = DEFAULT_PLAYER_SKL;
		shooting   = DEFAULT_PLAYER_SHT;
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

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public int getPhysical() {
		return physical;
	}

	public void setPhysical(int physical) {
		this.physical = physical;
	}

	public int getMentality() {
		return mentality;
	}

	public void setMentality(int mentality) {
		this.mentality = mentality;
	}

	public int getTackling() {
		return tackling;
	}

	public void setTackling(int tackling) {
		this.tackling = tackling;
	}

	public int getPassing() {
		return passing;
	}

	public void setPassing(int passing) {
		this.passing = passing;
	}

	public int getDribbling() {
		return dribbling;
	}

	public void setDribbling(int dribbling) {
		this.dribbling = dribbling;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getShooting() {
		return shooting;
	}

	public void setShooting(int shooting) {
		this.shooting = shooting;
	}

}
