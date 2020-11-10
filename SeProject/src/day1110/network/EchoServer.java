/*
 * 소켓이란?
 * 일상 생활에서는 전구 등에 꼽는 접점도구이다.
 * 역할?
 *	일상생활  -  전기지식 없어도, 전구를 동작시킬 수 있다.
 * 	프로그래밍분야    -  네트워크 지식이 없어도, 네트워크 제어 프로그래밍이 가능하게 하는것..
 *   따라서, 사실상 프로그래밍 언어에서 개발자가 제어하는 대상은 스트림인 것 뿐이다..
 * 
 * 소켓은 자바 언어만 지원하는 기술이고, 용어이다   ( X )
 * 		-현존하는 대부분의 응용 어플리케이션에서 지원하고 있다.(컴파일기반 :c,java,x#,python(인터프리터)...)
 * */

package day1110.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
		int port=8989; //클라이언트를 받아들일 포트번호, 이 포트번호에 의해서 다른 네트워크 프로세스와 구분될 수 있다.
					//예) 오라클 1521, mysql 3306
		ServerSocket server; //대화용 소켓이 아니라, 접속자를 감지하고, 접속자가 발견되면 대화용 소켓을 반환
										//해주는 객체 (마치 전화기의 벨이 울리면 그 이후부터 전화를 받고 대화하는것과 비슷)
										//전화 받는 용 소켓
	public EchoServer() {
		try {
			server = new ServerSocket(port); //서버소켓 생성 (아직 가동은 아님)
			Socket socket = server.accept(); //서버 가동 및 클라이언트의 접속 기다림(접속이 들어올때까지 무한대기 한다,지연발생)
			System.out.println("접속자 발견");
			//개발자는 반환받은 소켓으로부터 통신에 필요한 입출력 스트림을 얻을 수 있다.
			//이때 개발자는 네트워크 하부에 대한 아무런 지식이 없어도 그냥 스트림처리만 하면 알아서 원격지의 대화상대와
			//통신이 가능하며 이 모든것들은 소켓이 알아서 해주는 것이다.
		
			InputStream is = socket.getInputStream(); //바이트기반의 입력스트림이므로 영문만 해석가능한 상태
			InputStreamReader reader = new InputStreamReader(is);//한글로
			BufferedReader buffr = new BufferedReader(reader); //한줄씩 읽어들임
			
			//int data;
			String data;
			while(true) {
				//data = reader.read(); //한바이트 읽기 (소켓과 연결된 스트림으로 부터)
				data = buffr.readLine(); //한 줄 씩
				//System.out.print((char)data);
				System.out.print(data);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();
	}

}
