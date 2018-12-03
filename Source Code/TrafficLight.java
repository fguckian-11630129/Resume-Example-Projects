package Assignment3;

import java.util.Observable;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TrafficLight extends Application {
	
	StackPane currentColor;
	StackPane spGreen = GreenLight.get();
	StackPane spOrange = OrangeLight.get();
	StackPane spRed = RedLight.get();
	Thread myThread;
	volatile boolean suspend;
	
	Lock lock = new ReentrantLock();
	Condition goAhead = lock.newCondition();
	
	
	
		
	@Override
	public void start(Stage primaryStage) {
		
		TextField tfGreen = new TextField("3");
		tfGreen.setPrefWidth(50);
		BorderPane bpGreen = new BorderPane();
		bpGreen.setLeft(new Label ("Green"));
		bpGreen.setRight(tfGreen);
				
		TextField tfOrange = new TextField("3");
		tfOrange.setPrefWidth(50);
		BorderPane bpOrange = new BorderPane();
		bpOrange.setLeft(new Label("Orange  "));
		bpOrange.setRight(tfOrange);
				
		TextField tfRed = new TextField("3");
		tfRed.setPrefWidth(50);
		BorderPane bpRed = new BorderPane();
		bpRed.setLeft(new Label ("Red"));
		bpRed.setRight(tfRed);
		
		Button btStart = new Button("Start");
		Button btStop = new Button("Stop");
		
		HBox btHbox = new HBox(50);
		btHbox.getChildren().addAll(btStart, btStop);
		btHbox.setAlignment(Pos.CENTER);
		
		VBox tfVbox = new VBox();
		tfVbox.getChildren().addAll(bpGreen, bpOrange, bpRed);
		
		//combine textfields with buttons
		BorderPane bpCombinedTextButton = new BorderPane();
		bpCombinedTextButton.setLeft(tfVbox);
		bpCombinedTextButton.setBottom(btHbox);
		
		//combine textButton with the traffic light
		BorderPane bpAll = new BorderPane();
		bpAll.setPadding(new Insets(5,5,5,5));
		//initialise red
		currentColor = spRed;
		bpAll.setTop(currentColor);
		bpAll.setBottom(bpCombinedTextButton);
		
		
		
	
		myThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						
						while(suspend) {
							
							
						}
						
						if(currentColor == spRed) {
							currentColor = spGreen;
						}
						else if(currentColor == spGreen) {
							currentColor = spOrange;
						}
						else if(currentColor == spOrange) {
							currentColor = spRed;
						}
						
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								bpAll.setTop(currentColor);
							}
						});
						
						if(currentColor == spGreen) {
							
							int x = Integer.parseInt(tfGreen.getText());
							if(x>0) {
								System.out.println("Change light color...");
								TimeUnit.SECONDS.sleep(1);							
								for(int i = x-1; i!=0; i--) {
									System.out.println("Green light will change color in " + i + " seconds...");
									TimeUnit.SECONDS.sleep(1);
									
								}
							}
						}
						else if(currentColor == spOrange) {
							
							int x = Integer.parseInt(tfOrange.getText());
							if(x>0) {
								System.out.println("Change light color...");
								TimeUnit.SECONDS.sleep(1);							
								for(int i = x-1; i!=0; i--) {
									System.out.println("Orange light will change color in " + i + " seconds...");
									TimeUnit.SECONDS.sleep(1);
									
								}
							}
						}
						else if(currentColor == spRed) {
							
							int x = Integer.parseInt(tfRed.getText());
							if(x>0) {
								System.out.println("Change light color...");
								TimeUnit.SECONDS.sleep(1);							
								for(int i = x-1; i!=0; i--) {
									System.out.println("Red light will change color in " + i + " seconds...");
									TimeUnit.SECONDS.sleep(1);
									
								}
							}
						}
						
						
					}//while
				}//try
				catch(InterruptedException e) {
					
				}
				
			}//run
			
			
		});
		
		
		
		btStart.setOnAction((e) -> {
			
			if(myThread.isAlive()) {
				suspend = false;
				
							
			}
			else {
				myThread.start();
			}
			
		});
					
		
		btStop.setOnAction((e) -> {
			suspend = true;
		});
		
		primaryStage.setOnCloseRequest((e) ->  {
			System.exit(0);
		});
				
		Scene sc = new Scene(bpAll);
		primaryStage.setScene(sc);
		primaryStage.show();
		
		
	}//start

		
	
	public static class GreenLight {
		
		public static StackPane get() {
			Circle greenCircle = new Circle(100);
			greenCircle.setFill(Color.GREEN);
			Circle greyCircle1 = new Circle(100);
			greyCircle1.setFill(Color.GREY);
			Circle greyCircle2 = new Circle(100);
			greyCircle2.setFill(Color.GREY);
			Rectangle background = new Rectangle(670, 230);
			background.setArcHeight(20);
			background.setArcWidth(20);
			
			StackPane stack = new StackPane();
			Insets insets = new Insets(20,20,20,20);
			stack.setMargin(background, insets);
			
			HBox hbox = new HBox(20);
			hbox.setAlignment(Pos.CENTER);
			
			hbox.getChildren().addAll(greenCircle, greyCircle1, greyCircle2);
			stack.getChildren().addAll(background, hbox);
			
			return stack;
			
			
		}
		
	}
	
	public static class OrangeLight {
		
		public static StackPane get() {
			Circle orangeCircle = new Circle(100);
			orangeCircle.setFill(Color.ORANGE);
			Circle greyCircle1 = new Circle(100);
			greyCircle1.setFill(Color.GREY);
			Circle greyCircle2 = new Circle(100);
			greyCircle2.setFill(Color.GREY);
			Rectangle background = new Rectangle(670, 230);
			background.setArcHeight(20);
			background.setArcWidth(20);
			
			StackPane stack = new StackPane();
			Insets insets = new Insets(20,20,20,20);
			stack.setMargin(background, insets);
			
			HBox hbox = new HBox(20);
			hbox.setAlignment(Pos.CENTER);
			
			hbox.getChildren().addAll(greyCircle1, orangeCircle, greyCircle2);
			stack.getChildren().addAll(background, hbox);
			
			return stack;
			
			
		}
	}
	
	public static class RedLight {
			
			public static StackPane get() {
				Circle redCircle = new Circle(100);
				redCircle.setFill(Color.RED);
				Circle greyCircle1 = new Circle(100);
				greyCircle1.setFill(Color.GREY);
				Circle greyCircle2 = new Circle(100);
				greyCircle2.setFill(Color.GREY);
				Rectangle background = new Rectangle(670, 230);
				background.setArcHeight(20);
				background.setArcWidth(20);
				
				StackPane stack = new StackPane();
				Insets insets = new Insets(20,20,20,20);
				stack.setMargin(background, insets);
				
				HBox hbox = new HBox(20);
				hbox.setAlignment(Pos.CENTER);
				
				hbox.getChildren().addAll(greyCircle1, greyCircle2, redCircle);
				stack.getChildren().addAll(background, hbox);
				
				return stack;
				
				
			}
			
			
		}

}
