package day1102.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class WindowApp extends JFrame{
	
	
	public WindowApp() {
	this.addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("WindowClosing");
			System.exit(0); //프로세스 종료
		}
		

	});
	this.setSize(300,400);
	this.setVisible(true);
	}

	public static void main(String[] args) {
		new WindowApp();
	}

	

}
