package day1029.graphic.image;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import day1028.graphic.color.ThumPanel;

public class ImagePicker extends JFrame{
	String dir="C:/Users/user/eclipse-workspace/SeProject/res/travel2/";
	String[] path = {
			"aa.jpg",
			"ab.jpg",
			"ax.jpg",
			"bm.jpg",
			"et.jpg",
			"kg.jpg",
			"mx.jpg"
		};
	
	JPanel p_north;	
	DetailPanel p_center;
	ThumbCanvas [] thumb = new ThumbCanvas[path.length];
	
	public ImagePicker() {
		p_north = new JPanel();
		p_center = new DetailPanel();
		
		//캔버스 생성 및 조립
		for(int i=0;i<thumb.length;i++) {
			thumb[i]=new ThumbCanvas(dir+path[i],p_center);
			p_north.add(thumb[i]);
		}
		
		this.add(p_north,BorderLayout.NORTH);
		this.add(p_center);
		
		this.setSize(800,600);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ImagePicker();
	}
	
}
