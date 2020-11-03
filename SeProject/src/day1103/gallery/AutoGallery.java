package day1103.gallery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.image.ImageUtil;

public class AutoGallery extends JFrame {
	JPanel can;
	JButton bt_prev, bt_pause, bt_auto, bt_next;
	//os의 종속된 경로로 가져올 때는 Toolkit을 이용한다
	Toolkit kit;
	Image[] img = new Image[10];
	int index=0; //사진 배열의 인덱스
	Thread thread; //자동사진 넘기기용 스레드
	boolean flag = false;
	
	
	public AutoGallery() {
		kit = Toolkit.getDefaultToolkit();
			img[0]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/aa.jpg"),700, 700);
			img[1]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/ab.jpg"),700, 700);
			img[2]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/ax.jpg"),700, 700);
			img[3]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/bm.jpg"),700, 700);
			img[4]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/et.jpg"),700, 700);
			img[5]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/kg.jpg"),700, 700);
			img[6]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/mx.jpg"),700, 700);
			img[7]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/pk.jpg"),700, 700);
			img[8]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/ub.jpg"),700, 700);
			img[9]=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/SeProject/res/travel2/ya.jpg"),700, 700);
					
	

		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				//img[0]=img[0].getScaledInstance(700, 700, Image.SCALE_SMOOTH);
				g.drawImage(img[index], 0, 0, this);
			}
		};
		
		thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					if(flag) {
						next();						
					}
//					index++;
//					can.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		};

		bt_prev = new JButton(ImageUtil.getIcon(this.getClass(), "res/bt_prev.png",100,45));
		bt_pause = new JButton(ImageUtil.getIcon(this.getClass(), "res/bt_stop.png",100,45));
		bt_auto = new JButton(ImageUtil.getIcon(this.getClass(), "res/bt_auto.png",100,45));
		bt_next = new JButton(ImageUtil.getIcon(this.getClass(), "res/bt_next.png",100,45));

		//스타일적용
		can.setPreferredSize(new Dimension(680,600));
		can.setBackground(Color.YELLOW);
		bt_prev.setPreferredSize(new Dimension(100,45));
		bt_pause.setPreferredSize(new Dimension(100,45));
		bt_auto.setPreferredSize(new Dimension(100,45));
		bt_next.setPreferredSize(new Dimension(100,45));
		setLayout(new FlowLayout());
		
		this.add(can);
		this.add(bt_prev);
		this.add(bt_pause);
		this.add(bt_auto);
		this.add(bt_next);
		
		bt_prev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prev();
				
			}
		});
		bt_next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
				
			}
		});
		bt_pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flag = false;
			}
		});
		bt_auto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flag = true;
				thread.start();
				
			}
		});

		this.setSize(700, 750);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void next() {
		index++;
		can.repaint(); 
	}
	public void prev() {
		index--;
		can.repaint();
	}
	
	

	public static void main(String[] args) {
		new AutoGallery();
	}
}
