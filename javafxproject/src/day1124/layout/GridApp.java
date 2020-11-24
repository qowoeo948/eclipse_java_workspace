package day1124.layout;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//GridLayout과 기능이 같아 (즉 행과 열로 배치하는 레이아웃)
public class GridApp extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		GridPane parent = new GridPane();
		
		//AWT와 틀리게, 컨테이너에 부착시 행,열 형태가 결정됨 
		Button[] btn = new Button[6];
		
		for(int i=0;i<btn.length;i++) {
			btn[i] = new Button("버튼"+i);
			btn[i].setPrefWidth(100);
			btn[i].setPrefHeight(100);
		}
		
		//parent.add(child,호,층);
		
		parent.add(btn[0], 0, 0);
		parent.add(btn[1], 1, 0);
		parent.add(btn[2], 2, 0);
		
		parent.add(btn[3], 0, 1);
		parent.add(btn[4], 1, 1);
		parent.add(btn[5], 2, 1);
		
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
