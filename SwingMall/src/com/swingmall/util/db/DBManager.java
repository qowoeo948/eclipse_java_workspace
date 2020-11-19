/*
 * 데이터베이스와 관련된 업무를 처리하고, 또는 중복되는 로직을 공통화시켜
 * 재사용성을 높이기 위한 클래스
 * */

package com.swingmall.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user="user1104";
	private String password="user1104";
	

	public Connection connect() {
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//쿼리문 수행과 관련된 자원을 닫아주는 메서드
	//DML!!
	public void close(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//SELECT!!
	public void close(PreparedStatement pstmt,ResultSet rs) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void disConnect(Connection con) {
		if(con!=null) {
			try {
				con.close();
				System.exit(0);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

}
