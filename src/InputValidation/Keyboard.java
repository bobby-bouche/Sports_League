package InputValidation;

import java.util.Scanner;

public class Keyboard {
	
	// Keyboard fields
	private static Scanner input;
	
	
	// constructor
	public Keyboard() {
		
		super();
		input = new Scanner(System.in);
	}
	
	
	// method to validate integer input from user
	public int readInteger(String promptMsg, String errorMsg, int low, int high) {
		
		int num = 0;
		boolean valid = false;
		String strInput;
		
		while(valid == false) {
			
			System.out.println(promptMsg);
			//input.nextLine();
			strInput = input.nextLine();
			
			try {
				num = Integer.parseInt(strInput);
				if(num >= low && num <= high) {
					valid = true;
				}
				else {
					System.out.println(errorMsg);
				}
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}
	
	
	// method to validate String input from user
	public String readString(String promptMsg, String errorMsg) {
		
		String strInput = null;
		boolean valid   = false;
		
		while(valid == false) {
			
			System.out.println(promptMsg);
			strInput = input.nextLine();
			
			try {
				if(!(strInput.equals(null)) && strInput.matches("^[a-z A-Z]*$")) {
					valid = true;
				}
				else {
					System.out.println(errorMsg);
				}
			}
			catch(Exception e) {
				System.out.println(errorMsg);
			}
		}
		return strInput;
	}


}
