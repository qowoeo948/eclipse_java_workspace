/*
 * 달력에 사용되는 셀을 정의한다. (일자 처리)
 * */
package day1126.calendar;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Box extends Canvas{
	String title;
	int width;
	int height;
	
	GraphicsContext context ;
	
	public Box(String title,int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		this.setWidth(width);
		this.setHeight(height);
		
		//모든 컴포넌트는 그래픽 처리에 사용되는 객체를 가지고 있다.
		context = this.getGraphicsContext2D();

		
		erase();
		
		
		//글씨 그리기
		context.setFill(Color.BLACK);
		context.setFont(new Font(17));
		context.fillText(title, 0, 20);
	}
	
	//현재 박스에 그려진 글씨 지우기
	public void erase() {
		
		context.setFill(Color.YELLOW);
		context.fillRect(0, 0, 100, 100); 
		
		context.setStroke(Color.BLACK);
		context.strokeRect(0, 0, width, height); 
		
	}
	
}
