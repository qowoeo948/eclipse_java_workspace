package day1101.review;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class XCanvas2 extends Canvas{
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Image img;
	private String src;
	
	
	public XCanvas2(String src) {
	img = kit.getImage(src);
	img = img.getScaledInstance(660,450, Image.SCALE_SMOOTH);
	setPreferredSize(new Dimension(660,450));
	
	}
	
	public void setSrc(String src) {
		this.src = src;
		img = kit.getImage(src);
		img = img.getScaledInstance(660,450, Image.SCALE_SMOOTH);
	}
	public String getSrc() {
		return src;
	}
	
	
	
	@Override
	public void paint(Graphics g) {
	g.drawImage(img, 0, 0, this);
	}

}
