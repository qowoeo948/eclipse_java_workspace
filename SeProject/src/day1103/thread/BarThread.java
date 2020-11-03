package day1103.thread;

import javax.swing.JProgressBar;

public class BarThread extends Thread{
	int n;
	int interval;
	JProgressBar bar;
	
	public BarThread(JProgressBar bar,int interval) {
		this.bar = bar;
		this.interval = interval;
	}

	public void run() {
		while(true) {
		n++;
		bar.setValue(n);
		try {
			Thread.sleep(interval); //non-runnable에 빠졌다가 0.5초뒤 복귀하라는 명령
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
