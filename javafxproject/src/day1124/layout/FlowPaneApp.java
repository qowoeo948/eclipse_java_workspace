package day1124.layout;



import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneApp extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		FlowPane parent = new FlowPane(Orientation.VERTICAL,10,10);
		
		Button bt1 = new Button();
		Button bt2 = new Button();
		Button bt3 = new Button();
		
		parent.getChildren().add(bt1);
		parent.getChildren().add(bt2);
		parent.getChildren().add(bt3);
		
		
		showWindow(stage, parent);
	}
	
	public void showWindow(Stage stage,Parent parent) {
		Scene s = new Scene(parent);
		stage.setScene(s);
		stage.setWidth(500);
		stage.setHeight(500);
		stage.show();  //윈도우 보여주기
		
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
