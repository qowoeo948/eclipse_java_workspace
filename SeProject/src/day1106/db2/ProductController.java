/*
 * JTable은 디자인에 불과하므로, 이 클래스로부터 데이터에 대한 정보를 가져간다..
 * 따라서 TableModel인터페이스를 구현한 객체인 AbstractTableModel을 상속받자!!
 * */

package day1106.db2;

import javax.swing.table.AbstractTableModel;

public class ProductController extends AbstractTableModel{
	String[][] data = {};
	String[] column = {"product_id","subcategory_id","product_name","brand","price","filename"};
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}

	//컬럼의 제목 가져오기
	@Override
	public String getColumnName(int col) {
		return column[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
