package day1101.review;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy22 {
	FileInputStream fis;
	FileOutputStream fos;
	String ori = "C:/Users/user/eclipse-workspace/SeProject/res/data/memo2.txt"; // 원본의 위치
	String dest = "C:/Users/user/eclipse-workspace/SeProject/res/data/good.txt"; // 복사될 경로의 위치
	
	
	
	public FileCopy22() {
		try {
			fis=new FileInputStream(ori);
			System.out.println("파일스트림생성 성공");
			fos=new FileOutputStream(dest);
		
		int data;
		while(true) {
			data = fis.read();
			if(data == -1) break;
			fos.write(data);
		}
		System.out.println("복사처리 완료!");

		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을수없습니다.");
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
		new FileCopy22();
	}
}
