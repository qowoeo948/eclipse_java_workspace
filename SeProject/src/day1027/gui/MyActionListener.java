package day1027.gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

//Action은 범위가 넓다. (버튼에는 클릭, 텍스트박스-엔터 등)
//버튼에 클릭이벤트를 감지해보자!!
public class MyActionListener implements ActionListener{
	Button bt;
	JTextField t_input;
	JTextArea area;
	public MyActionListener(Button bt, JTextField t_input,JTextArea area) {
		this.bt = bt;
		this.t_input = t_input;
		this.area=area;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == bt) {
			String msg = t_input.getText();//텍스트필드 값을 구하자!!
			area.append(msg+"\n");
			t_input.setText("");
		}
		
	}

}
