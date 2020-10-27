//단축기보기 crtl + shift + L
//임포트 ctrl+shift+o
//자동 줄맞춤: ctrl+shift+F
//api찾아주기 shift + F2
//클래스만들기 ctrl+n,   프로젝트만들기 alt+shift+n
//생성자도 손으로 일일이 입력하지 말고 ctrl+space
package day1027.gui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;

public class RadioTest extends Frame {
	CheckboxGroup group;

	public RadioTest() {
		group = new CheckboxGroup();
		setLayout(new FlowLayout());
		this.add(new Checkbox("수영", group, false));
		this.add(new Checkbox("영화보기", group, false));
		this.add(new Checkbox("독서", group, false));
		this.add(new Checkbox("컴퓨터게임", group, false));
		this.add(new Checkbox("강아지돌보기", group, true));

		this.setSize(300, 400);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new RadioTest();

	}

}
