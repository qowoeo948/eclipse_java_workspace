package day1113.xml.down;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import common.file.FileManager;

public class DownLoader extends JFrame{
	JButton bt_down;
	JProgressBar bar;
	MovieHandler movieHandler;
	Thread parsingThread;
	
	public DownLoader() {
		bt_down = new JButton("download");
		bar = new JProgressBar();
	
		bar.setPreferredSize(new Dimension(580,45));
		bar.setForeground(Color.PINK);
		bar.setBackground(Color.WHITE);
		
		bar.setFont(new Font("Verdana",Font.BOLD,25));
		bar.setStringPainted(true);
		bar.setValue(0);
		
		setLayout(new FlowLayout());
		add(bt_down);
		add(bar);

		parsingThread = new Thread() {
			@Override
			public void run() {
				parseData();
				//총 몇건이 존재하는지 출력
//				System.out.println(movieHandler.movieList.size());
				for(int i =0; i<movieHandler.movieList.size();i++) {
					Movie movie = movieHandler.movieList.get(i);
					download(movie.getUrl());
				}
			}
		};
		
		//다운로드 버튼과 리스너 연결
		bt_down.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parsingThread.start();
			}
		});
		
		setSize(600,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void parseData() {
		//xml을 파싱하여 url만 추출해야 함!!
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser(); //파서 객체 생성
			URL url = this.getClass().getClassLoader().getResource("res/marvel.xml");
			URI uri = url.toURI();
			File file = new File(uri);
			
			saxParser.parse(file,movieHandler = new MovieHandler()); //파싱 시작
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	//인터넷상의 자원과 연결한 후, 스트림으로 데이터를 읽어와 로컬 하드 경로에 저장하기
	public void download(String path) { //매개변수로 가져올 자원을 지정한다.
		InputStream is = null;
		FileOutputStream fos =null; //다운받은 파일을 저장할 스트림
		int count=0;
		try {
			URL url = new URL(path);
			URLConnection con = url.openConnection();
			HttpURLConnection http=(HttpURLConnection)con; //웹에 특화된 커넥션 객체!! 따라서 get/post등 웹기반 요청가능
			
			
			http.setRequestMethod("GET");
			is = http.getInputStream();
			long time = System.currentTimeMillis(); //파일명
			String ext = FileManager.getExtend(path);
			String filename = time+"."+ext; //최종적으로 부여된  파일 명
			fos = new FileOutputStream("C:/Users/user/eclipse-workspace/SeProject/res/download/"+filename);
			int data = -1;
			while(true) {
				data = is.read();
				bar.setValue(count++);
				if(data==-1)break;
				fos.write(data);
				
			}
			
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
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		new DownLoader();
	}
}
