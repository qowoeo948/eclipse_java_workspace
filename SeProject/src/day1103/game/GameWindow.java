package day1103.game;

import java.awt.FlowLayout;import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	GamePanel gamePanel; //실제 게임이 그려질 패널
	
	public GameWindow() {
		gamePanel = new GamePanel();
		setLayout(new FlowLayout());
		this.add(gamePanel);
		
		pack();//윈도우 안에 있는 내용물까지 줄어듦
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		//윈도우와 리스너 연결
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("눌렀어?");
				gamePanel.moveKey(e.getKeyCode());
			}
			
			public void keyReleased(KeyEvent e) {
				System.out.println("눌렀어?");
				gamePanel.stopKey(e.getKeyCode());
			}
			
		});
		
		
		
		
	}
	
	public static void main(String[] args) {
		new GameWindow();
	}
}
