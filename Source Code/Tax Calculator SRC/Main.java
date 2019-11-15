package Task1;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	private enum STATE{MENU, CALCULATETAX, SEARCHTAX, EXIT};
	private static STATE state;
	private static Scanner sc;
	
	public static void main(String[] args) throws IOException{
		
		state = STATE.MENU;
		sc = new Scanner(System.in);
		runMain();
		
		
		
	}

	private static void runMain() throws IOException {
		
		while(true) {
			
			switch(state) {
			
				case MENU:
					
					output("Welcome to XYZ tax program\n");
					output("\n");
					output("1. Calculate Tax\n");
					output("2. Search Tax\n");
					output("3. Exit\n");
					String choice = input("Please select an option:\n");
					if(choice.equals("1")) {
						state = STATE.CALCULATETAX;
						break;
					}
					else if(choice.equals("2")) {
						state = STATE.SEARCHTAX;
						break;
					}
					else if (choice.equals("3")) {
						output("Thankyou for using the xyz tax program.");
						System.exit(0);
					}
					
				case CALCULATETAX:
					
					new CalculateTaxUI(new CalculateTaxControl()).run();
					state = STATE.MENU;
					break;
			
				case SEARCHTAX:
					
					new SearchTaxUI(new SearchTaxControl()).run();
					state = STATE.MENU;
					break;
					
			}
			
		}
		
	}
	
	private static String input(String message) {
		System.out.println(message);
		return sc.next();
		
		
	}
	
	private static void output(String message) {
		System.out.println(message);
	}
}
