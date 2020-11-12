package day1111.json1more;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Movie2 extends JPanel implements Runnable{
	Image img;
	Image big;
	BufferedImage buffImg;
	Gallery gallery;
	Thread thread;
	
	int width;
	int height;
	
	String url;
	String title;
	String phase;
	String category_name;
	String release_year;
	
	public Movie2(Gallery gallery, int width,int height,String url,String title,String phase,String category_name,String release_year) {
		this.gallery = gallery;
		this.width = width;
		this.height = height;
		this.url = url;
		this.title = title;
		this.category_name = category_name;
		this.release_year = release_year;
		
		
		this.setPreferredSize(new Dimension(width,height));
		thread = new Thread(this);
		thread.start();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				gallery.getDetail(big,title,phase,category_name,release_year);
			}
		});
		
	
	}
	
	public void getErrorImage() {
		URL url = getClass().getClassLoader().getResource("res/error.png");
		try {
			BufferedImage buffImg = ImageIO.read(url);
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void run() {
		try {
			URL path = new URL(url);
			buffImg = ImageIO.read(path);
			big = buffImg.getScaledInstance(400, 550, Image.SCALE_SMOOTH);
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			gallery.p_south.updateUI();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
			getErrorImage();
		}
		
		
	}
	
}
