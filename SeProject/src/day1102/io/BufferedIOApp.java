package day1102.io;
/*
 * 실행중인 프로그램으로 데이터를 읽거나 쓸때 한 바이트, 한 문자씩 입출력을 수행하면,
 * 데이터량이 많을 때 너무 많은 입출력을 수행하게 된다.. 속도 저하..
 * 마치CMD창의 버퍼 처리 처럼, 메모리상의 버퍼에 데이터를 모아놓고 한 줄이 끝나는 시점에만 
 * 입력 및 출력을 처리하면 프로그램 실행의 효율성이 극대화 된다!!
 * 스트림 중 버퍼를 지원하는 스트림은 접두어로 Buffered~~가 붙는다.
 * 
 * 
 * * 바이트
 * 스트림은 방향에 따라 입력 ~InputStream
 * 							출력 ~OutputStream
 * 
 *문자기반
 * 문자기반의 입력 Reader(인간의 눈으로 읽을 수 있다)
 * 				출력 Writer
 * 
 * 버퍼처리 여부에 따라
 * Buffered
 * 
 * 실습) 키보드로 입력된 데이터를 한 줄씩 가져와서 화면에 출력해보자
 * 
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedIOApp {

	public static void main(String[] args) {
		//keyboard에 연결된 스트림은 개발자가 원하는 타임에 new할 수 없다~
		//왜? os가 이미 얻어다 놓았으므로 .. 따라서 자바에서는 시스템으로 부터 얻어올수있다.
		
		//입력스트림의 최상위 추상 클래스이다! (키보드,스캐너 등등은 이 스트림으로 받을 수 있다)
		InputStream is = System.in;
		
		//한글이 깨지지 않도록 문자기반으로 업그레이드
		InputStreamReader reader = new InputStreamReader(is); //바이트 기반을 문자기반 스트림으로 변경
		BufferedReader buffr = new BufferedReader(reader); //버퍼 처리된 문자기반의 입력스트림
		
		int data;
		String str="null";
		try {
			str = buffr.readLine();
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
