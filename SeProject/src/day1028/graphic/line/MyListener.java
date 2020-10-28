package day1028.graphic.line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{
	LineCanvas can;
	
	//캔버스의 주소값을 넘겨받을 메서드가 필요하다
	//이번엔 생성자로 가자
	public MyListener(LineCanvas can) {
		this.can = can;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//LineMaker의 캔버스에 선을 그리되, LineMaker 클래스의 JTextField의 값을 이용하여...
		
		//paint메서드는 개발자가 직접호출할 수 도 없고, 호출해서도 안된다.
		//paint()메서드는 그림이 그려질 준비가 되었을때, 시스템 즉 JVM에 의해 호출된다.
		//따라서 개발자가 원하는 타임에, 그림을 갱신하게 하려면, paint() 메서드를 직접 호출해서는
		//안되고, 갱신 할 것을 시스템에 요청해야한다!!
		//repaint() 다시 그려주세요~~  ->update()화면 지우기 ->paint()
		//캔버스가 보유한 .repaint(); 를 버튼을 눌렀을 때 호출하면 된다!!
		can.repaint();
		//Linecanvas의 paint(); 불가능
		
	}

}
