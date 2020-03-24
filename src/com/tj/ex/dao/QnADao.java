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

import com.tj.ex.dto.QnADto;
public class QnADao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static QnADao instance = new QnADao();
	public static QnADao getInstance() {
		return instance;
	}
	private QnADao() {}
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
	
	//湲� 紐⑸줉 肉뚮━湲� 
	public ArrayList<QnADto> QnaList(int startRow, int endRow){
		ArrayList<QnADto> qDto = new ArrayList<QnADto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT QNO, (SELECT MNAME FROM QNA Q, MEMBER M " + 
				"        WHERE Q.MID=M.MID AND QNA.QNO=QNO) ||" + 
				"    (SELECT ANAME FROM QNA Q, ADMIN M " + 
				"        WHERE Q.AID=M.AID AND QNA.QNO=QNO) WRITER , MID, AID, " + 
				"        QTITLE, QCONTENT, QFILENAME,  QGROUP, QSTEP, QINDENT, QHIT, QRDATE" + 
				"        FROM QNA ORDER BY QGROUP DESC, QSTEP)A )" + 
				"        WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    qNo		  = rs.getInt("qNo");
				String aId		  = rs.getString("aId");
				String mId		  = rs.getString("mId");
				String qTitle 	  = rs.getString("qTitle");
				String qContent   = rs.getString("qContent");
				String qFilename = rs.getString("qFilename");
				int    qGroup	  = rs.getInt("qGroup");
				int    qStep	  = rs.getInt("qStep");
				int    qIndent	  = rs.getInt("qIndent");
				int    qHit		  = rs.getInt("qHit");
				Date   qRdate	  = rs.getDate("qRdate");
				qDto.add(new QnADto(qNo, aId, mId, qTitle, qContent, qFilename, qGroup, qStep, qIndent, qHit, qRdate));
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
		
		return qDto;		
	}	
	
	// 湲� 媛쒖닔
	public int qnaTotCnt() {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM QNA";
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
	
	// 湲� 議고쉶�닔 �삱由ш린
	public void qnaHitUp(int qNo) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QHIT = QHIT+1 WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			pstmt.executeUpdate();
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
	}
	
	
	// 湲� �긽�꽭蹂닿린
	public QnADto qNaContentView(int qNo) {
		qnaHitUp(qNo);
		QnADto 		  	  qDto  = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT QNO, (SELECT MNAME FROM QNA Q, MEMBER M WHERE Q.MID=M.MID AND QNA.QNO=QNO) ||" + 
				"    (SELECT ANAME FROM QNA Q, ADMIN M WHERE Q.AID=M.AID AND QNA.QNO=QNO) WRITER, MID, AID," + 
				"   QTITLE, QCONTENT, QFILENAME, QGROUP, QSTEP, QINDENT, QHIT, QRDATE" + 
				"   FROM QNA WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String aId		  = rs.getString("aId");
				String mId		  = rs.getString("mId");
				String qTitle 	  = rs.getString("qTitle");
				String qContent   = rs.getString("qContent");
				String qFilename = rs.getString("qFilename");
				int    qGroup	  = rs.getInt("qGroup");
				int    qStep	  = rs.getInt("qStep");
				int    qIndent	  = rs.getInt("qIndent");
				int    qHit		  = rs.getInt("qHit");
				Date   qRdate	  = rs.getDate("qRdate");
				qDto = new QnADto(qNo, aId, mId, qTitle, qContent, qFilename, qGroup, qStep, qIndent, qHit, qRdate);
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
		return qDto;
	}
	
	// 湲��벐湲� (�썝湲�)
	
	public int qNaWrite(String aId, String mId, String qTitle, String qContent, String qFilename)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA (QNO, AID, MID, QTITLE ,QCONTENT, QFILENAME, QGROUP, QSTEP, QINDENT)" + 
				"    VALUES (QNA_SEQ.NEXTVAL, ?, ?, ?, ?,?, QNA_SEQ.CURRVAL, 0, 0 )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, mId);
			pstmt.setString(3, qTitle);
			pstmt.setString(4, qContent);
			pstmt.setString(5, qFilename);
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
	
	// 湲� �닔�젙
	public int qnaModify(String qTitle, String qContent, String qFilename, int qNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QTITLE=?, QCONTENT=?, QFILENAME=?,  QRDATE=SYSDATE" + 
				"    WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, qTitle);
			pstmt.setString(2, qContent);
			pstmt.setString(3, qFilename);
			pstmt.setInt(4, qNo);
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
	
	// 湲� �궘�젣
	public int qnaDelete(int qNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QNA WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "湲��궘�젣 �꽦怨�":"湲��궘�젣 �떎�뙣");
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
	// �떟蹂�湲� �벐湲� �쟾 step a
	private void qnaReplyStepA(int qGroup, int qStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QSTEP = QSTEP+1 WHERE QGROUP=? AND QSTEP>?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qGroup);
			pstmt.setInt(2, qStep);
			int result = pstmt.executeUpdate();
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
	}
	
	public int qnaReply(String aId, String mId, String qTitle, String qContent, 
				String qFilename, int qGroup, int qStep, int qIndent)  {
		int result = FAIL;
		qnaReplyStepA(qGroup, qStep);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA " + 
				"    (QNO, AID, MID,  QTITLE, QWRITER , QCONTENT, QFILENAME,QGROUP, QSTEP, QINDENT)" + 
				"    VALUES (QNA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, QNA_SEQ.CURRVAL ,0, 0 )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, mId);
			pstmt.setString(3, qTitle);
			pstmt.setString(4, qContent);
			pstmt.setString(5, qFilename);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "湲��벐湲곗꽦怨�":"湲��벐湲곗떎�뙣");
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
