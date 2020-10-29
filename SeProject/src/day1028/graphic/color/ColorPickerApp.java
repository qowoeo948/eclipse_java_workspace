package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp extends JFrame implements MouseListener{
	JPanel p_red;
	JPanel p_orange;
	JPanel p_yellow;
	JPanel p_green;
	JPanel p_blue;
	JPanel p_navy;
	JPanel p_purple;
	JPanel p_north;
	JPanel p_center;
	
	 public ColorPickerApp() {

		 p_red = new JPanel();
		 p_orange = new JPanel();
		 p_yellow = new JPanel();
		 p_green = new JPanel();
		 p_blue = new JPanel();
		 p_navy = new JPanel();
		 p_purple = new JPanel();
		 p_north = new JPanel();
		 p_center = new JPanel();
		 
		p_red.setPreferredSize(new Dimension(100,100));
		p_red.setBackground(Color.RED);
		p_orange.setPreferredSize(new Dimension(100,100));
		p_orange.setBackground(Color.ORANGE);
		p_yellow.setPreferredSize(new Dimension(100,100));
		p_yellow.setBackground(Color.YELLOW);
		p_green.setPreferredSize(new Dimension(100,100));
		p_green.setBackground(Color.GREEN);
		p_blue.setPreferredSize(new Dimension(100,100));
		p_blue.setBackground(Color.BLUE);
		p_navy.setPreferredSize(new Dimension(100,100));
		p_navy.setBackground(Color.CYAN);
		p_purple.setPreferredSize(new Dimension(100,100));
		p_purple.setBackground(Color.MAGENTA);
		 
		 p_north.add(p_red);
		 p_north.add(p_orange);
		 p_north.add(p_yellow);
		 p_north.add(p_green);
		 p_north.add(p_blue);
		 p_north.add(p_navy);
		 p_north.add(p_purple);
		 
		 this.add(p_north,BorderLayout.NORTH);
		 this.add(p_center);
		 p_center.setBackground(Color.WHITE);
		 
		 
		 
		 p_red.addMouseListener(this);
		 p_orange.addMouseListener(this);
		 p_yellow.addMouseListener(this);
		 p_green.addMouseListener(this);
		 p_blue.addMouseListener(this);
		 p_navy.addMouseListener(this);
		 p_purple.addMouseListener(this);
		 
		 this.setSize(800,800);
		 this.setVisible(true);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object obj = e.getSource();

		if (obj == p_red) {
			p_center.setBackground(Color.RED);
		}else if(obj == p_orange) {
			p_center.setBackground(Color.ORANGE);
		}else if(obj == p_yellow) {
			p_center.setBackground(Color.YELLOW);
		}else if(obj == p_green) {
			p_center.setBackground(Color.GREEN);
		}else if(obj == p_blue) {
			p_center.setBackground(Color.BLUE);
		}else if(obj == p_navy) {
			p_center.setBackground(Color.CYAN);
		}else if(obj == p_purple) {
			p_center.setBackground(Color.MAGENTA);
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new ColorPickerApp();
	}

}
