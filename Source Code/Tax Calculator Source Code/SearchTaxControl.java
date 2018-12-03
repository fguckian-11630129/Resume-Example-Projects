package Task1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SearchTaxControl {
	
	SearchTaxUI ui;
	TaxReport taxReport;
	
	
	public SearchTaxControl() throws IOException {
		
		checkTaxReportFile();
	}
	
		
	
	public String search(int empID) {
		
		return taxReport.searchRecord(empID);
		
	}
	
	private void checkTaxReportFile() throws IOException{
		File taxRateFile = new File("taxreport.txt");
		if(taxRateFile.exists()) {
			this.taxReport = new TaxReport(new File("taxreport.txt"));
		}
		else {
			String path = getReportPath();
			this.taxReport = new TaxReport(new File(path));
		}
	}
	
	 public String getReportPath() {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter tax report path: ");
			String path = sc.next();
			return(path);
			
		}
	
	



	public void setUI(SearchTaxUI searchTaxUI) {
		this.ui = searchTaxUI;
		
	}






	
}
