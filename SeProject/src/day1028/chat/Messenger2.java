package day1028.chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Messenger2 extends JFrame implements KeyListener {
	// is a //is a
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	Button bt;
	JPanel p_south;
	Messenger ch;

	public Messenger2(Messenger ch) {
		this.ch = ch;

		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt = new Button("SEND");
		t_input = new JTextField(20);
		p_south = new JPanel();

		// 혼자해봤는데 필요없네...?
		// this.setLayout(new BorderLayout());

		// 패널 조립 (패널은 디폴트가 FlowLayout)
		p_south.add(t_input);
		p_south.add(bt);

		this.add(scroll);
		this.add(p_south, BorderLayout.SOUTH);

		area.setBackground(Color.GREEN);
		t_input.setBackground(Color.PINK);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);

		t_input.setPreferredSize(new Dimension(265, 30));

		// 보여주기 전에 미리 연결해놓자. 컴포넌트와 리스너 연결

		t_input.addKeyListener(this);// 프레임이 곧 리스너다!!

		// this.setSize(300, 400);
		setBounds(500, 150, 300, 400);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 눌렀다 떼는거
		// 엔터키를 누르면, area에 입력 데이터를 반영하자!!(누적 시키자)
		int key = e.getKeyCode(); // 키 코드값 반환
		if (key == 10) {
			send();
		}
	}

	public void send() {
		String msg = t_input.getText();// 텍스트필드 값을 구하자!!
		// 나에 대한 처리
		area.append(msg + "\n");
		t_input.setText("");
		// 너에 대한 처리
		ch.area.append(msg + "\n");
		ch.t_input.setText("");

	}

}
