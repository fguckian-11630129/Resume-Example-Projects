package Assignment3;

import java.sql.*;

import javafx.scene.control.Alert.AlertType;

public class Database {
	
	Connection connection;
	Statement statement;
	
	public Database() {
		
		loadDriver();		
		
	}
	
	//this method loads the MySql driver 
	public void loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			output("Driver loaded");
		}
		catch(Exception e) {
			output("Problem loading driver");
		}
	}
	
	public void connect(String username, String password) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gradeprocessing", username, password);
			this.connection = connection;
		}
		catch(SQLException e) {
			output("Problem connecting to database");
		}
	}
	
	public void createTable() {
		
		//check if exists
		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet rs = meta.getTables(null, null, "java2", null);
			if(rs.next()) {
				output("Table exists");
			}
			else {		
				
				String java2Table =  "create table java2 (ID integer (8), StudentName varchar(25), Quiz integer, A1 integer, A2 integer, A3 integer, Exam integer, Results double, Grade varchar(2), primary key(ID), CHECK (Quiz>=0 AND Quiz<=100 AND A1>=0 AND A1<=100 AND A2>=0 AND A2<=100 AND A3>=0 AND A3<=100 AND Exam>=0 AND Exam<=100	AND Results>=0 AND Results<=100))";
								
				try {
					Statement statement = connection.createStatement();
					this.statement = statement;
					//output("XXXXX");
					statement.executeUpdate(java2Table);
					output("Table created");
				}
				catch(SQLException e) {
					output("Problem creating table");
				}
			}
		}//try
		catch(SQLException e) {
			output("Problem checking if table exists");
		}
		
	}
	
	public boolean insertRecord(int ID, String studentName, int quiz, int A1, int A2, int A3, int exam, double results, String grade) {
		
		if(isOutOfRange(quiz) || isOutOfRange(A1) || isOutOfRange(A2) || isOutOfRange(A3) || isOutOfRange(exam)) {
			//output("Please ensure all your results are between 0 and 100");
			return false;
		}
		else if(Integer.toString(ID).length() != 8){
			//output("Please ensure the ID is 8 characters in length");
			return false;
		}
		else {
			String stID = Integer.toString(ID);
			String stQuiz = Integer.toString(quiz);
			String stA1 = Integer.toString(A1);
			String stA2 = Integer.toString(A2);
			String stA3 = Integer.toString(A3);
			String stExam = Integer.toString(exam);
			String stResults = String.format("%.2f", results);
			
			String speechMark = "\"";
			
			StringBuilder stBuilder = new StringBuilder();
			stBuilder.append(stID + ", " + speechMark);
			stBuilder.append(studentName + speechMark + ", ");
			stBuilder.append(stQuiz + ", ");
			stBuilder.append(stA1 + ", ");
			stBuilder.append(stA2 + ", ");
			stBuilder.append(stA3 + ", ");
			stBuilder.append(stExam + ", ");
			stBuilder.append(stResults + ", " + speechMark);
			stBuilder.append(grade + speechMark + ")");
			
			
							
			String insert = "insert into java2 (ID, StudentName, Quiz, A1, A2, A3, Exam, Results, Grade) values(" + 
					stBuilder.toString();
			//output(insert);
			
			try {
				statement.executeUpdate(insert);
				return true;
			}
			catch(Exception e) {
				output("Problem inserting record");
				return false;
			}
			
		}
	}
	
	public boolean isOutOfRange(int score) {
		if(score <= 100 && score >=0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public String search(String attribute, String value) {
		
		
		String speechMark = "\"";
		String queary = "select * from java2 where " + attribute + " = " + speechMark + value + speechMark;
		ResultSet resSet = null;
		String row = null;
		
		try {
			
			resSet = statement.executeQuery(queary);
			
			try {
				
				while(resSet.next()) {
					row = "ID: " + "\t\t\t" + resSet.getString(1) + "\n" + "Student Name: " + "" + resSet.getString(2) + "\n" + "Quiz: " + "\t\t" + resSet.getString(3) + "\n" +
							 "A1: " + "\t\t\t" + resSet.getString(4) + "\n" +  "A2:" + "\t\t\t" + resSet.getString(5) + "\n" +  "A3: " + "\t\t\t" +resSet.getString(6) + "\n" +  "Exam: " + "\t\t" +
							resSet.getString(7) + "\n" +  "Result: " + "\t\t" + resSet.getString(8) + "\n" +  "Grade: " + "\t\t" +resSet.getString(9);
				}
			}
			catch(SQLException e) {
				output("Problem turning row to string");
			}
		}
		catch(SQLException e) {
			output("Problem searching");
		}
								
		
		//output(row);
		return row;
	
	}
	
	public boolean update(String ID, String attribute, String newValue) {
		
		if(attribute.equals("Quiz") || attribute.equals("A1") || attribute.equals("A2") || attribute.equals("A3") || attribute.equals("Exam")){
			if(isOutOfRange(Integer.parseInt(newValue))){
				return false;
			}
		}
		
		try {
			String speechMark = "\"";
			String queary = "update Java2 set " + attribute + " = " + speechMark + newValue + speechMark + " where ID = " + ID; 
	 		//output(queary);
			int updateStatus = statement.executeUpdate(queary);
	 		
			if(updateStatus>0) {
		 		return true;
				//output(queary);
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			return false;
		}
		
	}

	private void output(String message) {
		System.out.println(message);
		
	}
	
	

}
