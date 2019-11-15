package Task1;

import java.io.IOException;
import java.util.Scanner;

public class CalculateTaxUI {
	
	public static enum STATE{CHECKINGID, CALCULATINGTAX, SAVEOPTION, CANCELLED};
	private Scanner sc = new Scanner(System.in);
	private STATE state;
	private CalculateTaxControl control;
	
	
	
	public CalculateTaxUI(CalculateTaxControl control) {
		this.control = control;	
		control.setUI(this);
		state = STATE.CHECKINGID;
	}
			
	public void run() throws IOException{
		
		output("Calculate Tax\n\n");
		
		while(true) {
			
			switch(state) {
			
				case CHECKINGID:
		
					String ID = input("Please enter employee ID (Press enter to cancel): ");
					//check ID is na 4 digit number
					if(ID.length() == 0) {
						this.state = STATE.CANCELLED;
						break;
					}
					if(ID.length() == 4) {
						try {
							int integerID = Integer.parseInt(ID);
							control.setID(integerID);
							this.state = STATE.CALCULATINGTAX;
						}
						catch(NumberFormatException e){
							output("An employee ID must contain only numeric characters");
						}
					}
					else {
						output("Employee ID must be four characters in length");
					}
					break;
					
				case CALCULATINGTAX:
					
					String salary = input("Please enter the employee salary (Press enter to cancel): ");
					if(salary.length() == 0) {
						this.state = STATE.CANCELLED;
						break;
					}
					try {
						int salaryInteger = Integer.parseInt(salary);
						control.calculateTax(salaryInteger);
						this.state = STATE.SAVEOPTION;
						break;
					}
					catch(NumberFormatException e){
						output("Salary must be an integer");
					}
					
					break;
					
				case SAVEOPTION:
					
					String choice = input("Would you like to save this record? (Y/N): ");
					if(choice.toUpperCase().equals("Y")) {
						control.addRecord();
						output("Record saved");
						this.state = STATE.CHECKINGID;
						break;
					}
					else {
						output("Record not saved to file");
						this.state = STATE.CANCELLED;
					}
					
					
					
				case CANCELLED:
					
					output("Calculate Tax cancelled.");
					return;
					
				
			
			}
		}
	
	}
	private String input(String message) {
		System.out.println(message);
		return sc.nextLine();
	}
	
	private static void output(String message) {
		System.out.println(message);
	}

	

}
