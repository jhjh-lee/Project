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

import com.tj.ex.dto.ProductDto;

public class ProductDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static ProductDao instance = new ProductDao();
	public static ProductDao getInstance() {
		return instance;
	}
	private ProductDao() {}
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
	
	public ArrayList<ProductDto> productlistFileBoard(int startRow, int endRow){
		ArrayList<ProductDto> Pdto = new ArrayList<ProductDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT * FROM PRODUCT ORDER BY PRDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pCode 	 = rs.getString("pCode");
				String pName 	 = rs.getString("pName");
				String pDes		 = rs.getString("pDes");
				String pType	 = rs.getString("pType");
				String pFilename = rs.getString("pFilename");
				int    pPrice    = rs.getInt("pPrice");
				int    pStock    = rs.getInt("pStock");
				Date   pRdate    = rs.getDate("pRdate");
				
				Pdto.add(new ProductDto(pCode, pName,pDes, pType, pFilename,pPrice, pStock, pRdate));
				
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
		return Pdto;
	}
	public ArrayList<ProductDto> productTypelistFileBoard(String pType, int startRow, int endRow){
		ArrayList<ProductDto> Pdto = new ArrayList<ProductDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT * FROM PRODUCT WHERE PTYPE=? ORDER BY PRDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pCode 	 = rs.getString("pCode");
				String pName 	 = rs.getString("pName");
				String pDes		 = rs.getString("pDes");
				String pFilename = rs.getString("pFilename");
				int    pPrice    = rs.getInt("pPrice");
				int    pStock    = rs.getInt("pStock");
				Date   pRdate    = rs.getDate("pRdate");
				Pdto.add(new ProductDto(pCode, pName,pDes, pType, pFilename,pPrice, pStock, pRdate));
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
		return Pdto;
	}
	public int productAllTotCnt() {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM PRODUCT";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
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
	public int productTypeTotCnt(String pType) {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM PRODUCT WHERE PTYPE=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pType);
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
	public int productInsert(String pCode, String pName,String pDes, String pType, String pFilename, int pPrice, int pStock)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PRODUCT (PCODE,PNAME,PDES,PTYPE,PFILENAME,PPRICE,PSTOCK) VALUES (?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pCode );
			pstmt.setString(2,pName );
			pstmt.setString(3,pDes );
			pstmt.setString(4,pType );
			pstmt.setString(5,pFilename );
			pstmt.setInt(6, pPrice);
			pstmt.setInt(7, pStock);	
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
	
	
	public ProductDto productContentView(String pCode) {
		ProductDto Pdto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM PRODUCT WHERE PCODE=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pName 	 = rs.getString("pName");
				String pType 	 = rs.getString("pType");
				String pDes		 = rs.getString("pDes");
				String pFilename = rs.getString("pFilename");
				int    pPrice    = rs.getInt("pPrice");
				int    pStock    = rs.getInt("pStock");
				Date   pRdate    = rs.getDate("pRdate");
				Pdto = new ProductDto(pCode, pName,pDes, pType, pFilename, pPrice, pStock, pRdate);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(Pdto.toString());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return Pdto;
	}
	public int productUpdate(String pName, String pType, String pFilename, int pPrice, int pStock, String pCode)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET PNAME=?, PTYPE=?,PFILENAME=?,PPRICE=?,PSTOCK=? WHERE PCODE=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pName);
			pstmt.setString(2,pType);
			pstmt.setString(3,pFilename);
			pstmt.setInt(4, pPrice);
			pstmt.setInt(5, pStock);
			pstmt.setString(6,pCode );
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS);
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
	public int productDelte(String pCode) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PRODUCT WHERE PCODE=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCode);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS);
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
	public int productStockUpdate(int pStock, String pCode)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET PSTOCK=PSTOCK-? WHERE PCODE=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pStock);
			pstmt.setString(2, pCode);
			
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS);
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
