package day1030.io;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Stream이란? 현실에서는 흐르는 물줄기를 의미
 * 					전산에서는 흐름의 대상이 물이 아닌 데이터이다!!
 * 					but 전산에서는 흐름의 방향에 따라 다음과 같이 분류
 * 					(기준은 실행중인 프로그램)
 * 					1)입력(Input): 실행중인 프로그램으로 데이터가 흘러들어 가는 것
 * 					2)출력(Output): 실행중인 프로그램에서 데이터가 흘러나오는 것
 * 
 * 자바에서는 입출력과 관련된 패키지명이 java.io이다!! 여기에는 입출력 처리를 위한
 * 수많은 api를 지원...


 예외란? 프로그램이 정상 실행되어질 수 없는 예외적인 상황을 의미 (에러)
 	에러가 발생하면? 프로그램이 비정상 종료가 되어 버린다.
	
	★★★★★★★★★★★★★★★★★★★★★★★
	예외처리의 목적은? 에러에 대한 정보를 분석하여 비정상 종료를 방지함에 있다!

 * */

public class FileReadApp {
	FileInputStream fis;
	public FileReadApp() {
		File file = new File("C:/Users/user/eclipse-workspace/SeProject/res/data/memo.txt");
			
		//로컬상의 파일을 대상으로 스트림(관)을 생성하자!!
		try{ //이 영역은 에러가 발생할 가능성이 있는 코드임을 명시
		fis = new FileInputStream(file);  //빨대
		System.out.println("스트림 생성 성공입니다.");

		int data;
	
		while(true){ //무한 루프
		data = fis.read();
		if(data==-1)	break; //파일의 끝에 도달하면, 반복문 빠져나옴
		System.out.print((char)data);

		}
		
			//sun사에서 미리, 가능성이 있는 에러를 객체화 시켜놓은 것 중 하나
			//만일 우려했던 에러가 발생하면, jvm에 의해 FileNotFoundException 인스턴스가
			//메모리에 올라오고, catch문의 인수로 전달하여, 개발자로 하여금 에러에 대한 정보를 분석 할 수 있는
			//기회를 주는것이다.
		}catch(FileNotFoundException e){ //혹여나 우려했던 에러가 발생한다면, 비정상 종료하지 말고,
															//실행부는 이 catch문 블럭을 수행하라!!	
			System.out.println("지정한 파일을 찾을 수 없습니다.");
			e.printStackTrace(); //stack구조로, 에러의 원인을 출력하라!
		}catch(IOException e){
			System.out.println("파일을 읽을 수 없습니다.");
			e.printStackTrace();

		}finally {
			//이 영역은 실행부가 try문을 수행하던 catch문을 수행하던 무조건 거쳐서 가야하는 영역 
			//이 영역에 자원을 닫는 코드를 작성해야 한다. 
			//주로 데이터베이스와의 연결끊기, 스트림 연결 끊기
			//트라이케치 단축키 ---> 블록지정- shift + alt + z

			//db,stream 닫을 때 반드시 null 여부를 따져보는 습관을 갖자!!
			if(fis!=null) {//메모리에 존재할때만..
				try {
				
					//null이 아닐 때만 닫자..
					fis.close(); //파일스트림 닫기
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			
			}
		}

	}
	
	public static void main(String[] args) {
		new FileReadApp();
	}
}
