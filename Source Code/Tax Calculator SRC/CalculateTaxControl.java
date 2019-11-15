package Task1;

import java.io.*;
import java.util.Scanner;

import org.omg.PortableInterceptor.ACTIVE;


public class CalculateTaxControl {
	
	private CalculateTaxUI ui;
	private TaxRate taxRate;
	private TaxReport taxReport;
	private int employeeID;
	private double tax;
	private double salary;
	
	public CalculateTaxControl() throws IOException{
		
		checkTaxRateFile();
		checkTaxReportFile();
		
	}
	
	//check the taxratefile exists. Asks for path if not
	private void checkTaxRateFile() throws IOException{
		File taxRateFile = new File("taxrate.txt");
		if(taxRateFile.exists()) {
			this.taxRate = new TaxRate(new File("taxrate.txt"));
		}
		else {
			String path = getRatePath();
			this.taxRate = new TaxRate(new File(path));
		}
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
	
	
	public String getRatePath() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter tax rate path: ");
		String path = sc.next();
		return(path);
		
	}
	
    public String getReportPath() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter tax report path: ");
		String path = sc.next();
		return(path);
		
	}

	public void setUI(CalculateTaxUI ui) {
		this.ui = ui;
	}

	
	public void calculateTax(double salary) {
		this.salary = salary;
		this.tax = taxRate.getTax(salary);
		
	}
	
	public double getTax() {
		return this.tax;
	}

	public void setID(int employeeID) {
		this.employeeID = employeeID;
		
	}

	public void addRecord() throws IOException {
		taxReport.addRecord(employeeID, salary, tax);
		
	}
	
	
}
