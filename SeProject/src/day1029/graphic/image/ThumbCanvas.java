package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//이미지 썸네일에 사용할 캔버스~
public class ThumbCanvas extends Canvas implements MouseListener{
	Toolkit kit;
	Image img;
	DetailPanel p_center;
	
	public ThumbCanvas(String path, DetailPanel p_center) {
		kit=Toolkit.getDefaultToolkit();  
		img = kit.getImage(path);
		this.setPreferredSize(new Dimension(100,100));
		this.p_center = p_center;
		
		//마우스 리스너와 연결
		this.addMouseListener(this);
		  
	  }
	
	//도화지에 그림을 그리자! 모든 컴포넌트는 스스로 그림의 주체이자, 그려질 대상이기도 하다
	@Override
	public void paint(Graphics g) {
		g.drawImage(img,0,0,this);
		
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e){
		//p_center패널에, 이미지를 그리되, 현재 나의 이미지를 가지고
		//p_center에게 나의 img를 전달해야 한다!
		p_center.setImg(img);
		p_center.repaint(); //다시그려라-->update()화면지우기->paint()그리기
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}

}
