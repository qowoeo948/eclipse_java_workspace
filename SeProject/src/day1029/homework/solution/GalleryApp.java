package day1029.homework.solution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GalleryApp extends JFrame implements ActionListener{
	JPanel p_west;
	JPanel p_center;
	JScrollPane scroll;
	JLabel la_name; //제목 나올 라벨
	XCanvas can; //센터에 크게 나올 이미지를 그릴 켄버스
	JPanel p_south;
	JButton bt_prev,bt_next;
	
	
	//썸네일 배열
	//Thumb[] list = new Thumb[10]; //[][][][][]공간만 생성
	//위에 대신 유연한 리스트로
	ArrayList<Thumb> list = new ArrayList<Thumb>();
	String dir="C:/Users/user/eclipse-workspace/SeProject/res/travel2/";
	String[] src = {
			"aa.jpg",
			"ab.jpg",
			"ax.jpg",
			"bm.jpg",
			"et.jpg",
			"kg.jpg",
			"mx.jpg",
			"pk.jpg",
			"ub.jpg",
			"ya.jpg"
		};
	
	int n=0; //배열의 index
	
	public GalleryApp() {
		super("자바 갤러리");
		p_west = new JPanel();
		p_center = new JPanel();
		scroll = new JScrollPane(p_west);
		la_name=new JLabel(src[n]+"("+(n+1)+"/10)",SwingConstants.CENTER);
		can = new XCanvas(dir+src[n]);
		p_south = new JPanel();
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		
		
		
		//썸네일 생성
		for(int i=0;i<src.length;i++) {
			Thumb thumb = null;
			list.add(thumb=new Thumb(dir+src[i],this));
			p_west.add(thumb);
		}
		
		la_name.setPreferredSize(new Dimension(700,50));
		la_name.setBackground(Color.RED);
		la_name.setFont(new Font("Verdana", Font.BOLD, 25));
		
		
		p_west.setPreferredSize(new Dimension(100,600));
		p_center.setPreferredSize(new Dimension(700,600));
		p_west.setBackground(Color.YELLOW);
		p_center.setBackground(Color.PINK);
		
		p_south.add(bt_prev);		
		p_south.add(bt_next);
		
		p_center.add(la_name);
		p_center.add(can);
		p_center.add(p_south);
		
		this.add(scroll,BorderLayout.WEST);
		this.add(p_center);
		
		//버튼과 리스너 연결
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		this.setSize(800,600);
		this.setVisible(true);
		//윈도우를 화면 중앙에 띄우는 법
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); //이벤트를 일으킨 컴포넌트
		if(obj == bt_prev) {
			prev();
		}else if(obj == bt_next) {
			next();
		}
		updateData();
	}
	
	public void updateData() {
		can.setSrc(dir+src[n]);
		can.repaint(); //update()-->paint()
		
		la_name.setText(src[n]+"("+(n+1)+"/"+src.length+")");
		
	}
	
	public void prev() {
		//그림은 XCanvas가 담당하므로, 그려질 img를 변경시켜주고, 다시 그리라고 하면 된다
		if(n>=1) {
			n--;
		}else {
			JOptionPane.showMessageDialog(this, "넘어갔어용");
		}
//				can.setSrc(dir+src[n]);
//				can.repaint(); //update()-->paint()
//				
//				la_name.setText(src[n]);
	}
	public void next() {
		//그림은 XCanvas가 담당하므로, 그려질 img를 변경시켜주고, 다시 그리라고 하면 된다
		
		//배열을 넘어서지 않는 범위내에서 ++허용
		if(n<src.length-1) {
			n++;
		}else {
			JOptionPane.showMessageDialog(this, "넘어갔어용");
		}
		//넘어서면 욕!!
		
		
//		can.setSrc(dir+src[n]);
//		can.repaint(); //update()-->paint()
//		
//		la_name.setText(src[n]);
	}
	
	
	public static void main(String[] args) {
		new GalleryApp();
	}

}
