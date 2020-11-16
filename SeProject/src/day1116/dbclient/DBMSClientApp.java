/*
 * 딕셔너리를 학습하기 위해 데이터베이스 접속 클라이언트를 자바로 만들기
 * 실무에서는 SQLPlus를 잘 사용하지 않음.. 이유) 업무 효율성이 떨어지기 때문에
 * 		그럼? 실무현장에서는 개발자의 pc에는 각종 개발툴들이 있지만, 실제적인 운영 서버에는
 * 		보안상 아무것도 설치해서는 안된다. 따라서 서버에는 툴들이 없기 때문에 만일 오라클 유지보수하러 파견을
 * 		나간 경우, 콘솔창 기반으로 쿼리를 다뤄야할 경우가 종종 있다..이때 SQLPlus를 써야함

 * 개발자들이 개발할때 데이터베이스를 다루는 툴을 "데이터베이스 접속 클라이언트"라고 부른다.
 * 이러한 툴 들중 유명한 제품은 Toad, 등이 있다.. (유료)
 * Toad는 DBeaver에 비해 기능이 막강하지만 유료이기에 우리는 DBeaver를 사용중인것이다.
 * */
package day1116.dbclient;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DBMSClientApp extends JFrame{
	JPanel p_west; //서쪽 영역 패널
	Choice ch_users;
	JPasswordField t_pass;
	JButton bt_login;
	
	JPanel p_center; //그리드가 적용될 패널
	JTable t_tables; //유저의 테이블 정보를 출력할 JTable
	JTable t_seq; //유저의 시퀀스 정보를 출력할 JTable
	JScrollPane s1,s2; 
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="system";
	String password="manager";
	
	Connection con;
	
	//테이블을 출력할 벡터, 및 컬럼
	Vector tableList = new Vector(); //이 벡터안에는 추후 또다른 벡터가 들어갈 예정 , 즉 이차원배열과 동일함
													//단, 이차원배열보다는 크기가 자유로워서 유연하고 코딩이 편하다
	Vector<String> columnList = new Vector<String>(); //컬럼명은 당연 String
	
	
	public DBMSClientApp() {
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("접속");
		
		p_center = new JPanel();
		p_center.setLayout(new GridLayout(2,1));
		
		t_tables = new JTable(tableList,columnList); //완성된 이차원 백터를 JTable에 반영해야함. 생성자의 인수로 넣어야함!!
		t_seq = new JTable();
		s1 = new JScrollPane(t_tables);
		s2 = new JScrollPane(t_seq);
		
		p_center.add(s1);
		p_center.add(s2);
		
		
		p_west.setPreferredSize(new Dimension(150,350));
		ch_users.setPreferredSize(new Dimension(145,30));
		t_pass.setPreferredSize(new Dimension(145,30));
		bt_login.setPreferredSize(new Dimension(145,30));
		
		
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
		
		add(p_west,BorderLayout.WEST);
		add(p_center);
	
		
		
		setSize(600,450);
		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//프로세스만 종료시켜 버리므로, 오라클 , 스트림 닫는 처리를 할 기회를 잃어버리게 된다.
		//따라서 윈도우 어댑터 구현하여 닫을게 있다면 닫는 처리를 하자!
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disConnect();//시스템 종료전에 닫을 자원이 있을 때 먼저 닫으려고 호출함!
				System.exit(0);
			}
			
		});
		setLocationRelativeTo(null);
		
		bt_login.addActionListener((e)->{
			login(); //선택한 유저로 로그인 시도
			
		});
		
		
		
		connect();
		getUserList(); //유저목록 구해오기
		
		//컬럼정보 초기화 하기
		columnList.add("table_name");
		columnList.add("tablespace_name");
		
		

	}
	
	//유저목록 가져오기
	public void getUserList() {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select username from dba_users order by username asc";
		
		//쿼리문 준비하기
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			//rs에 들어있는 유저정보를 Choice에 출력
			while(rs.next()) {
				ch_users.add(rs.getString("username"));
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	//로그인 하기
	public void login() {
		//현재 유지되고 있는 접속 객체인 Connection을 끊고, 다른 유저로 접속을 시도한다!
		disConnect(); //접속 끊고
		user = ch_users.getSelectedItem(); //현재 초이스컴포넌트에서 선택된 아이템 값!
		password =new String( t_pass.getPassword()); //비밀번호 설정
		
		connect(); //접속하기 (하지만 멤버변수가 현재 system으로 되어 있으므로 멤버변수를 초이스 값으로 교체 해줘야함
		getTableList();
		
//		System.out.println("보유한 테이블 "+tableList.size());
		
	}
	
	//현재 접속 유저의 테이블목록 가져오기
	public void getTableList() {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select table_name,tablespace_name from user_tables";
		try {
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//평소였으면 이차원 배열 선언 후 last(), getRow()등 아주 성가셨으나 백터를 이용하면 그런게 필요없음
			
	
			while(rs.next()) {
				Vector vec = new Vector(); //tableList벡터에 담겨질 백터
				vec.add(rs.getString("table_name"));
				vec.add(rs.getString("tablespace_name"));
				
				tableList.add(vec); //멤버변수 백터에 백터를 담았으니, 이제 멤버변수 백터는 이차원 백터가 됨
			}
			
			t_tables.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	//오라클에 접속하기
	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password); //접속시도
			if(con==null) {
				JOptionPane.showMessageDialog(this, "접속실패");
			}else {
				this.setTitle(user+"계정 접속중☆");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//오라클 접속 끊기
	public void disConnect() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	

	public static void main(String[] args) {
		new DBMSClientApp();
	}

}
