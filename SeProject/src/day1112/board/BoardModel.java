/*
 * JTable에게 정보를 제공하는 객체인 TableModel을 재정의하자!
 * *
 */
package day1112.board;

import javax.swing.table.AbstractTableModel;

public class BoardModel extends AbstractTableModel{
	/*
	 * JTable을 위한 메서드 재정의
	 * 즉, 아래의 메서드들은 JTable이 호출해 간다.
	 *  
	 * */
	
	String[] column= {"Board_id","title","writer","regdate","hit"};
	String[][]data = {}; //비어있는 2차원 배열 선언
	

	@Override
	public int getRowCount() { //레코드 수
		return data.length;
	}

	@Override
	public int getColumnCount() { //컬럼 수 
		return column.length;
	}
	
	//컬럼제목을 출력하기 위해선, 이미지원하는 메서드 오버라이드
	@Override
	public String getColumnName(int col) {
		return column[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
