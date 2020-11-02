/*
 * 리스너는 인터페이스이기 때문에, 리스너를 구현하는자는 반드시
 * 추상메서드를 재정의해야한다.
 * 하지만, 리스너가 보유한 추상메서드의 수가 너무 많은 경우
 * 사용하지도 않는 비어있는 메서드를 우리가 정의한 클래스내에 두는 것이 효율적이지 못하다.
 * 따라서 Sun에서는 리스너의 메서드가 3개이상일 경우, 각각의 리스너를 구현한 어댑터라는
 * 객체를 지원해준다.
 * 
 * ex) WindowListener 를 구현한 클래스 WindowAdapter
 * 		 MouseListener를 구현한 클래스 MouseAdapter
 * 		 KeyListener를 구현한 클래스 KeyAdapter
 * */
package day1102.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWinAdapter extends WindowAdapter{
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("WindowClosing");
		System.exit(0); //프로세스 종료
	}
	

}
