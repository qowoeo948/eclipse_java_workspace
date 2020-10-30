package day1029.homework;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ThumbNail extends JPanel{
	Toolkit kit;
	Image img;
	
	public ThumbNail(String path) {
		kit=Toolkit.getDefaultToolkit();  
		img = kit.getImage(path);
		this.setPreferredSize(new Dimension(100,100));
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

}
