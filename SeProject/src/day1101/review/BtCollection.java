package day1101.review;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BtCollection extends JFrame implements ActionListener{
	JPanel p_north;
	JPanel p_center;
	JButton bt1;
	JButton bt2;
	
	ArrayList<JButton> btn = new ArrayList<JButton>();
	
	public BtCollection() {
		p_north = new JPanel();
		p_center = new JPanel();
		bt1 = new JButton("생성");
		bt2 = new JButton("배경색");
		p_north.add(bt1);
		p_north.add(bt2);
		
		this.add(p_north,BorderLayout.NORTH);
		
		this.add(p_center);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
	
		
		this.setSize(700,700);
		this.setVisible(true);
		
	}
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == bt1) {
			create();
		}
		if(obj == bt2) {
			setBg();
		}
		
	}
	
	public void create() {
		JButton bt = new CustomBt();
		p_center.add(bt);
		
		btn.add(bt);
		bt.setText("버튼"+Integer.toString(btn.size()));
		p_center.updateUI();
	}
	public void setBg() {
	
		for(int i=0;i<btn.size();i++) {
			JButton bt = btn.get(i);
			bt.setBackground(Color.RED);
		}
	}
	
	
	public static void main(String[] args) {
		new BtCollection();
	}

}
