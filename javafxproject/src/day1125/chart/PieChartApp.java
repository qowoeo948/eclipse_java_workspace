/*
 * FX 지원차트
 * PieChart
 * BarChart
 * 
 * */
package day1125.chart;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartApp extends Application{
	PieChart pie;

	@Override
	public void start(Stage stage) throws Exception {
		pie = new PieChart();
		
		//파이차트의 데이터 구성하기
		PieChart.Data s1 = new PieChart.Data("안드로이드폰", 6); //안드로이드가 60%
		PieChart.Data s2 = new PieChart.Data("아이폰", 3); //30%
		PieChart.Data s3 = new PieChart.Data("윈도우폰", 1); //10%
		
		//파이차트에 적용
		pie.getData().add(s1);
		pie.getData().add(s2);
		pie.getData().add(s3);
		
		pie.setLegendSide(Side.RIGHT);
		
		showWindow(stage, pie);
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
