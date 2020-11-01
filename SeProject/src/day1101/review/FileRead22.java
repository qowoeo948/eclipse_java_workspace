package day1101.review;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.lang.model.element.NestingKind;

public class FileRead22 {
	FileInputStream fis;
	public FileRead22() {
		File file = new File("C:/Users/user/eclipse-workspace/SeProject/res/data/memo2.txt");
		
		try {
			fis = new FileInputStream(file);
			System.out.println("스트림 생성 성공!!");
			
			int data;
			while(true) {
				data = fis.read();
				if(data==-1) break;
				System.out.println((char)data);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("지정한 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일을 읽을 수 없습니다.");
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
		}

		
	
	}
	
	public static void main(String[] args) {
	 new FileRead22();
	}

}
