package Assignment3;

import javax.swing.JComboBox;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class UI extends Application{
	
	RadioButton rbInsert;
	RadioButton rbSearch;
	RadioButton rbUpdate;
	RadioButton rbResult;
	RadioButton rbGrade;
	Control control;
	
	//textfields
	TextField tfID = new TextField("ID");
	TextField tfStudentName = new TextField("Student Name");
	TextField tfQuiz = new TextField("Quiz");
	TextField tfA1 = new TextField("A1");
	TextField tfA2 = new TextField("A2");
	TextField tfA3 = new TextField("A3");
	TextField tfExam = new TextField("Exam");
	
	TextField tfAttribute = new TextField("Attribute");
	TextField tfValue = new TextField("Value");
	
	TextField tfNewValue = new TextField("New Value");
	
	ComboBox<String> searchCombo;
	ComboBox<String> updateCombo;
	
	TextField tfUsername = new TextField("Username");
	TextField tfPassword = new TextField("Password");
	
	String username;
	String password;
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		getUsernameAndPassword();
		
		control = new Control(username, password);
		
		//create radio buttons and add them to the toggle group
		rbInsert = new RadioButton("Insert Record");
		rbSearch = new RadioButton("Search");
		rbUpdate = new RadioButton("Update");
		rbResult = new RadioButton("Calculate Result");
		rbGrade = new RadioButton("Calculate Grade");
		
		ToggleGroup toggle = new ToggleGroup();
		rbInsert.setToggleGroup(toggle);
		rbSearch.setToggleGroup(toggle);
		rbUpdate.setToggleGroup(toggle);
		rbResult.setToggleGroup(toggle);
		rbGrade.setToggleGroup(toggle);
		
		//proceed button
		Button btProceed = new Button("Proceed");
		btProceed.setOnAction(e -> showProceed());
		
		//put in a pane
		VBox vbox = new VBox();
		vbox.getChildren().addAll(rbInsert, rbSearch, rbUpdate, rbResult, rbGrade);
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(vbox);
		borderPane.setRight(btProceed);
		
		Scene sc = new Scene(borderPane);
		
		primaryStage.setTitle("University Grading System");
		primaryStage.setMinWidth(400);
		primaryStage.setScene(sc);
		primaryStage.show();
		
		
	}
	
	private void getUsernameAndPassword() {

		Stage stage = new Stage();
			
		VBox vbox = new VBox(20);
		Button btOK = new Button("OK");
		btOK.setAlignment(Pos.CENTER_RIGHT);
		vbox.getChildren().addAll(new Label ("Please enter username and password for your local host"), tfUsername, tfPassword, btOK);
		
		btOK.setOnAction((e) -> {
			try {
				username = tfUsername.getText();
				password = tfPassword.getText();
				
			}
			catch(Exception ex) {
				
			}
			finally {
				stage.close();
			}
		});
		
		Scene sc = new Scene(vbox);
		stage.setScene(sc);
		stage.showAndWait();
		
		
		
	}

	private void showProceed() {
		
				
		if(rbInsert.isSelected()) {
			
			VBox vbox = new VBox();
			Stage insertStage = new Stage();
			
			//Hboxes for insert
			HBox hbIDi = new HBox();
			HBox hbNamei = new HBox();
			HBox hbQuizi= new HBox();
			HBox hbA1i= new HBox();
			HBox hbA2i = new HBox();
			HBox hbA3i = new HBox();
			HBox hbExami = new HBox();
					
			//button
			Button btProceed = new Button("Proceed");
			btProceed.setOnAction(e -> 
				proceedInsert(insertStage)
				
			);
			
			
			//setup hbox
			hbIDi.getChildren().addAll(new Label  ("ID:               \t"), tfID);
			hbNamei.getChildren().addAll(new Label("Student Name:"), tfStudentName);
			hbQuizi.getChildren().addAll(new Label("Quiz:           \t"), tfQuiz);
			hbA1i.getChildren().addAll(new Label  ("A1:             \t"), tfA1);
			hbA2i.getChildren().addAll(new Label("A2:             \t"), tfA2);
			hbA3i.getChildren().addAll(new Label("A3:             \t"), tfA3);
			hbExami.getChildren().addAll(new Label("Exam:           \t"), tfExam);
			
			//setup pane
			vbox.getChildren().addAll(hbIDi, hbNamei, hbQuizi, hbA1i, hbA2i, hbA3i, hbExami);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btProceed);
			
			//set the scene
			Scene sc = new Scene(pane);
			insertStage.setScene(sc);
			insertStage.setTitle("Insert");
			insertStage.show();
			
			
		}
		
		if(rbSearch.isSelected()) {
			VBox vbox = new VBox();
			
			//hboxes
			HBox hbAttribute = new HBox();
			HBox hbValue = new HBox();
			
			//button
			Button btSearch = new Button("Search");
			btSearch.setOnAction(e -> searchDatabase());
			
			//combobox
			searchCombo = new ComboBox();
			searchCombo.getItems().addAll("ID", "StudentName", "Quiz", "A1", "A2", "A3", "Exam", "Results", "Grade");
			
			hbAttribute.getChildren().addAll(new Label("Attribute: "), searchCombo);
			hbValue.getChildren().addAll(new Label("Value:\t"), tfValue);
			
			//setup pane
			vbox.getChildren().addAll(hbAttribute, hbValue);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btSearch);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage searchStage = new Stage();
			searchStage.setScene(sc);
			searchStage.setTitle("Search");
			searchStage.show();
		}
		
		if(rbUpdate.isSelected()) {
			VBox vbox = new VBox();
			
			HBox hbID = new HBox();
			HBox hbAttribute = new HBox();
			HBox hbValue = new HBox();
			
			//button
			Button btUpdate = new Button("Update");
			btUpdate.setOnAction(e -> updateDatabase());
			
			//combobox
			updateCombo = new ComboBox();
			updateCombo.getItems().addAll("ID", "StudentName", "Quiz", "A1", "A2", "A3", "Exam", "Results", "Grade");
			
			hbID.getChildren().addAll(new Label ("ID:   \t         "), tfID);
			hbAttribute.getChildren().addAll(new Label("Attribute: "), updateCombo);
			hbValue.getChildren().addAll(new Label("Value:\t"), tfNewValue);
			
			//setup pane
			vbox.getChildren().addAll(hbID, hbAttribute, hbValue);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btUpdate);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage searchStage = new Stage();
			searchStage.setScene(sc);
			searchStage.setTitle("Update");
			searchStage.show();
			
		}
		
		if(rbResult.isSelected()) {
			
			VBox vbox = new VBox();
			
			//Hboxes 
			HBox hbQuiz= new HBox();
			HBox hbA1= new HBox();
			HBox hbA2 = new HBox();
			HBox hbA3 = new HBox();
			HBox hbExam = new HBox();
			
			//button
			Button btGetResult = new Button("Get Result");
			btGetResult.setOnAction(e -> getResult());
			
			//setup hbox
			hbQuiz.getChildren().addAll(new Label("Quiz: \t"), tfQuiz);
			hbA1.getChildren().addAll(new Label("A1:   \t"), tfA1);
			hbA2.getChildren().addAll(new Label("A2:   \t"), tfA2);
			hbA3.getChildren().addAll(new Label("A3:   \t"), tfA3);
			hbExam.getChildren().addAll(new Label("Exam: \t"), tfExam);
			
			//setup pane
			vbox.getChildren().addAll(hbQuiz, hbA1, hbA2, hbA3, hbExam);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btGetResult);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage searchStage = new Stage();
			searchStage.setScene(sc);
			searchStage.setTitle("Calculate Result");
			searchStage.setMinWidth(400);
			searchStage.show();
			
			
		}
		
		if(rbGrade.isSelected()) {
			
			VBox vbox = new VBox();
			
			HBox hbQuizx= new HBox();
			HBox hbA1x= new HBox();
			HBox hbA2x = new HBox();
			HBox hbA3x = new HBox();
			HBox hbExamx = new HBox();
			
			//button
			Button btGetGrade = new Button("Get Grade");
			btGetGrade.setOnAction(e -> getGrade());
			
			//setup hbox
			hbQuizx.getChildren().addAll(new Label("Quiz: \t"), tfQuiz);
			hbA1x.getChildren().addAll(new Label("A1:   \t"), tfA1);
			hbA2x.getChildren().addAll(new Label("A2:   \t"), tfA2);
			hbA3x.getChildren().addAll(new Label("A3:   \t"), tfA3);
			hbExamx.getChildren().addAll(new Label("Exam: \t"), tfExam);
			
			//setup pane
			vbox.getChildren().addAll(hbQuizx, hbA1x, hbA2x, hbA3x, hbExamx);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btGetGrade);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage searchStage = new Stage();
			searchStage.setScene(sc);
			searchStage.setTitle("Calculate Grade");
			searchStage.setMinWidth(400);
			searchStage.show();
			
		}
		
		
		
	}
	
	
	
	private void getGrade() {
		try {
			double result = control.calculateResults(Integer.parseInt(tfQuiz.getText()), Integer.parseInt(tfA1.getText()), Integer.parseInt(tfA2.getText()), Integer.parseInt(tfA3.getText()), Integer.parseInt(tfExam.getText()));
	
			String grade = control.calcultateGrade(result);
			
			VBox vbox = new VBox();
			vbox.getChildren().add(new Text("Grade: " + grade));
			Scene sc = new Scene(vbox);
			Stage stage = new Stage();
			stage.setScene(sc);
			stage.show();
		}
		catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Calculation failed. Please check your inputs");
			alert.showAndWait();
		}
	}

	private void getResult() {
		
		try {
			double result = control.calculateResults(Integer.parseInt(tfQuiz.getText()), Integer.parseInt(tfA1.getText()), Integer.parseInt(tfA2.getText()), Integer.parseInt(tfA3.getText()), Integer.parseInt(tfExam.getText()));
			
			VBox vbox = new VBox();
			vbox.getChildren().add(new Text("Result: " + String.format("%.2f", result)));
			Scene sc = new Scene(vbox);
			Stage stage = new Stage();
			stage.setScene(sc);
			stage.show();
		}
		catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Calculation failed. Please check your inputs");
			alert.showAndWait();
		}
	}

	private void updateDatabase() {
		boolean updateSuccessful = control.updateRecord(tfID.getText(), updateCombo.getValue(), tfNewValue.getText());
		String attribute = updateCombo.getValue();
		
		if(!updateSuccessful) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Update failed. Please check your inputs");
			alert.showAndWait();
		}
		else {
			if(attribute.equals("Quiz") || attribute.equals("A1") || attribute.equals("A2") || attribute.equals("A3") || attribute.equals("Exam")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("You have updated an assesment item. Please update the final result. Please also check"
						+ " if grade needs to be updated.");
				alert.showAndWait();
			}
			Alert confirmation = new Alert(AlertType.CONFIRMATION);
			confirmation.setContentText("Update successful");
			confirmation.showAndWait();
		}
	}

	private void searchDatabase() {
		String result = control.search(searchCombo.getValue(), tfValue.getText());
		
		if(result != null) {
			VBox vbox = new VBox();
			vbox.getChildren().add(new Text(result));
			Scene sc = new Scene(vbox);
			Stage stage = new Stage();
			stage.setScene(sc);
			stage.show();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Search unsuccessful. Please check your inputs.");
			alert.showAndWait();
		}
	}

	private void proceedInsert(Stage insertStage) {
		
		//calculate results and grade
		try {
			Double result = control.calculateResults(Integer.parseInt(tfQuiz.getText()), Integer.parseInt(tfA1.getText()), 
					Integer.parseInt(tfA2.getText()), Integer.parseInt(tfA3.getText()), Integer.parseInt(tfExam.getText()));
			String grade = control.calcultateGrade(result);
			
			//insert
			boolean insertSuccesful = control.insertRecord(Integer.parseInt(tfID.getText()), tfStudentName.getText(), Integer.parseInt(tfQuiz.getText()), 
					Integer.parseInt(tfA1.getText()), Integer.parseInt(tfA2.getText()), Integer.parseInt(tfA3.getText()), 
					Integer.parseInt(tfExam.getText()), result, grade);
			
			if(!insertSuccesful) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Insert Aborted: Please ensure the ID is an 8 digit number and the scores lie btween 0 and 100");
				alert.showAndWait();
			}
			else {
				Alert confirmation = new Alert(AlertType.CONFIRMATION);
				confirmation.setContentText("Insertion successful");
				confirmation.showAndWait();
			}
			
							
		}
		catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Please ensure you use the correct format for the numeric values");
			alert.showAndWait();
		}
		finally {
			insertStage.close();
		}
		
		
		
		
	}

	



	public static void main(String[] args) {
		
		launch(args);
	}
	
	

}
