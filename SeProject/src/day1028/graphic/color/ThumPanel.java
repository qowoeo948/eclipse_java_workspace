package day1028.graphic.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

//나만의 패널 정의하기 - 기존 패널의 커스터마이징 
public class ThumPanel extends JPanel implements MouseListener{
	JPanel p_center;
	Color color;
	
	//너비와, 높이와 생삭을 가진 패널로 태어나게
	public ThumPanel(JPanel p_center,Color color) {
		this.p_center = p_center;
		this.color = color;
		this.setPreferredSize(new Dimension(100,100));
		this.setBackground(color);
		this.addMouseListener(this);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {		
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//센터영역의 패널의 배경색을 나(핸재패널)와 같은 색상으로 설정하자!!
		p_center.setBackground(this.color);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	

}
