package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Action은 범위가 넓다. (버튼에는 클릭, 텍스트박스-엔터 등)
//버튼에 클릭이벤트를 감지해보자!!
public class MyActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("나눌렀어?");
		
	}

}
