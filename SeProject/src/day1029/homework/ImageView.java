package day1029.homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import day1028.graphic.album.AlbumPanel;
import day1029.graphic.image.DetailPanel;
import day1029.graphic.image.ThumbCanvas;

public class ImageView extends JFrame{
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
	JPanel p_south;
	JPanel p_west;
	JPanel p_center;
	JButton bt_prev,bt_next;	
	ThumbNail [] thumb = new ThumbNail[path.length];
	
	public ImageView() {
		p_north = new JPanel();
		p_south = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();
		bt_prev = new JButton("◀");
		bt_next = new JButton("▶");
		
		this.add(p_north,BorderLayout.NORTH);
		this.add(p_south,BorderLayout.SOUTH);
		this.add(p_west,BorderLayout.WEST);
		this.add(p_center);
		p_center.setBackground(Color.PINK);
		p_west.setBackground(Color.ORANGE);
		p_north.setBackground(Color.RED);
		p_south.setBackground(Color.YELLOW);
		
		p_south.add(bt_prev);
		p_south.add(bt_next);
		
		for(int i=0;i<thumb.length;i++) {
			thumb[i]=new ThumbNail(dir+path[i]);
			p_west.add(thumb[i]);
		}
		
		
		this.setSize(800,800);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new ImageView();
		
	}
}
