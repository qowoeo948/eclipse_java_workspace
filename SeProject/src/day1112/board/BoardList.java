//게시물 목록 페이지
package day1112.board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BoardList extends JPanel{
	JTable table;
	JScrollPane scroll;
	JButton bt;
	BoardApp boardApp;
	BoardModel boardModel;
	Connection con;
	
	public BoardList(BoardApp boardApp) {
		this.boardApp = boardApp;
		con = boardApp.getCon();
	
		table = new JTable(boardModel = new BoardModel());
		scroll = new JScrollPane(table);
		bt = new JButton("글등록");
		
		setLayout(new BorderLayout());
		add(scroll);
		add(bt,BorderLayout.SOUTH);
		
		getList();
		
		setPreferredSize(new Dimension(780,500));
		setVisible(true);
		
		table.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent e) {
			//상세보기가 보유한 getDetail()메서드 호출하기!!
		BoardDetail boardDetail=(BoardDetail)boardApp.getPages(BoardApp.BOARD_DETAIL);
		String board_id = (String)table.getValueAt(table.getSelectedRow(), 0); //board_id
		boardDetail.getDetail(Integer.parseInt(board_id));
		boardDetail. updateHit(Integer.parseInt(board_id));
		
		boardApp.setPage(BoardApp.BOARD_DETAIL); //상세보기
		}
		
		});
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//글쓰기 폼의 작성자에 아이디 넣어두기
				BoardWrite page=(BoardWrite) boardApp.getPages(BoardApp.BOARD_WRITE);
				page.t_writer.setText(boardApp.getBoardMember().getM_id());
				
				//글쓰기 폼 보여주기 BoardWrite
				boardApp.setPage(BoardApp.BOARD_WRITE);
			}
		});
		
	}
	
	//게시물 가져오기
	//rs에 담겨진 데이터를 TableModel이 보유한 이차원 배열 data에 대입
	public void getList() {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select *from board order by board_id desc";
		
		try {
			pstmt = con.prepareStatement(sql
					,ResultSet.TYPE_SCROLL_INSENSITIVE
					,ResultSet.CONCUR_READ_ONLY);
			rs= pstmt.executeQuery();
			
			rs.last(); //커서를 제일 마지막으로 보내기
			int total = rs.getRow();
			
			String[][]data = new String[total][boardModel.column.length];
			rs.beforeFirst(); //이건 맨꼭대기 rs.First()는 꼭대기에서 밑에
			
			int index=0;
			while(rs.next()) {			
			String[] record = new String[boardModel.column.length];
			
			record[0]= rs.getString("Board_id"); //그냥 String취급할수도 있어
			record[1]= rs.getString("title");
			record[2]= rs.getString("writer");
			record[3]= rs.getString("regdate");
			record[4]= rs.getString("hit");
			
			//채워진 일차원 배열을 data이차원배열에 순서대로 담자
			data[index++]=record;
		}
		//완성된 이차월 배열을 boardModel이 보유한 data배열에 주소로 대입시켜버리자
			boardModel.data = data;
		
		//JTable 다시 ui갱신
			table.updateUI();
			
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
