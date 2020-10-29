package day1028.graphic.line;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LineMaker extends JFrame  {
	JLabel x1,y1,x2,y2;
	JTextField t_input1,t_input2,t_input3,t_input4;
	JButton bt;
	JPanel p_north;
	LineCanvas can;

	public LineMaker() {
		super("선 그리기 어플리케이션");
		x1 = new JLabel("x1");
		y1 = new JLabel("y1");
		x2 = new JLabel("x2");
		y2 = new JLabel("y2");
		t_input1 = new JTextField("0",8); //앞께 초기값 줄 수 있음
		t_input2 = new JTextField("0",8);
		t_input3 = new JTextField("0",8);
		t_input4 = new JTextField("0",8);
		bt = new JButton("OK");
		p_north = new JPanel();
		can = new LineCanvas();
		can.setLineMaker(this); //캔버스에게 나의 주소값 넘기기
	
		p_north.add(x1);
		p_north.add(t_input1);
		p_north.add(y1);
		p_north.add(t_input2);
		p_north.add(x2);
		p_north.add(t_input3);
		p_north.add(y2);
		p_north.add(t_input4);
		p_north.add(bt);

		this.add(p_north, BorderLayout.NORTH);
		p_north.setBackground(Color.YELLOW);
		
		this.add(can);
		//this.add(can, BorderLayout.CENTER);
		can.setBackground(Color.PINK);

    	bt.addActionListener(new MyListener(can));

		this.setSize(700, 600);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	

	public static void main(String[] args) {
		new LineMaker();
	}

}
