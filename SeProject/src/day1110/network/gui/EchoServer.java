package day1110.network.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoServer extends JFrame{
	JTextField t_port;
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port=7777;
	Thread thread ; //메인쓰레드 대신, 접속자를 감지하게 될 쓰레드!! (accept()메서드 떄문에,,)
	BufferedReader buffr; //듣기 (서버입장)
	BufferedWriter buffw;// 말하기 (써버입장)
	
	public EchoServer() {
		t_port = new JTextField((Integer.toString(port)),10);
		bt = new JButton("서버가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//조립
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		
		//가동버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					@Override
					public void run() {
						startServer();
					}
				};
				
				thread.start();
			}
		});
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //윈도우창 닫기 + 프로세스종료
		setVisible(true);
		setBounds(600,200,300,400); //x,y,width,height 순서
		
	}
	
	//서버 가동
	public void startServer() {
		
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 준비\n");
			
			//서버가동하기
			//자바는 쓰레드기반이므로, 지금까지 메인실행부라 불렸던 실행조체도 사실은 시스템에 의해서 생성된 쓰레드였다.
			//하지만, 메인쓰레드는 개발자가 생성하는 일반쓰레드와는 하는 역할에 차이가 있다.
			//메인쓰레드는 프로그램을 운영해주는 역할, 특히 그래픽처리와, 이벤트처리까지 담당하므로, 절대로 
			//이러한 업무 금지 1) 무한루프에 빠뜨리지 말것   2) 대기상태에 빠뜨리지 말것,	예로 (accept,read())
			//참고로 안드로이드에서는 메인쓰레드의 1,2번 시도자체를 에러로 본다.
			//결론) 별도의 쓰레드를 생성하여 처리하자!!
			
			Socket socket = server.accept(); //접속자 감지와 동시에 대화용 소켓 반환!
			area.append("접속자 발견\n");
			
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			 
			listen(); //듣기시작
			 
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//메시지 받기 (청취)
	public void listen() {
		String msg=null;
		try {
			while(true) {
			msg = buffr.readLine();
			area.append(msg+"\n");
			//클라이언트에게 다시 보내야 한다(서버의 의무)
			send(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//클라이언트에게 메시지 보내기
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new EchoServer();
		
	}
	

}
