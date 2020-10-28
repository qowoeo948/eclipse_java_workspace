package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JTextField;

public class LineCanvas extends Canvas{
	//default접근 제한자는 같은 패키지에 존재하는 클래스 만이 접근가능함
	LineMaker lineMaker;
	int x1,y1,x2,y2;
	//이 메서드를 호출하는 자는, LineMaker의 주소값을 넘기면 된다.
	//꼭 생성자로 안하고 그냥 메서드로 넘길수있다.
	//생성자만이라는 고정관념은 XXXXX
	public void setLineMaker(LineMaker lineMaker) {
		this.lineMaker=lineMaker;
		
	}
	//캔버스는 개발자가 직접 그림을 그릴수 있는 빈 도화지이다!!
	//따라서 paint()메서드를 재정의하면 된다.
	@Override
	public void paint(Graphics g) {
		x1=Integer.parseInt(lineMaker.t_input1.getText());
		y1=Integer.parseInt(lineMaker.t_input2.getText());
		x2=Integer.parseInt(lineMaker.t_input3.getText());
		y2=Integer.parseInt(lineMaker.t_input4.getText());
		
		g.drawLine(x1,y1,x2,y2); //두점을 연결한 선 그리기!!

		
	}
}
