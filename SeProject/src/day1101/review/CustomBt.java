package day1101.review;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomBt extends JButton implements ActionListener{
	
	public CustomBt() {
		this.addActionListener(this);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setBackground(Color.GREEN);
	}
	
	

}
