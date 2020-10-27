package day1027.gui;

import java.awt.Button;
import java.awt.Frame;

public class Test extends Frame {
	Button bt;

	public Test() {
		bt = new Button("나버튼");
		add(bt);
		setVisible(true);
		setSize(300, 400);
	}

	public static void main(String[] args) {
		new Test();

	}

}
