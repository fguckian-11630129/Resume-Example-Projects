package Task1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//a Tax rate object can be given a salary and will return the tax on that salary
public class TaxRate {
		
	private ArrayList<Integer> dollars;
	
	private ArrayList<Double> cents;
	private ArrayList<Integer> upperLimits;
	private ArrayList<Integer> baseAmmount;
	
	public TaxRate(File rateFile) throws IOException {
		
		//try with resources to read file into an ArrayLIst<String>
		try(
				Scanner input = new Scanner(rateFile);
		){
			ArrayList<String> temp = new ArrayList<String>();
			while(input.hasNext()) {
				temp.add(input.next());
			}
			
			//close scanner
			input.close();
			
			//call analyse file
			analyseFile(temp);
		}
		
		
		
		
	}
	
	//this public method will accept a salary integer and return the ammount of tax owed
	public double getTax(double salary) {
				
		if(salary<upperLimits.get(0)) {
			return 0;
		}
		else if(salary>upperLimits.get(0) && salary<upperLimits.get(1)) {
			return (salary-upperLimits.get(0))*cents.get(1);
		}
		else if(salary>upperLimits.get(1) && salary<upperLimits.get(2)) {
			return (salary-upperLimits.get(1))*cents.get(2) + baseAmmount.get(2);
		}
		else if(salary>upperLimits.get(2) && salary<upperLimits.get(3)) {
			return (salary-upperLimits.get(2))*cents.get(3) + baseAmmount.get(3);
		}
		else {
			return (salary-upperLimits.get(3))*cents.get(4) + baseAmmount.get(4);
		}
		
	}
	
	//this method will go through the temp ArrayList and find the meaningful data
	private void analyseFile(ArrayList<String> fileAsArray) {
			
		//extract data that has money symbols attached to it
		extractDollars(fileAsArray);
		extractCents(fileAsArray);
				
		//now we need to take the lower limits and upper limits of the taxable income range
		findUpperLimits(dollars);
		//we need the base tax ammount for the higher tax brackers
		findBaseAmmount(fileAsArray);
		
	}
	
	//this method will find the strings that begin with a $ symbol and put them 
	//into an array. 
	private void extractDollars(ArrayList<String> fileAsArray) {
		
		ArrayList<Integer> dollars = new ArrayList<Integer>();
		for(int i = 0; i<fileAsArray.size(); i++) {
			if(fileAsArray.get(i).startsWith("$")) {
				//get rid of the commas
				String dollarString = fileAsArray.get(i).replaceAll(",", "");
				//convert to integer
				int numericDollar = Integer.parseInt(dollarString.substring(1));
				dollars.add(numericDollar);
			}
		}
		
		//make the class variable reference this ArrayList<Integer> dollars
		
		this.dollars = dollars;
		
		
	}
	
	//this method will extract cents values from the file and put them in an array
	private void extractCents(ArrayList<String> fileAsArray) {
		
		ArrayList<Double> cents = new ArrayList<>();
		//first tax bracket has 0 cents
		cents.add(0.0);
		for(int i = 0; i<fileAsArray.size(); i++) {
			//if string ends with c and the initial character is a digit
			if(fileAsArray.get(i).endsWith("c") && Character.isDigit(fileAsArray.get(i).charAt(0))) {
				cents.add((Double.parseDouble(fileAsArray.get(i).substring(0, (fileAsArray.get(i).length() - 1))))/100);
			}
		}
		
		this.cents = cents;
		
		
		
	}
	
	//find the upper limits of each tax bracket
	private void findUpperLimits(ArrayList<Integer> dollars) {
		
		ArrayList<Integer> upperLimits = new ArrayList<Integer>();
		int index = 0;
		//add the first limit
		upperLimits.add(dollars.get(index));
		//the next upper limit will be the next number larger than the current
		//upper limit by more than $1
		for(int i = 1; i<dollars.size();i++) {
			if(dollars.get(i) > ((upperLimits.get(index)) + 1)) {
				upperLimits.add(dollars.get(i));
				index++;
			}
			
		}
		
		this.upperLimits = upperLimits;
		
		
	}
	
	//this method will find the base ammount of tax for each income level.
	//the base ammount is the ammount of tax an income level stats off with
	//before you start adding a percentage of the dollar
	private void findBaseAmmount(ArrayList<String> fileAsArray) {
		
		ArrayList<Integer> baseAmmount = new ArrayList<Integer>();
		//the first two salary leve.s have no base tax
		baseAmmount.add(0);
		baseAmmount.add(0);
		// " plus " is the keyword for finding base ammounts
		for(int i = 1; i<fileAsArray.size(); i++) {
			if(fileAsArray.get(i).equals("plus")) {
				String temp = fileAsArray.get(i-1).replaceAll(",", "");
				baseAmmount.add(Integer.parseInt(temp.substring(1)));
			}
			
		}
		this.baseAmmount = baseAmmount;
		
	}

	
	
	
}
