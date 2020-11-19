package com.swingmall.admin.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.swingmall.admin.AdminMain;
import com.swingmall.admin.Page;


public class Product extends Page{
	JPanel p_west; //tree나올 영역
	JPanel p_center; 
	JTree tree;
	JTable table;
	JScrollPane s1,s2;  //s1 -트리용 s2 -테이블용
	JButton bt_regist;
	
	
	ArrayList<String> topList; //상위카테고리 이름을 담게될 리스트, top,down,accessary,shoes
	ArrayList<ArrayList> subList = new ArrayList<ArrayList>(); //모든 하위카테고리

	ProductModel model;
	RegistForm registForm;
	
	public Product(AdminMain adminMain) {
		super(adminMain);

		
		getTopList(); //상위 카테고리 가져오기, 맴버변수인 topList에 최상위 카테고리가 채워진다.
		for(String name:topList) { //변수 name에 topList가 대입 size만큼
		getSubList(name); 
		}
		
//		for(int i =0; i<topList.size();i++) {
//			getSubList(topList.get(i));
//		}

		//노드만들기
		//'상품목록'이라는 제목의 최상위노드 생성하기
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품목록");
		for(int i =0;i<topList.size();i++) {
			top.add(getCreatedNode(topList.get(i), subList.get(i)));
		}
		
		p_west = new JPanel();
		p_center = new JPanel();
		tree = new JTree(top);
		table = new JTable();
		s1 = new JScrollPane(tree);
		s2 = new JScrollPane(table);
		bt_regist = new JButton("등록하기");
		
		//등록폼 생성
		registForm = new RegistForm(this);
		
		//스타일
		s1.setPreferredSize(new Dimension(200,adminMain.HEIGHT-100));
		p_west.setBackground(Color.WHITE);
		s2.setPreferredSize(new Dimension(adminMain.WIDTH-300,adminMain.HEIGHT-200));

		//조립
		setLayout(new BorderLayout());
		
		p_west.add(s1); //서쪽 패널에 트리 부착
		p_center.add(s2); //센터패널에 테이블 부착
		p_center.add(bt_regist);
		
		add(p_west,BorderLayout.WEST);
		add(p_center);

		
		getProductList(null);//모든상품 가져오기
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
//				System.out.println("나 선택했어?");
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			
				if(selectedNode.toString().equals("상품목록")) {
					getProductList(null);//모든상품 가져오기					
				}else {
					getProductList(selectedNode.toString()); //모든 상품 가져오기
					
				}
			}
		});
		
		bt_regist.addActionListener((e)->{
			addRemoveContent(p_center, registForm);
			
		});
		
		
	}

	//상위카테고리 가져오기
	public void getTopList() {
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		String sql="select *from topcategory";
		
		try {
			pstmt=getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			//배열은 유연하지 못하므로 ArrayList에 담자
			topList = new ArrayList();
			while(rs.next()) {	
				topList.add(rs.getString("name"));		
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			getAdminMain().getDbManager().close(pstmt,rs);
		}

	}

	//하위카테고리 가져오기
	public void getSubList(String name) { //top,down,accessary,shoes
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select * from subcategory where topcategory_id=(select topcategory_id from topcategory where name=?)";
//		String sql = "select * from subcategory where topcategory_id=(select topcategory_id from topcategory where name='"+name+"')";

		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			pstmt.setString(1, name); //바인드 변수 지정
			rs = pstmt.executeQuery();

			ArrayList list = new ArrayList(); //상위 카테고리에 등록된 하위 카테고리
			while(rs.next()) {
				list.add(rs.getString("name"));
			}
			//모두 담겨지면, 이차원 리스트에 추가해놓자!
			subList.add(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			getAdminMain().getDbManager().close(pstmt,rs);
		}

	}
	
	
	//트리노드 생성하기
	public DefaultMutableTreeNode getCreatedNode (String parentName,ArrayList childName){
		//상위노드(부모노드)  생성
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);
		
		//넘겨받은 매개변수인 ArrayList만큼 반복하여 부모노드에 자식노드 부착!!
		for(int i=0;i<childName.size();i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}
		
		return parent;
		
		
	}
	
	//상품가져오기
	public void getProductList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql=null;
		
		if(name==null) {
			//안넘어오면 모든상품 가져오기
			sql = "select *from product";
		}else {
			//name값이 넘어오면 조건 쿼리 수행
			sql = "select *from product where subcategory_id=(select subcategory_id from subcategory where name='"+name+"')";
		}
		
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//메타정보를 이용하여 ProductModel의 column ArrayList를 채우자
			ResultSetMetaData meta=rs.getMetaData();
			ArrayList<String> columnNames = new ArrayList<String>();
			
			for(int i =1; i<=meta.getColumnCount();i++) {
				String colName =meta.getColumnName(i); //컬럼명 추출
				columnNames.add(colName);
			}
			
			//rs의 레코드를 ProductModel의 record ArrayList에 채우자
			ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
			while(rs.next()) {
				ProductVO vo = new ProductVO(); //비어있는 vo를 생성해서 rs의 값들을 채워넣기 위해!
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setSubcategory_id(rs.getInt("subcategory_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				
				productList.add(vo); //방금 생성하고 하나의 레코드가 채워진 vo를 ArrayList에 추가하자
			}
			model = new ProductModel();
			model.record = productList; //레코드 정보 대입
			model.column=columnNames; //컬럼 정보 대입
			table.setModel(model); //테이블에 방금 생성한 모델 적용!!
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			getAdminMain().getDbManager().close(pstmt,rs);
		}
		
		
		
	}
	
	//보여질 컨텐트와 가려질 컨텐트를 제어하는 메서드
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj); //제거될 자
		this.add(addObj);//부착될 자
		((JPanel)addObj).updateUI();
	}
	

}
