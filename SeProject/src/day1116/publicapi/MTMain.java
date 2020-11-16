package day1116.publicapi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MTMain extends JFrame{
		//서쪽 영역
		JPanel p_west;
		JTextField t_name;
		JTextField op1;
		JTextField op2;
		JTextField op3;
		JButton bt;
		
		
		
		//센터영역
		Vector data = new Vector(); //이차원 백터가 될 예정
		//컬럼 제목 정보를 담는 벡터
		Vector<String> columnName = new Vector<String>();
		
		JTable table;
		MountainModel model;
		JScrollPane scroll;
		
		/*
		 * JTable에서 데이터 연동시 지금까지는 이차원 배열만 써왔는데, 사실 이차원 배열은
		 * 생성시 크기를 정해야 하기 때문에 불편한 점이 많다. 
		 * (불편했던 예) rs.last() 후 rs.getRow()로 데이터 총 수를 구하고, 다시 커서를 원상복귀 시킴..난리남..
		 * 따라서 컬렉션 프레임웍중 Vector를 지원하는 생성자를 이용해보자
		 * 
		 * */
	
	public MTMain() {
		init();
		
		
		p_west = new JPanel();
		t_name = new JTextField();
		op1 = new JTextField();
		op2 = new JTextField();
		op3 = new JTextField();
		bt = new JButton("검색");
		
		table = new JTable(data,columnName);
		scroll = new JScrollPane(table);
		
		p_west.setPreferredSize(new Dimension(200,600));
		p_west.setBackground(Color.WHITE);
		t_name.setPreferredSize(new Dimension(190,30));
		op1.setPreferredSize(new Dimension(190,30));
		op2.setPreferredSize(new Dimension(190,30));
		op3.setPreferredSize(new Dimension(190,30));

		p_west.add(t_name);
		p_west.add(op1);
		p_west.add(op2);
		p_west.add(op3);		
		p_west.add(bt);
		
		add(p_west,BorderLayout.WEST);
		add(scroll);
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
			}
		});
	
		
		setSize(900,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	
	}
	
	//벡터값을 초기화 하자, 이 메서드를 생성자에 호출
	public void init() {
		//데이터 1건 넣어보기
		
		Vector v = new Vector();
		v.add("1");
		v.add("설악산");
		v.add("강원도");
		v.add("3000");
		
		data.add(v);
		
		//컬럼정보 채우고
		columnName.add("ID"); //산 아이디
		columnName.add("산이름"); //산이름
		columnName.add("소재지"); //산 소재지
		columnName.add("높이"); //산 높이
	}

	
	public static void main(String[] args) {
		new MTMain();
	}
}
