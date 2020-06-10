package kys.web.main;


import java.sql.*;
import java.util.*;

public class ReviewDAO {
		
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private final String URL = "jdbc:oracle:thin:@211.238.142.200:1521:XE";

	public ReviewDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.print("FoodDAO():");
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			System.out.print("getConnection():");
			e.printStackTrace();
		}
	}

	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.print("disConnection():");
			e.printStackTrace();
		}
	}

	
	//ReviewVO로 바꿔야 
		public void reviewInsert(myReviewVO vo) {
			try {
				getConnection();
				String sql = "INSERT INTO review (reviewno,memberid,title,regdate,grade,type) VALUES((SELECT NVL(MAX(reviewno)+1,1) FROM restaurant),?,?,?,?,2)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vo.getMemberid());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getRegdate());
				ps.setInt(4, vo.getGrade());
				ps.setString(5, vo.getRegdate());
				ps.setString(6, vo.getContent());
				ps.setString(7, vo.getExpdate());
				ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("reviewInsert():");
				e.printStackTrace();
			} finally {
				disConnection();
			}
		}

		public boolean hasReview(int rNo) {
			boolean bCheck = false;

			try {
				getConnection();
				String sql = "SELECT CASE WHEN EXISTS (SELECT 1 FROM review WHERE rNo = ?) THEN 1 ELSE 0 END FROM DUAL";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rNo);
				ResultSet rs = ps.executeQuery();
				rs.next();
				if(rs.getInt(1)==1) bCheck = true;
				rs.close();
			} catch (Exception e) {
				System.out.println("hasReview():");
				e.printStackTrace();
				return true;
			} finally {
				disConnection();
			}

			return bCheck;
		}

}
