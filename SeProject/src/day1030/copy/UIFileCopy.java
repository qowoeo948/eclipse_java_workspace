package day1030.copy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UIFileCopy extends JFrame implements ActionListener{
	JLabel l_ori;
	JLabel l_dest;
	JTextField t_ori;
	JTextField t_dest;
	JButton bt;
	
	public UIFileCopy() {
		l_ori = new JLabel("원본");
		l_dest = new JLabel("복사본");
		t_ori = new JTextField();
		t_dest = new JTextField();
		bt = new JButton("COPY");
		
		
		l_ori.setPreferredSize(new Dimension(150,30));
		l_dest.setPreferredSize(new Dimension(150,30));
		t_ori.setPreferredSize(new Dimension(500,30));
		t_dest.setPreferredSize(new Dimension(500,30));
		
		this.setLayout(new FlowLayout());
		
		
		this.add(l_ori);
		this.add(t_ori);
		this.add(l_dest);
		this.add(t_dest);
		this.add(bt);
		
		
		
		bt.addActionListener(this);
		
//		JFileChooser chooser = new JFileChooser();
//		chooser.showOpenDialog(this);
		
		
		this.setSize(700,180);
		this.setVisible(true);
	//오늘은 일회성이니 해도돼
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//하면안됨. 왜? 스트림을 닫을 기회가 없기 떄문에
		//해결책) 윈도우창을 닫을 때, 이벤트를 구현해야 한다. 즉 WindowListener구현..!
}
	
	public void copy() {
		//이 두개의 클래스가 메모리에 올라와야 하는 시점은?
		//메서드내의 지역변수는 반드시 개발자가 초기화해야한다.. 멤버변수가 아니다
		FileInputStream fis=null; // 파일을 대상으로 한 입력스트림
		FileOutputStream fos=null; // 파일을 대상으로 한 출력스트림
		String ori = t_ori.getText();
		String dest = t_dest.getText();
		
		try {
			fis = new FileInputStream(ori); //입력 스트림 생성!!
			fos = new FileOutputStream(dest); //출력 스트림 생성
			
			//읽고 내뱉자!!
			int data;
			while(true) {
				data = fis.read();  //1byte 읽기
				if(data==-1) break;
				fos.write(data);
			}
			JOptionPane.showMessageDialog(this,"복사완료");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		copy();
	
	}
	
	public static void main(String[] args) {
		new UIFileCopy();
	}


}


