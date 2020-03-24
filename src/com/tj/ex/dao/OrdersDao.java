package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.OrderDetailDto;
import com.tj.ex.dto.OrderDto;

public class OrdersDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	private static OrdersDao instance = new OrdersDao();
	public static OrdersDao getInstance() {
		return instance;
	}
	private OrdersDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public int orderInsert(String mId, String oName, String oPostalcode, String oAddress, String oTel,String oEmail)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ORDERS (ONO,MID,ONAME,OPOSTALCODE,OADDRESS,OTEL,OEMAIL) " + 
				"    VALUES (ORDERS_SEQ.NEXTVAL, ?,?, ?, ?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, oName);
			pstmt.setString(3, oPostalcode);
			pstmt.setString(4, oAddress);
			pstmt.setString(5, oTel);	
			pstmt.setString(6, oEmail);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	public OrderDto ordersMember (String oNo) {
		OrderDto oDto = new OrderDto();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql ="SELECT * FROM ORDERS WHERE ONO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId = rs.getString("mId");
				String oName = rs.getString("oName");
				String oPostalcode = rs.getString("oPostalcode");
				String oAddress = rs.getString("oAddress");
				String oTel = rs.getString("oTel");
				String oEmail = rs.getString("oEmail");
				Date oRdate = rs.getDate("oRdate");
				oDto = new OrderDto(oNo, mId, oName, oPostalcode, oAddress, oTel, oEmail, oRdate);
				
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage()+2);
			}
		}
		
		return oDto;
	}
	
}















