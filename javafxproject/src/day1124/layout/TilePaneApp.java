package day1124.layout;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneApp extends Application{


	@Override
	public void start(Stage stage) throws Exception {
		TilePane parent = new TilePane(Orientation.VERTICAL,10,10);
	
		Button[]btn = new Button[10];
		
		for(int i=0;i<btn.length;i++) {
			btn[i]=new Button("버튼 "+i);
			btn[i].setPrefSize(70, 70);
			parent.getChildren().add(btn[i]);
		}
		
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
