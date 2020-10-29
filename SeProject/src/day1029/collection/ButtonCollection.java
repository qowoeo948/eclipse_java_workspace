package day1029.collection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonCollection extends JFrame implements ActionListener{
	JPanel p_north;
	JPanel p_center;
	JButton bt_create;
	JButton bt_bg;
	//배열은 크기를 정해야 하기 때문에, 크기를 알 수 없는 프로그램에서는 사용에 제한이 있다.
	//JButton[] btn = new JButton[];//배열의 가장 큰 특징, 배열은 생성시 반드시 크기를 명시해야함
	ArrayList<JButton> btn = new ArrayList<JButton>(); //크기가 0이다.하지만 늘어난다
	
	
	public ButtonCollection() {
		p_north = new JPanel();
		p_center = new JPanel();
		bt_create = new JButton("생성");
		bt_bg = new JButton("배경색");
	
		
		p_north.add(bt_create);
		p_north.add(bt_bg);
		this.add(p_north,BorderLayout.NORTH);
		this.add(p_center);
		
		bt_create.addActionListener(this);
		bt_bg.addActionListener(this);
		
		
		this.setSize(500,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =  e.getSource();
		//이벤트를 일으킨 컴포넌트를 반환!
		
		if (obj == bt_create) {
			create();	
		} else if (obj.equals(bt_bg)) {
			setBg();
		}
	}
	public void create() {
		//버튼 생성하여 p_center에 부착
		JButton bt = new CustomButton();
		p_center.add(bt);
		
		//리스트에 추가하자
		btn.add(bt);
		System.out.println("현재까지 누적된 리스트의 수는 "+btn.size());
		bt.setText("버튼"+Integer.toString(btn.size()));
		
		//p_center에 버튼을 그린게 아니라, 버튼을 추가한것이다.
		//따라서 이때는 p_center를 갱신하면 된다.. updateUI()
		p_center.updateUI();
	}
	public void setBg() {
		//btn 리스트에 들어있는 모든 요소를 대상으로 배경색 바꾸기!!
		//ArrayList는 순서있는 집합이므로, for문을 사용할 수 있다.
		for(int i=0;i<btn.size();i++) {
			JButton bt = btn.get(i);//리스트에서 끄집어 내기
			bt.setBackground(Color.YELLOW);
		}
	}
	
	public static void main(String[] args) {
		new ButtonCollection();
	}
		
}
