package day1116.publicapi;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MountainModel extends AbstractTableModel{
	/*
	 * 이제 이차원배열이 아니라, Vector를 선언해서 , 데이터와 컬럼명을 처리해보겠습니다.
	 * 백터는 컬렉션 프레임웍이니 배열처럼 생성시 크기를 명시하지 않아도 됩니다..그 만큼 유연합니다..
	 * */
	//복사하세요
	Vector<Mountain> data=new Vector<Mountain>();//근데 제너릭타입이 아니라서 뭔가 허전합니다..
											//제너릭타입으로 알맞는 자료형은 뭘까요? 우리는 지금 산을 가져와서 
											//테이블에 출력할 예정에용!!!!! 그럼 산이 들어와야 합니다
	//컬럼 제목 정보를 담는 백터 
	Vector<String> columnName = new Vector<String>(); //n이 빠졌네요
	
	//컬럼제목을 구성할 벡터 요소는 생성자에서 채웁시다!!!
	//컬럼명은 한국말로 채웁시다!!
	public MountainModel() {
		//컬럼구성
		columnName.add("ID");//산 아이디 
		columnName.add("산이름");//산 이름 
		columnName.add("소재지");//산 소재지 
		columnName.add("높이");//산 높이 	
		
		//데이터로 거짓말 구성 (테스트 용도)
		Mountain mt = new Mountain();
//		mt.setMntnid(1);
//		mt.setMntnnm("설악산");
//		mt.setMntninfopoflc("강원도");
//		mt.setMntninfohght(1000);
//		data.add(mt); //산 1개를 백터에 넣기
	}
	
	//이제 레코드의 수(행의 수)는 백터의 길이에서 가져오면 됨
	public int getRowCount() {
		return data.size();
	}
	
	//이제 컬럼의 수는 백터의 길이에서 가져오면 됨 
	public int getColumnCount() {
		return columnName.size();
	}
	//컬럼 제목을 출력하기 위한, 메서드 오버라이드 
	public String getColumnName(int col) { //col로 줄여주세요
		return columnName.get(col);//배열이 아니므로 get(인덱스) 로 가져와야 합니다
	}
	//2차원배열이나 벡터는 모두 배열구조이므로 지금까지는 [row][col]형태로 데이터를 추출하였다
	//하지만, 이제 백터에 있는 데이터는 VO이므로 [row]에다한 적급은 가능하지만 [col]에 대한 접근은 불가하다
	public Object getValueAt(int row, int col) {
		//해결책! 조건문 쓰기
		//즉 col이 0일때는 산의 아이디, 1일때 이름, 2일때 뭐 등등...
		
		System.out.println("row="+row+"col의 값"+col); //호출 시 컬럼값 확인하기 
		
		Mountain mt = data.get(row);
		String obj=null; //각 조건에 따라 반환될 데이터
		if(col==0) { //산 아이디 반환
			//row번째에서 산을 하나 꺼집어 내기
			obj = Integer.toString(mt.getMntnid());
		}else if(col==1) { //산의 이름
			obj = mt.getMntnnm();
		}else if(col==2) { //산의 위치
			obj = mt.getMntninfopoflc();
		}else if(col==3) { //산의 높이
			obj = Integer.toString(mt.getMntninfohght());
		}

		//이 메서드의 반환형이 오브젝트 형이므로, 객체형 (String,Integer 등)으로 반환해야 한다
		
		return obj;
	}
	
	
}