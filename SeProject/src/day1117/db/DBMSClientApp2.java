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
package day1117.db;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;



public class DBMSClientApp2 extends JFrame{
	JPanel p_west; //서쪽 영역 패널
	Choice ch_users;
	JPasswordField t_pass;
	JButton bt_login;
	
	JPanel p_center; //그리드가 적용될 패널
	JPanel p_upper; //테이블과 시퀀스를 포함할 패널(그리드 레이아웃 예정)

	JPanel p_middle; //SQL편집기가 위치할 패널
	JTextArea area; //쿼리 편집기
	JButton bt_execute;//쿼리문 실행버튼
	
	
	JPanel p_footer; // 하단의 그리드 (1,2)
	
	
	JTable t_tables; //유저의 테이블 정보를 출력할 JTable
	JTable t_seq; //유저의 시퀀스 정보를 출력할 JTable
	
	JTable t_record; //유저가 선택한 테이블의 레코드를 출력할 JTable
	
	JTable t_column; //유저가 선택한 테이블의 구조를 출력할 JTable
	
	JScrollPane s1,s2,s3,s4,s5; 
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="system";
	String password="manager";
	
	Connection con;
	
	//테이블을 출력할 벡터, 및 컬럼
	Vector tableList = new Vector(); //이 벡터안에는 추후 또다른 벡터가 들어갈 예정 , 즉 이차원배열과 동일함
													//단, 이차원배열보다는 크기가 자유로워서 유연하고 코딩이 편하다
	Vector<String> tableColumn = new Vector<String>(); //컬럼명은 당연 String
	
	
	//시퀀스에 필요한 백터들
	Vector seqList = new Vector(); 
	Vector<String> seqColumn = new Vector<String>(); 
	
	//테이블 컬럼정보에 필요한 벡터들
	Vector columnList = new Vector();
	Vector<String> columnType = new Vector<String>();
		
	
	MyTableModel model;
		
	public DBMSClientApp2() {
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("접속");
		
		p_center = new JPanel();
		p_upper = new JPanel();
		p_middle = new JPanel();
		area = new JTextArea();
		bt_execute = new JButton("쿼리실행");
		
		p_footer = new JPanel();
		
		
		p_center.setLayout(new GridLayout(3,1));
		p_upper.setLayout(new GridLayout(1,2));
		p_middle.setLayout(new BorderLayout());
		p_footer.setLayout(new GridLayout(1,2));
		
		//테이블 생성보다 위에 올라가있어야돼
		//컬럼정보 초기화 하기
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		t_tables = new JTable(tableList,tableColumn); //완성된 이차원 백터를 JTable에 반영해야함. 생성자의 인수로 넣어야함!!

		//시퀀스의 생성자에 백터 적용하기
		seqColumn.add("sequence_name");
		seqColumn.add(" last_number");
		t_seq = new JTable(seqList,seqColumn);
		
		columnType.add("컬럼명");
		columnType.add("데이터타입");
		t_column = new JTable(columnList,columnType);
		
		s1 = new JScrollPane(t_tables);
		s2 = new JScrollPane(t_seq);
		s3 = new JScrollPane(area);
		s5 = new JScrollPane(t_column);
		
		//선택한 테이블의 레코드 보여줄 JTable생성
		t_record = new JTable(null); //MyTableModel을 대입할 예정
		s4 = new JScrollPane(t_record);
		
		p_west.setPreferredSize(new Dimension(150,350));
		ch_users.setPreferredSize(new Dimension(145,30));
		t_pass.setPreferredSize(new Dimension(145,30));
		bt_login.setPreferredSize(new Dimension(145,30));
		area.setFont(new Font("Arial Black",Font.BOLD,20));
		
		
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
		p_upper.add(s1);
		p_upper.add(s2);
		p_middle.add(s3);
		p_middle.add(bt_execute,BorderLayout.SOUTH);
		p_footer.add(s4);
		p_footer.add(s5);

		p_center.add(p_upper); //그리드의 1층
		p_center.add(p_middle); //그리드의 2층
		p_center.add(p_footer); //그리드의 3층
		
		add(p_west,BorderLayout.WEST);
		add(p_center);
	
		
		
		setSize(900,750);
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
		
		//테이블과 리스너 연결, 눌렀을 때 상세한 것들 밑에 뜨게
		t_tables.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//선택한 좌표의 테이블명 얻기
				
				//선택한 row구하기
				int row = t_tables.getSelectedRow();
				int col = t_tables.getSelectedColumn();
				
				String tableName=(String)t_tables.getValueAt(row, col);
//				System.out.println(t_tables.getValueAt(row, col));
				tableName=tableName.toLowerCase();
				
				//구해진 테이블명을 select()메서드의 인수로 넘기자!!
				select(tableName);
				//JTable갱신
				t_record.updateUI();
				//System.out.println("당신이 선택한 "+tableName+" 테이블의 컬럼수는 "+t_record.getColumnCount());
				
			
			}
		});
		
		bt_execute.addActionListener((e)->{
			select(null); //테이블명을 넘기지 않음
		});
		
		connect();
		getUserList(); //유저목록 구해오기

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
	
	//유저가 선택한 테이블의 레코드 가져오기
	//이 메서드를 호출하는 자는 select문의 매개변수로 테이블명을 넘겨야 한다.
	//매개변수가 넘어오면, 테이블명만 사용하고, 안넘어오면 전체 SQL문 대체하자
	public void select(String tableName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=null;
		
		if(tableName!=null) {
			sql = "select *from "+tableName;			
		}else {
			sql=area.getText();
		}
		
//		System.out.println(sql);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//컬럼 정보 만들기 위한 코드
			Vector column = new Vector(); //이 벡터는 새로운 TableModel객체의 인스턴스가 가진 컬럼백터에 대입될 예정
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount(); 
			
			System.out.println("당신이 선택한 "+tableName+" 테이블의 컬럼수는 "+columnCount);
			
			//컬럼 정보 출력
			//출력만 하지 말고, MyTableModel이 보유한 컬럼용 벡터에 정보를 채워넣자!!
			for(int i=1;i<=columnCount;i++) {
				//System.out.println(meta.getColumnName(i)); //컬럼 정보가 다 나와준다.
				column.add(meta.getColumnName(i));
			}
			
			Vector record = new Vector();
			
			//rs를 어디에 담아야 될까..? (생성자 벡터 방식이 아닌 TableModel 방식을 이용하고 있다)
			//TableModel인 MyTableModel이 보유한 벡터에 담으면 된다.
			while(rs.next()) {
				Vector vec = new Vector(); //비어있는 일차원 백터
				
				//rs도 일정의 배열이므로, index로 컬럼을 접근 할 수 있다.
				//주의 ) 1부터 시작이다 .. 만든자가 1부터래....
				//			1부터 몇까지 컬럼이 존재하는지 알 수가 없다.
				//-- 이럴땐 테이블에 대한 메타정보를 가져오면 된다.
				//가져오는 법!!!!! 위에 있어 meta!!!
				for(int i=1;i<=meta.getColumnCount();i++) {
					vec.add(rs.getString(i));
					//MyTableModel이 보유한 벡터에 추가하자
				}
				record.add(vec);
			}
			//데이터를 담은 이차원 벡터와, 컬럼을 담은 일차원 백터를 새로운 모델객체를 생성하면서 전달하자!
			model = new MyTableModel(record,column);
			//테이블에 모델을 생성자가 아닌, 메서드로 적용하자!
			t_record.setModel(model);
			
			getColumnType(meta);
			
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
	
	//유저가 선택한 테이블의 구조 정보 가져오기
	public void getColumnType(ResultSetMetaData meta) {
	try {
		int total = meta.getColumnCount(); //총 컬럼수
		
		//멤버변수로 선언된 벡터에 버튼을 누를때마다 계쏙 누적이 되므로,
		//아래의 for문이 수행되기 전에, 먼저 비워놓고 채우자
		columnList.removeAll(columnList);
		
		for(int i=1;i<=total;i++) {
			System.out.println("컬럼명 "+meta.getColumnName(i)+"("+meta.getColumnTypeName(i)+")");
			Vector vec = new Vector();
			vec.add(meta.getColumnName(i));
			vec.add(meta.getColumnTypeName(i));
			
			columnList.add(vec);
		}
		t_column.updateUI();
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
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
		getSeqList();
		
		System.out.println("보유한 테이블 "+tableList.size());
		
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
			
			//테이블의 레코드와 컬럼갯수 확인 (여전히 0인지 체크)
			System.out.println("컬럼 수 는?"+t_tables.getColumnCount());
			
			
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
	
	
	//시퀀스 가져오기
	public void getSeqList() {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql=" select sequence_name, last_number from user_sequences";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Vector vec = new Vector(); //레코드를 담을 백터준비
				vec.add(rs.getString("sequence_name"));
				vec.add(rs.getString("last_number"));
				//기존 시퀀스 백터에 추가해서 이차원 백터로 만들자
				seqList.add(vec);
			}
			
			//만들어진 백터를 테이블에 반영한 결과를 업데이트
			t_seq.updateUI();
		
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
		new DBMSClientApp2();
	}

}
