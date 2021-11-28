package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.*;
public class TestFxApp extends Application {

	public static void main(String[]args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		GridPane pane =new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25));		

		Label user_id = new Label("Username");
		Label password = new Label("password");
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		
		Button btn = new Button("Submit");
		pane.addRow(0,user_id,tf1);
		pane.addRow(1,password,tf2);
		pane.addRow(2,btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Enterd User Id: "+tf1.getText()+"  "+"Password is: "+tf2.getText());
				tf1.setText(null);
				tf2.setText(null);
				
						
			}
			
		});
		Scene scene = new Scene(pane,800,200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Java Fx Application");
		primaryStage.show();
	}
	
	

}
