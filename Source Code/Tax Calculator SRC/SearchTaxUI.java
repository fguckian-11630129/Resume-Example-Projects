package Task1;

import java.util.Scanner;

public class SearchTaxUI {
	
	private enum STATE {CHECKINGID, CANCELLED}
	private SearchTaxControl control;
	private STATE state;
	private Scanner sc = new Scanner(System.in);
	
		
	
	
	public SearchTaxUI(SearchTaxControl control) {
		this.control = control;
		control.setUI(this);
		state = STATE.CHECKINGID;
		
	}




	public void run() {
		
		while(true) {
			
			switch(state) {
			
				case CHECKINGID:
					
					String choice = input("Please enter the employee ID (press enter to quit):");
					
					if(choice.length() == 0) {
						
						this.state = STATE.CANCELLED;
						
						break;
						
					}
					if(choice.length() == 4) {
																
						try {
							int empID = Integer.parseInt(choice);
							output("EmployeeID		Taxable Income		Tax");
							output(control.search(empID));
										
						}
						catch (NumberFormatException e) {
							output("Employee ID must be made up of numeric characters");
							
						}
					}
					else {
						output("Employee ID must be four characters in length.\n");
					}
					break;
					
					
				case CANCELLED:
					
					output("Search Tax cancelled");
					return;
					
				
				
			}//switch	
		
		}//while
		
	}//run
	
	private String input(String message) {
		System.out.println(message);
		return sc.nextLine();
		
		
	}
	
	private void output(String message) {
		System.out.println(message + "\n");
	}




	
	

}
