package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerTcher extends JFrame{
	
	JPanel p_north;
	JPanel p_center;
	ThumPanel[] thumb = new ThumPanel[7];
	//7가지 색상을 배열로 보유하자
	Color[] colorArray= {
			Color.RED,
			Color.ORANGE,
			Color.YELLOW,
			Color.GREEN,
			Color.BLUE,
			Color.CYAN,
			Color.PINK
			};
		
	public ColorPickerTcher() {
		p_north = new JPanel();
		p_center = new JPanel();
		
		for(int i=0;i<thumb.length;i++) {			
			thumb[i]=new ThumPanel(p_center,colorArray[i]);
			p_north.add(thumb[i]);
		}
		p_center.setBackground(Color.WHITE);
		this.add(p_north,BorderLayout.NORTH);
		this.add(p_center);

		this.setSize(770,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ColorPickerTcher();
	}

}
