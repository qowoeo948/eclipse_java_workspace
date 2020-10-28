package day1028.graphic.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum extends JFrame implements ActionListener{
	AlbumPanel p; //paint메서드를 재정의하려면, 클래스로 정의해야 한다..

	JPanel p_south; 
	JButton bt_prev, bt_next;
	
	public PhotoAlbum() {
		p = new AlbumPanel();
		p.setBackground(Color.YELLOW);
		p_south = new JPanel();
		bt_prev = new JButton("이전사진");
		bt_next = new JButton("다음사진");
		
		this.add(p); //중앙에 앨범패널 넣기
		p_south.add(bt_prev);		
		p_south.add(bt_next);		
		this.add(p_south,BorderLayout.SOUTH);
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setSize(500,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		//다음 버튼이면 AlbumPanel.n을 증가시킨다 이전버튼이면 AlbumPanel.n을 감소시킨다
		
		//이벤트를 일으킨 이벤트 소스 구하기
		//이벤트소스란? 이벤트를 일으킨 컴포넌트를 의미~!
		Object obj = e.getSource();

		if (obj == bt_prev) {
			p.n--;
		} else if (obj == bt_next) {
			p.n++;
		}
		//화면 갱신 (직접 AlbumPanel의 paint()호출 불가
		//갱신하도록 요청!!
		//repaint!!
		p.repaint();
	}
	
	public static void main(String[] args) {
		new PhotoAlbum();
	}
}
