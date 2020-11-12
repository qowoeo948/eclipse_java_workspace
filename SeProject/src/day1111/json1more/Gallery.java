package day1111.json1more;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Gallery extends JFrame{
	JPanel p_center;
	JPanel p_south;
	JPanel p_can;
	JPanel p_detail;
	
	BufferedReader buffr;
	
	Image big;
	
	JLabel[] la = new JLabel[4];
	String[] la_title= {"Title","Phase","Categoru","Release"};
	
	public Gallery() {
		p_center = new JPanel();
		p_south = new JPanel();
		p_can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(big, 0, 0, this);
			}
		};
		p_detail = new JPanel();
		
		for(int i=0;i<la_title.length;i++) {
			la[i]=new JLabel(la_title[i]);
			la[i].setPreferredSize(new Dimension(380,50));
			
			p_detail.add(la[i]);
		}
		
		p_center.setLayout(new GridLayout(1,2));
		p_south.setPreferredSize(new Dimension(800,100));
		p_south.setBackground(Color.PINK);
		p_can.setBackground(Color.GRAY);
		p_detail.setBackground(Color.GREEN);
		p_center.add(p_can);
		p_center.add(p_detail);
		add(p_center);
		this.add(p_south,BorderLayout.SOUTH);
			
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	
		createThumb();
	}
	
	public void createThumb() {
		try {
			URL url  = getClass().getClassLoader().getResource("res/data.json");
			URI uri = url.toURI();
			FileReader reader = new FileReader(new File(uri));
			buffr = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();
			String data=null;
			
			while(true) {
				
			data = buffr.readLine();
			if(data == null) break;
				sb.append(data);
			}
			System.out.println(sb.toString());
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
			JSONArray jsonArray = (JSONArray)jsonObject.get("marvel");
			
			for(int i=0;i<jsonArray.size();i++) {
				
			
			JSONObject obj = (JSONObject)jsonArray.get(i);
			
			String u = (String)obj.get("url");
			String title = (String)obj.get("title");
			String phase = (String)obj.get("phase");
			String category_name = (String)obj.get("category_name");
			String release_year = ((Long)obj.get("release_year")).toString();
			
			Movie2 movie2 = new Movie2(this,45, 45, u,title,phase,category_name,release_year);
					
			p_south.add(movie2);			
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void getDetail(Image big,String title,String phase,String category_name,String release_year) {
		this.big = big;
		p_can.repaint();
		
		la[0].setText(title);
		la[1].setText(phase);
		la[2].setText(category_name);
		la[3].setText(release_year);
		
	}
	
	public static void main(String[] args) {
	new Gallery();
	
	}

}
