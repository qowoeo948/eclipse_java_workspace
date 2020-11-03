/*
 * 진행상황을 직관적으로 알 수 있는 프로그래스바를 활용해보자
 * */

package day1103.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ProgressApp extends JFrame{
	JLabel la;
	JProgressBar bar1;
	JProgressBar bar2;
	JProgressBar bar3;
	BarThread t1,t2,t3; //바를 증가시킬 쓰레드
	
	
	public ProgressApp() {
		la = new JLabel("0",SwingConstants.CENTER);
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		
			
		la.setPreferredSize(new Dimension(500,150));
		la.setFont(new Font("Verdana",Font.BOLD,45));
		bar1.setPreferredSize(new Dimension(500,60));
		bar2.setPreferredSize(new Dimension(500,60));
		bar3.setPreferredSize(new Dimension(500,60));
		
//		barThread = new Thread() {
//			@Override
//			public void run() {
//				while(true) {
//				n++;
//				setBarValue();
//				try {
//					Thread.sleep(50); //non-runnable에 빠졌다가 0.5초뒤 복귀하라는 명령
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				}
//			}
//			
//		};
		
		
		this.add(la);
		this.add(bar1);
		this.add(bar2);
		this.add(bar3);
		
		//쓰레드 3개 생성하여 runable로 진입시키자
		t1 = new BarThread(bar1,400);
		t2 = new BarThread(bar2,250);
		t3 = new BarThread(bar3,50);
		
		t1.start();
		t2.start();
		t3.start();
		
		setLayout(new FlowLayout());
		this.setSize(600,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	
	public static void main(String[] args) {
		new ProgressApp();
	}
}
