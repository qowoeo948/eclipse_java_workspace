package com.swingmall.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.swingmall.board.QnA;
import com.swingmall.cart.Cart;
import com.swingmall.home.Home;
import com.swingmall.member.Login;
import com.swingmall.member.MyPage;
import com.swingmall.member.RegistForm;
import com.swingmall.product.Product;
import com.swingmall.product.ProductDetail;
import com.swingmall.util.db.DBManager;

public class ShopMain extends JFrame{
	public static final int WIDTH=1200;
	public static final int HEIGHT=900;
	//최상위 페이지들
	public static final int HOME=0;
	public static final int PRODUCT=1;
	public static final int QNA=2;
	public static final int MYPAGE=3;
	public static final int LOGIN=4;
	//하위페이지
	public static final int PRODUCT_DETAIL=5;
	public static final int MEMBER_REGIST=6;
	public static final int CART=7;

	
	JPanel user_container; //관리자,사용자 화면을 구분지을 수 있는 컨테이너
	JPanel p_content; 
	
	JPanel p_navi; //웹사이트의 주 메뉴를 포함할 컨테이너 패널
	String[] navi_title = {"Home","Product","QnA","MyPage","Login"};
	public JButton[]navi = new JButton[navi_title.length]; //[][][][][] 배열생성
	
	//페이지 구성
	private Page[] page = new Page[8]; //페이지들
	
	JLabel la_footer; //윈도우 하단의 카피라이트 영역
	private DBManager dbManager;
	private Connection con;
	
	//로그인 상태인지 여부를 알수 있는 변수
	private boolean hasSession= false;
	
	public ShopMain() {
		dbManager = new DBManager();
		user_container = new JPanel();
		p_content = new JPanel();
		p_navi = new JPanel();
		
		la_footer = new JLabel("SwingMall All rights reserved",SwingConstants.CENTER);
		
		con = dbManager.connect(); 
		if(con==null) {
			JOptionPane.showMessageDialog(this, "데이터베이스 접속 실패");
		}else {
			this.setTitle("SwingMall 유저로 접속 성공");
		}
		
		//메인네비게이션 생성
		for(int i =0;i<navi.length;i++) {
			navi[i]=new JButton(navi_title[i]);
			p_navi.add(navi[i]);
		}
		
		//페이지구성
		page[0]=new Home(this);
		page[1]=new Product(this);
		page[2]=new QnA(this);
		page[3]=new MyPage(this);
		page[4]=new Login(this);
		page[5]=new ProductDetail(this);
		page[6]=new RegistForm(this); //회원가입 폼
		page[7]=new Cart(this);
	
		
		//스타일 적용
		user_container.setPreferredSize(new Dimension(WIDTH,HEIGHT-50));
		user_container.setBackground(Color.WHITE);	
		la_footer.setPreferredSize(new Dimension(WIDTH,50));
		la_footer.setFont(new Font("Arial Black",Font.BOLD,30));
		
		
		//조립
		user_container.setLayout(new BorderLayout());
		user_container.add(p_navi,BorderLayout.NORTH);

		for(int i=0;i<page.length;i++) {
			p_content.add(page[i]);
		}
		
		//센터에 부착
		user_container.add(p_content);
		
		add(user_container);
		add(la_footer,BorderLayout.SOUTH);
		
		
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dbManager.disConnect(con);
			}
		});
		
		//네비게이션 버튼과 리스너연결 
		for(int i=0;i<navi.length;i++) {
			navi[i].addActionListener((e)->{
				Object obj = e.getSource();
				if(obj==navi[0]) { //home
					showPage(0);
				}else if(obj==navi[1]) {
					showPage(1);
				}else if(obj==navi[2]) {
					showPage(2);
				}else if(obj==navi[3]) {
					//mypage 는 무조건 보여줘서는 안되고, 로그인한 사람에게만 보여줘야 함!!
					//로그인 상태가 아니라면 욕!!
					if(hasSession==false) {
						JOptionPane.showMessageDialog(ShopMain.this,"로그인이 필요한 서비스입니다");
					}else {
						showPage(3);
					}
					
				}else if(obj==navi[4]) {
					//로그인을 요청할지, 로그아웃을 요청할지를 구분하자!!
					//hasSession의 값이 true 일때는 로그인한 상태이므로, 로그아웃을 요청해야 한다..
					if(hasSession) {
						int ans = JOptionPane.showConfirmDialog(ShopMain.this, "로그아웃 하시겠습니까?");
						
						if(ans==JOptionPane.OK_OPTION) {//예를 누른것임
							Login loginPage = (Login)page[ShopMain.LOGIN];
							loginPage.logout();
						}
					}else {
						showPage(4);//로그인						
					}
				}
			});
		}
		
		
		
	}

	//보여질 페이지와 않보여질 페이지를 설정하는 메서드
	public void showPage(int showIndex) { //매개변수로는 보고싶은 페이지 넘버
		for(int i=0;i<page.length;i++) {
			if(i==showIndex) {
				page[i].setVisible(true);				
			}else {
				page[i].setVisible(false);				
			}
		}
	}
	
	
	public Connection getCon() {
		return con;
	}
	
	public DBManager getDbManager() {
		return dbManager;
	}
	
	
	public Page[] getPage() {
		return page;
	}
	

	public boolean isHasSession() {
		return hasSession;
	}

	public void setHasSession(boolean hasSession) {
		this.hasSession = hasSession;
	}

	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj); //제거될 자
		this.add(addObj);//부착될 자
		((JPanel)addObj).updateUI();
	}


	public static void main(String[] args) {
		new ShopMain();
	}

}
