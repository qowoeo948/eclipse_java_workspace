package day1124.layout;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class EventApp extends Application{
	Button bt;
	TextField t;
	ImageView imageView;

	@Override
	public void start(Stage stage) throws Exception {
		//액션이벤트,키보드이벤트,마우스 이벤트 처리
		bt = new Button("나버튼");
		t = new TextField();
		imageView = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwMG7CQ3FoPku6FFsf9T9p0HW6xRi7VX0CwQ&usqp=CAU");
	
		imageView.setFitWidth(200);
		imageView.setFitHeight(200);
		imageView.setPreserveRatio(true);
		
		//이벤트소스와 이벤트핸들러 연결
		bt.setOnAction((e)->{
			System.out.println("나눌럿어?");
		});
	
		
		t.setOnKeyReleased((e)->{
			if(e.getCode()==KeyCode.ENTER) {
				System.out.println("엔터 눌렀어?");				
			}
		});
		
		imageView.setOnMouseReleased((e)->{
			System.out.println("눌?");
		});
		
		FlowPane flow = new FlowPane(bt,t,imageView);
		
		showWindow(stage, flow);
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
