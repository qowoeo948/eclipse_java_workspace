package day1030.chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatB extends JFrame implements KeyListener, ActionListener {
	// is a //is a
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	Button bt;
	JPanel p_south;
	JButton bt_open; // 대화 상대방을 띄우는 버튼
	private ChatA chatA;
	private ChatC chatC;
	
	

	public ChatB() {
		// 나보다 부모가 먼저 태어나야 함. super(), <- JFrame("부모창")
		super("부모창");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt = new Button("SEND");
		t_input = new JTextField(10);
		p_south = new JPanel();
		bt_open = new JButton("OPEN");

		// 혼자해봤는데 필요없네...?
		// this.setLayout(new BorderLayout());

		// 패널 조립 (패널은 디폴트가 FlowLayout)
		p_south.add(t_input);
		p_south.add(bt);
		p_south.add(bt_open);

		this.add(scroll);
		this.add(p_south, BorderLayout.SOUTH);

		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.PINK);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);

		t_input.setPreferredSize(new Dimension(200, 30));

		// 보여주기 전에 미리 연결해놓자. 컴포넌트와 리스너 연결

		t_input.addKeyListener(this);// 프레임이 곧 리스너다!!

		// send버튼에 리스너 연결
		bt.addActionListener(this);
		// open버튼에 리스너 연결
		bt_open.addActionListener(this);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// btn으로 이런 방법도 가능
		// JButton btn = (JButton)obj; //하위 자료형으로 변환 down casting

		// if(btn.equals(bt)) 이것도 가능
		if (obj == bt) {
			System.out.println("send 버튼 눌렀어?");
			send();
		} else if (obj == bt_open) {
			System.out.println("open 버튼 눌렀어?");
			open();
		}
	}

	// 메시지창에 대화내용 누적하기!!!
	public void send() {
		// 나에대한 채팅처리
		String msg = t_input.getText();// 텍스트필드 값을 구하자!!
		area.append(msg + "\n");
		t_input.setText("");

		// 너에 대한 채팅처리

		chatA.area.append(msg + "\n");
		chatC.area.append(msg + "\n");

	}

	// 대화할 상대방 윈도우 띄우기!!!
	public void open() {
		// Messenger2를 화면에 띄우자!!
//		Messenger2 m2 = new Messenger2();
//		m2.setVisible(true);
		
	}
	
	public void setChatA(ChatA chatA) {
		this.chatA = chatA;
	}
	public void setChatC(ChatC chatC) {
		this.chatC = chatC;
	}


}
