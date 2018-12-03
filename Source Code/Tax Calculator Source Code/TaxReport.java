package Task1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

//this class saves a tax record to file. It can also be used to search tax records
public class TaxReport {
	
	ArrayList<String> queue;
	File taxReportText;
	
	//constuctor takes file and puts it into a linked list. Using a linked
	//list preserves the order for pulling the latest entry.
	public TaxReport(File taxReportText) throws IOException{
		
		this.taxReportText = taxReportText;
		ArrayList<String> queue = new ArrayList<>();
		try(
			Scanner input = new Scanner(taxReportText);
		){
			
			//discard of the heading
			input.nextLine();
			
			//place the rest of the records into a linked list
			while(input.hasNext()) {
				queue.add(input.nextLine());
			}
			
			this.queue = queue;
			input.close();
		}
		
		
	}

	public void addRecord(int employeeID, double taxableIncome, double tax) throws IOException {
		
		//convert to string then append to stringbuilder. Format currency.
		String employeeIDString = Integer.toString(employeeID);
		String taxableIncomeString = String.format("%.2f", taxableIncome);
		String taxString = String.format("%.2f", tax);
		StringBuilder st = new StringBuilder();
		st.append(employeeIDString + "			");
		st.append(taxableIncomeString + "			");
		st.append(taxString);
		
		//use an outputstrem to write the new record
		try(
			PrintWriter output = new PrintWriter(new FileOutputStream(taxReportText, true));
		){
			output.write(System.getProperty("line.separator") + st.toString());
			
			
		}
		
	}
	
	public String searchRecord(int employeeID) {
		
		String empID = Integer.toString(employeeID);
		
		//search from the back to find the latest entry
		for(int i = queue.size()-1; i>-1; i--) {
			String record = queue.get(i);
			String recordID = record.substring(0, 4);
			if(empID.equals(recordID)) {
				return queue.get(i);
			}
			
		}
		return("This employee ID does not appear in any of our records.");
		
	}
	
	

}
