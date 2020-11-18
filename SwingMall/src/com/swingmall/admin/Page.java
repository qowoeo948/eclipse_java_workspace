/*
 * 모든 페이지가 공통적으로 가져야할 속성, 메서드등을
 * 정의하기 위한 최상위 페이지 클래스
 * 따라서 Home, product, Q&A, MyPage, Login등의 페이지들이
 * 이 클래스를 상속받을 경우, 코드를 중복해서 작성하지 않아도된다.
 * */
package com.swingmall.admin;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	private AdminMain adminMain;
	
	public AdminMain getAdminMain() {
		return adminMain;
	}
	
	public Page(AdminMain adminMain) {
		this.adminMain = adminMain;
		this.setPreferredSize(new Dimension(adminMain.WIDTH,adminMain.HEIGHT-100));
	}
	
	
}
