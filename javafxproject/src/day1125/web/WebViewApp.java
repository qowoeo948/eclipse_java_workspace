/*
 * awt / swing에서 부분적으로나마 html이 적용될 수 있으나,
 * javascript는 실행될 수 없다.
 * 
 * */

package day1125.web;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewApp extends Application{
	WebView webView; //html문서를 포함할 수 있는 컨테이너
	

	@Override
	public void start(Stage stage) throws Exception {
		//youtube를 봐보자
		webView = new WebView();
		webView.getEngine().load("https://www.youtube.com/watch?v=ADLk4XIs8rc");
		webView.setPrefSize(1200, 1200);
		showWindow(stage, webView);
		
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
