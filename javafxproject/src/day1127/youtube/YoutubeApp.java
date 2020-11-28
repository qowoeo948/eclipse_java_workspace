package day1127.youtube;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class YoutubeApp extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane borderPane=(BorderPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("day1127/youtube/layout.fxml"));
		
		showWindow(stage, borderPane);
	}
	
	
	public void showWindow(Stage stage,Parent parent) {
		Scene s = new Scene(parent);
		stage.setScene(s);
		stage.setWidth(800);
		stage.setHeight(700);
		stage.show();  //윈도우 보여주기
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
}
