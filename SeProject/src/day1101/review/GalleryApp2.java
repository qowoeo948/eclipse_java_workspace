package day1101.review;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import day1029.homework.solution.XCanvas;

public class GalleryApp2 extends JFrame implements ActionListener{
	ArrayList<Thumb2> list = new ArrayList<Thumb2>();
	String dir= "C:/Users/user/eclipse-workspace/SeProject/res/travel2/" ;
	String[] src= {
			"aa.jpg",
			"ab.jpg",
			"ax.jpg",
			"bm.jpg",
			"et.jpg",
			"kg.jpg",
			"mx.jpg",
			"pk.jpg",
			"ub.jpg",
			"ya.jpg"
	};
	
	JLabel la_name;
	JPanel p_west;
	JPanel p_south;
	JPanel p_center;
	JButton bt_prev;
	JButton bt_next;
	JScrollPane scroll;
	
	XCanvas2 can ;
	
	int n=0;
	
	public GalleryApp2() {
		la_name = new JLabel();
		p_west = new JPanel();
		p_south = new JPanel();
		p_center = new JPanel();
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		scroll = new JScrollPane(p_west);
		can = new XCanvas2(dir+src[n]);
		
		for(int i=0;i<src.length;i++) {
			Thumb2 thumb = null;
			list.add(thumb = new Thumb2(dir+src[i],this));
			p_west.add(thumb);
		}
		

		
		
		this.add(scroll,BorderLayout.WEST);
		p_west.setPreferredSize(new Dimension(100,600));
		this.add(p_south,BorderLayout.SOUTH);
		p_center.add(can);
		this.add(p_center);
		
		p_south.add(bt_prev);
		p_south.add(bt_next);
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		
		
		this.setSize(800,600);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == bt_prev) {
			prev();
		}
		
		if(obj==bt_next) {
			next();
			
		}
		updateData();
		
	}
	
	public void updateData() {
		can.setSrc(dir+src[n]);
		can.repaint();
		
		
	}
	
	public void prev() {
		if(n>=1) {
			n--;
		}else {
			JOptionPane.showMessageDialog(this,"더이상 전으로 갈수 없어요");
		}
		
	}
	
	public void next() {
		if(n<src.length-1) {
			n++;
		}else {
			JOptionPane.showMessageDialog(this,"더이상 전으로 갈수 없어요");
		}
	}

	
	
	
	
	
	
	public static void main(String[] args) {
		new GalleryApp2();
	}


}
