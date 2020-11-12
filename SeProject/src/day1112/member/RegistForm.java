package day1112.member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import day1112.board.BoardApp;

public class RegistForm extends JPanel{
	JPanel p_container; //묶기위한 컨테이너
	JTextField t_id;
	JPasswordField t_pass;
	JTextField t_name;
	JButton bt;
	JButton bt_login;
	BoardApp boardApp;
	Connection con;
	
	public RegistForm(BoardApp boardApp) {
		this.boardApp = boardApp;
		con = boardApp.getCon(); //다른 패키지의 변수 접근  
		
		p_container = new JPanel();
		t_id = new JTextField(30);
		t_pass = new JPasswordField(30);
		t_name = new JTextField(30);
		bt = new JButton("가입");
		bt_login = new JButton("로그인");
		
		
		p_container.setBackground(Color.YELLOW);
		p_container.setPreferredSize(new Dimension(400,200));
		
		p_container.add(t_id);
		p_container.add(t_pass);
		p_container.add(t_name);
		p_container.add(bt);
		p_container.add(bt_login);
		
		add(p_container);
		
		setVisible(true);
		
		//가입버튼과 리스너 연결
		bt.addActionListener((e)->{
			regist();
		});
		bt_login.addActionListener((e)->{
			boardApp.setPage(BoardApp.MEMBER_LOGIN);

		});
		
	}
	
	//퀴리문 실행
	public void 	regist() {
		PreparedStatement pstmt=null;
		
		String sql = "insert into board_member(member_id,m_id,m_pass,m_name)";
		sql+=" values(seq_board_member.nextval, ?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql); //쿼리 실행할 준비
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, new String(t_pass.getPassword()));//배열을 스트링으로..
			pstmt.setString(3, t_name.getText());
			
			//DML의 경우 executeUpdate(), selecet는 executeQuery
			//DML의 경우, 이 쿼리수행에 의해 영향을 받은 레코드 수가 반환되므로 만일 0이면 실패로판단
			int result = pstmt.executeUpdate();
			if(result==0) {
				JOptionPane.showMessageDialog(this, "가입에 실패하였습니다");
			}else {
				JOptionPane.showMessageDialog(this, "가입에 성공하셨습니다♥");			
			}
		} 
		 catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
			
	
		
	}


}














