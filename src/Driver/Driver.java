package Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
	
	
	// create database connection
	Connection ConnectDB() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sports_league_db","root", "Ronaldo");
			
			System.out.println("database connection success..\n");
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}

	
	
	// main method
		public static void main(String[] args) {
			
			Driver driver;
			driver = new Driver();
			
			try {
			
				driver.ConnectDB();
				
			}catch(NullPointerException e) {
				System.out.println(e);
				
			}catch(IllegalArgumentException e) {
				System.out.println(e);
				
			}catch(Exception e) {
				System.out.println(e);
				
			}
		}

}
