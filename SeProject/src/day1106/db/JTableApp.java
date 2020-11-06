/*
 * JTable 더 자세히 알아보기
 * JTable은 MVC패턴을 적용한 컴포넌트이다.
 * */
package day1106.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableApp extends JFrame{
	JTable table; //MVC에서 V(VIEW)이다
	JScrollPane scroll;
	MyModel  model=new MyModel();
	
	public JTableApp() {
		table = new JTable(model); //JTable과 컨트롤러인 MyModel연결
		scroll = new JScrollPane(table);
		
		this.add(scroll);
		
		this.setSize(400,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
	new JTableApp();	
	}
}
