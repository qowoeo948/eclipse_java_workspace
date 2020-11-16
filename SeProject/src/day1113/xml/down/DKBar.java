package day1113.xml.down;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class DKBar extends JFrame{
	JTextField text;
	JButton bt;
	JProgressBar bar;
	
	URLConnection con;
	HttpURLConnection http;
	URL url;
	FileOutputStream fos;
	Thread thread;
	
	
	
	public DKBar() {
		text = new JTextField();
		bt = new JButton("download");
		bar = new JProgressBar();		
		setLayout(new FlowLayout());
		add(text);
		add(bt);
		add(bar);
		text.setPreferredSize(new Dimension(400,50));
		bar.setPreferredSize(new Dimension(400,50));
		
		thread = new Thread() {
			public void run() {
				
				
			};
			
		};
		
		
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getURL();
				
			}
		});
		
		setSize(500,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public void getURL() {
		try {
			
			url = new URL(text.getText());
			
			con = url.openConnection();
			http = (HttpURLConnection)con;
			
			http.setRequestMethod("GET");
			
			InputStream is = http.getInputStream();
			
			File file = new File("C:/Users/user/eclipse-workspace/SeProject/res/copy2.jpg");
			fos = new FileOutputStream(file);
			
			int data = -1;
			while(true) {
				data = is.read();
				if(data==-1)break;
				fos.write(data);
			}
			
			JOptionPane.showMessageDialog(this, "다운로드완료");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		new DKBar();
	}

}
