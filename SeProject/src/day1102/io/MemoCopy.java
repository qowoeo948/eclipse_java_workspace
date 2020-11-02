/*
 * 지난주에는 문서파일(영문)과 바이너리 파일(사진)등을 복사해봤다.
 * 하지만, 문서파일의 경우 한글이 섞여 있을 때 어떤 결과가 나오는지 테스트해보자
 * 
 * replace all   ctrl + f
 * 
 * [스트림의 유형]
 * 스트림의 기본은 1byte씩 처리하는 바이트 기반의 스트림이다.
 * 하지만, 스트림으로 흐르는 데이터를 문자로 해석할 수 있는 스트림을 문자기반 스트림이라 한다.
 * 문자기반 스트림은 접미어가 ~Reader, ~Writer로 끝난다..
 * 
 * 바이트
 * 스트림은 방향에 따라 입력 ~InputStream
 * 							출력 ~OutputStream
 * 
 *문자기반
 * 문자기반의 입력 Reader(인간의 눈으로 읽을 수 있다)
 * 				출력 Writer
 * 
 * 
 * 
 * */
package day1102.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoCopy {
	FileInputStream fis; 
	FileOutputStream fos;
	
	//2byte를 이해하는 애들
	FileReader reader; //파일을 대상으로 한 문자기반의 입력스트림
	FileWriter writer; //파일을 대상으로 한 문자기반의 출력스트림
	
	String path = "C:/Users/user/eclipse-workspace/SeProject/res/data/test.txt";
	String path2 = "C:/Users/user/eclipse-workspace/SeProject/res/data/test2.txt";
	
	public MemoCopy() {
		try {
			//fis = new FileInputStream(path);
			reader = new FileReader(path);
			System.out.println("스트림생성 성공");
//			fos = new FileOutputStream(path2);
			writer = new FileWriter(path2);
			int data;
			while(true) {
				//한바이트씩 읽어들이면서 영문과 한글이 어떻게 되는지 관찰해보자
				//결론:FileInputStream은 바이트 기반의 스트림이므로 1byte만 해석할 수 있다.
				//따라서 한글의 경우 2byte로 구성되어 있으므로, 복사는 성공하겠으나, 스트림상에서 흐르는
				//데이터를 한글로 보고자 할떄는 꺠져서 보일수 밖에 없다.
				data = reader.read();
				if(data==-1) break;
				System.out.print((char)data);
				writer.write(data);
			}
			System.out.println("파일복사완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	
	
	}
	
	public static void main(String[] args) {
		new MemoCopy();
	}

}
