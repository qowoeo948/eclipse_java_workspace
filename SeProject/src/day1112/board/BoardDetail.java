package day1112.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardDetail extends JPanel{
	JTextField t_title;
	JTextField t_writer;
	JTextArea content;
	JScrollPane scroll;
	JButton bt_edit;
	JButton bt_del;
	JButton bt_list;
	BoardApp boardApp;
	Connection con;
	Board board=null; //수정이나 삭제시에도 이 안에 들어있는 정보들을 활용하기 위해 멤버변수로
	
	public BoardDetail(BoardApp boardApp) {
		this.boardApp = boardApp;
		con = boardApp.getCon();
		
		t_title = new JTextField();
		t_writer = new JTextField();
		content = new JTextArea();
		scroll = new JScrollPane(content);
		bt_edit = new JButton("수정하기");
		bt_del = new JButton("삭제하기");
		bt_list = new JButton("목록보기");
		
		t_title.setPreferredSize(new Dimension(780,35));
		t_writer.setPreferredSize(new Dimension(780,35));
		scroll.setPreferredSize(new Dimension(780,300));
		
		//조립
		add(t_title);
		add(t_writer);
		add(scroll);
		add(bt_edit);
		add(bt_del);
		add(bt_list);
		
		setPreferredSize(new Dimension(780,500));
		setVisible(true);
		
		bt_list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boardApp.setPage(BoardApp.BOARD_LIST);
				
			}
		});
	}
	
	//한건 가져오기
	public void getDetail(int board_id) {
		
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select *from board where board_id="+board_id;
		System.out.println(sql);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//커서를 한칸 전진
			if(rs.next()) { //레코드가 있다면
				board = new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				
				//데이터 채우기
				t_title.setText(board.getTitle());
				t_writer.setText(board.getWriter());
				content.setText(board.getContent());
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

}
