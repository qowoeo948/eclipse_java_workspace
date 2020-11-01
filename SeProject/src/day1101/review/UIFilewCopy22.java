package day1101.review;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UIFilewCopy22 extends JFrame implements ActionListener{
	JLabel l_ori;
	JLabel l_dest;
	JTextField t_ori;
	JTextField t_dest;
	JButton bt;
	
	public UIFilewCopy22() {
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
		
		
		
		this.setSize(700,180);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		copy();
	}
	
	public void copy() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String ori = t_ori.getText();
		String dest = t_dest.getText();
		
		try {
			fis = new FileInputStream(ori);
			System.out.println("파일 스트림완료!");
			fos = new FileOutputStream(dest);
			
			int data;
			while(true) {
				data = fis.read();
				if(data == -1) break;
				fos.write(data);
			}
			JOptionPane.showMessageDialog(this,"복사완료");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		new UIFilewCopy22();
	}
}
