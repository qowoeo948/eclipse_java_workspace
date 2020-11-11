package day1111.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardWrite extends JPanel{
	JTextField t_title;
	JTextField t_writer;
	JTextArea content;
	JScrollPane scroll;
	JButton bt_regist;
	JButton bt_list;
	BoardApp boardApp;
	
	public BoardWrite(BoardApp boardApp) {
		this.boardApp = boardApp;
		t_title = new JTextField();
		t_writer = new JTextField();
		content = new JTextArea();
		scroll = new JScrollPane(content);
		bt_regist = new JButton("글등록");
		bt_list = new JButton("목록보기");
		
		t_title.setPreferredSize(new Dimension(780,35));
		t_writer.setPreferredSize(new Dimension(780,35));
		scroll.setPreferredSize(new Dimension(780,300));
		
		//조립
		add(t_title);
		add(t_writer);
		add(scroll);
		add(bt_regist);
		add(bt_list);
		
		setPreferredSize(new Dimension(780,500));
		setVisible(true);
		
		//목록으로 가기 구현
		bt_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardApp.setPage(BoardApp.BOARD_LIST);
			}
		});
	}

}
