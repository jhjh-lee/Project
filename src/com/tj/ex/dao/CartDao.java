package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.CartDto;

public class CartDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static CartDao instance = new CartDao();
	public static CartDao getInstance() {
		return instance;
	}
	private CartDao() {}
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
	public int cartInsert(String mId, String pCode, int cNt)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CART (CNO,MID,PCODE,CNT) VALUES (CART_SEQ.NEXTVAL, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, pCode);
			pstmt.setInt(3, cNt);
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
	public ArrayList<CartDto> cartList(String mId){
		ArrayList<CartDto> Cdto = new ArrayList<CartDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT C.CNO CNO, C.MID MID, P.PFILENAME PFILENAME, P.PNAME PNAME, P.PCODE PCODE," + 
				"        C.CNT CNT, P.PPRICE PPRICE, (C.CNT*P.PPRICE) COST " + 
				"    FROM CART C, PRODUCT P " + 
				"        WHERE P.PCODE = C.PCODE AND MID=? ORDER BY C.CNO";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cNo = rs.getInt("cNo");
				String pCode = rs.getString("pCode");
				int cNt = rs.getInt("cNt");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int cost = rs.getInt("cost");
				String pFilename = rs.getString("pFilename");
				Cdto.add(new CartDto(cNo, mId, pCode, cNt, pName, pPrice, cost, pFilename));
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
		return Cdto;
	}
	public ArrayList<CartDto> cartSelectList(int cNo){
		ArrayList<CartDto> Cdto = new ArrayList<CartDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT C.CNO CNO, C.MID MID, P.PFILENAME PFILENAME, P.PNAME PNAME, P.PCODE PCODE," + 
				"        C.CNT CNT, P.PPRICE PPRICE, (C.CNT*P.PPRICE) COST " + 
				"    FROM CART C, PRODUCT P " + 
				"        WHERE P.PCODE = C.PCODE AND C.CNO=? ORDER BY C.CNO";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId = rs.getString("mId");
				String pCode = rs.getString("pCode");
				int cNt = rs.getInt("cNt");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int cost = rs.getInt("cost");
				String pFilename = rs.getString("pFilename");
				Cdto.add(new CartDto(cNo, mId, pCode, cNt, pName, pPrice, cost, pFilename));
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
		return Cdto;
	}
	
	public CartDto cDtos(int cNo) {
		CartDto Cdto = null;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT C.CNO, C.MID, P.PCODE, C.CNT,P.PNAME, P.PPRICE, (C.CNT*P.PPRICE) COST, P.PFILENAME " + 
				"    FROM CART C, PRODUCT P" + 
				"    WHERE P.PCODE = C.PCODE AND CNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId = rs.getString("mId");
				String pCode = rs.getString("pCode");
				int cNt = rs.getInt("cNt");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int cost = rs.getInt("cost");
				String pFilename = rs.getString("pFilename");
				Cdto = new CartDto(cNo, mId, pCode, cNt, pName, pPrice, cost, pFilename);
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
		return Cdto;
	}
	public int carttDelte(int cNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM CART WHERE CNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cNo);
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
	public int cartModify(int cNt, int cNo)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE CART SET CNT=? WHERE CNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cNt);
			pstmt.setInt(2, cNo);
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
	public int cartOrderDelte(String mId) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM CART WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
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
	
}
