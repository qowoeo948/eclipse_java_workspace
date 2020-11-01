package day1101.review;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Thumb2 extends JPanel implements MouseListener{
	Toolkit kit;
	Image img;
	String src;
	GalleryApp2 galleryApp;
	
	public Thumb2(String src,GalleryApp2 galleryApp) {
		this.galleryApp = galleryApp;
		this.src= src;
		
		kit=Toolkit.getDefaultToolkit();
		img = kit.getImage(src);
		img=img.getScaledInstance(75, 55, Image.SCALE_SMOOTH);
		
		setPreferredSize(new Dimension(75,55));
		
		this.addMouseListener(this);
	
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
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

		//n을 지금 나의 .index값으로 바꾸자
		//현재 패널이 ArrayList내에서의 몇번째 인덱스에 들어있는지 역추적
		galleryApp.n=galleryApp.list.indexOf(this);
		galleryApp.updateData();
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
