package Assignment3;

public class Control {
	
	Database database;
	
	
	public Control (String username, String password) {
		
		this.database = new Database();
		//initiate the table
		database.connect(username, password);
		database.createTable();
		
	}

	
	
	
	public double calculateResults(int quiz, int a1, int a2, int a3, int exam) {
		double result = quiz*.05 + a1*.15 + a2*.2 + a3*.1 + exam*.5;
		return result;
	}
	
	public String calcultateGrade(double result) {
		
		if(result>=85 && result<=100) {
			return "HD";
		}
		else if(result>=75) {
			return "DI";
		}
		else if(result>=65) {
			return "CR";
		}
		else if(result>=50) {
			return "PS";
		}
		else if(result>=0) {
			return "FL";
		}
		else {
			return null;
		}
	}
	
	public boolean insertRecord(int ID, String studentName, int quiz, int a1, int a2, int a3, int exam, double result, String grade ) {
		 boolean insertSuccesful = database.insertRecord(ID, studentName, quiz, a1, a2, a3, exam, result, grade);
		 return insertSuccesful;
	}
	
	public String search(String attribute, String value) {
		String result = database.search(attribute, value);
		return result;
	}
	
	public boolean updateRecord(String ID, String attribute, String newValue) {
		boolean updateSuccessful = database.update(ID, attribute, newValue);
		
		return updateSuccessful;
	}
	
	

}
