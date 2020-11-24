package day1124.components;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ImageViewApp extends Application{
	
	String url;
	ImageView imageView;
	
	@Override
	public void start(Stage stage) throws Exception {
		/*
		 *
		 *1) String의 url로 생성하는 방법
		url="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNG4GAXOlMoR-laqdP1j6C7okjIG94uylcig&usqp=CAU";
		imageView = new ImageView(url);
		*/
		
		
		//2) Image 객체를 이용하는 방법
		url="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNG4GAXOlMoR-laqdP1j6C7okjIG94uylcig&usqp=CAU";
		Image img = new Image(url);
		imageView = new ImageView(img);
		

		//이미지의 비율을 자체적으로 계산하여 유지하는 방법?
		imageView.setPreserveRatio(true); //비율
		imageView.setFitWidth(300);
		imageView.setFitHeight(300);
		FlowPane parent = new FlowPane(imageView);
//		parent.getChildren().add(imageView);
		
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
