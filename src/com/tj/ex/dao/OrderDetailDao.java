package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.OrderDetailDto;

public class OrderDetailDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static OrderDetailDao instance = new OrderDetailDao();
	public static OrderDetailDao getInstance() {
		return instance;
	}
	private OrderDetailDao() {}
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
	// 二쇰Ц�젙蹂� �엯�젰
	public int orderDetailInsert(String pCode, int cNt, int cost)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql ="INSERT INTO ORDERDETAIL (ODNO, ONO, PCODE, CNT, COST) " + 
				"    VALUES " + 
				"    (ORDERDETAIL_SEQ.NEXTVAL,   ORDERS_SEQ.CURRVAL     , ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCode);
			pstmt.setInt(2, cNt);
			pstmt.setInt(3, cost);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "ORDERd二쇰Ц�꽦怨�":"ORDERd二쇰Ц�떎�뙣");
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
	// �궡 二쇰Ц由ъ뒪�듃 蹂닿린
	public ArrayList<OrderDetailDto> orderDetailList(String mId, int startRow, int endRow){
		ArrayList<OrderDetailDto> odDto = new ArrayList<OrderDetailDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql ="SELECT * FROM (SELECT ROWNUM RN , A.* FROM" + 
				"    (SELECT P.PFILENAME, P.PNAME, O.ONO, O.MID, D.ODNO, D.CNT, D.COST, O.ORDATE, P.PCODE  " + 
				"        FROM ORDERS O, ORDERDETAIL D,  PRODUCT P " + 
				"        WHERE D.ONO= O.ONO AND P.PCODE = D.PCODE AND O.MID=?" + 
				"        ORDER BY O.ORDATE)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String oDno = rs.getString("oDno");
				String oNo = rs.getString("oNo");
				String pCode = rs.getString("pCode");
				int cNt = rs.getInt("cNt");
				int cost = rs.getInt("cost");
				String pFilename = rs.getString("pFilename");
				String pName = rs.getString("pName");
				Date oRdate = rs.getDate("oRdate");
				
				odDto.add(new OrderDetailDto(oDno, oNo, pCode, cNt, cost, mId, pFilename, pName, oRdate));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage()+odDto.toString());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage()+2);
			}
		}
		return odDto;
	}
	// �궡 二쇰Ц由ъ뒪�듃 媛쒖닔
	public int orderDetailTotCnt(String mId) {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM ORDERS O, ORDERDETAIL D WHERE D.ONO= O.ONO AND O.MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	//諛곗넚�긽�꽭 由ъ뒪�듃
	public ArrayList<OrderDetailDto> orderContentList(String oNo){
		ArrayList<OrderDetailDto> odDto = new ArrayList<OrderDetailDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql ="SELECT P.PFILENAME, P.PNAME, O.ONO, O.MID, D.ODNO, D.CNT, D.COST, O.ORDATE, P.PCODE  " + 
				"        FROM ORDERS O, ORDERDETAIL D,  PRODUCT P " + 
				"        WHERE D.ONO= O.ONO AND P.PCODE = D.PCODE AND O.ONO=? " + 
				"        ORDER BY O.ORDATE";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String oDno = rs.getString("oDno");
				String pCode = rs.getString("pCode");
				int cNt = rs.getInt("cNt");
				int cost = rs.getInt("cost");
				String mId = rs.getString("mId");
				String pFilename = rs.getString("pFilename");
				String pName = rs.getString("pName");
				Date oRdate = rs.getDate("oRdate");
				
				odDto.add(new OrderDetailDto(oDno, oNo, pCode, cNt, cost, mId, pFilename, pName, oRdate));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage()+odDto.toString());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage()+2);
			}
		}
		return odDto;
	}
}
