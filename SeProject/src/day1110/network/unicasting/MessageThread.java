package day1110.network.unicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//이 쓰레드는, 대화용 쓰레드 이므로 입출력 스트림을 각각의 인스턴스가 보유해야 한다.
//아래의 클래스를 쓰레드로 선언하는 순간부터, 이 인스턴스들은 서로 비동기적(독립적)으로 동작할 수 있다!!
public class MessageThread extends Thread{
	Socket socket; //각각의 쓰레드는 대화용 소켓을 보유해야 스트림을 뽑을 수 있으므로, 접속자가 감지되면
							//해당 소켓을 인수로 넘겨받자
	BufferedReader buffr; //듣기 (서버입장)
	BufferedWriter buffw;// 말하기 (써버입장)
	UniServer uniserver;
	
	public MessageThread(UniServer uniserver,Socket socket) {
		this.socket = socket;
		this.uniserver = uniserver;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//메시지 받기 (청취)
		public void listen() {
			String msg="null";
			try {
				while(true) {
				msg = buffr.readLine();
				uniserver.area.append(msg+"\n");
				//클라이언트에게 다시 보내야 한다(서버의 의무)
				send(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void run() {
			//서버는 듣고 보내니까 listen 호출
			listen();
			
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
	
	
	
}
