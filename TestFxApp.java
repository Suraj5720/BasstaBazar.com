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

public class TestFxApp extends Application {

	public static void main(String[]args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Label user_id = new Label("Username");
		Label password = new Label("password");
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		
		Button btn = new Button("Submit");
		GridPane grid =new GridPane();
		grid.addRow(0,user_id,tf1);
		grid.addRow(1,password,tf2);
		grid.addRow(2,btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Enterd User Id: "+tf1.getText()+"  "+"Password is: "+tf2.getText());
				tf1.setText(null);
				tf2.setText(null);
				
						
			}
			
		});
		Scene scene = new Scene(grid,800,200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Java Fx Application");
		primaryStage.show();
	}
	
	

}
