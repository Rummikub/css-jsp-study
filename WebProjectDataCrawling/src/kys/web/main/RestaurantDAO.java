package kys.web.main;

import java.sql.*;
import java.util.*;



public class RestaurantDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private final String URL = "jdbc:oracle:thin:@211.238.142.205:1521:XE";

	public RestaurantDAO() {
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
	
	//1) 정보 삽입
	public void restaurantInfoInsert(RestaurantVO vo) {
		try {
			/*
			NO       NOT NULL NUMBER        
				RNAME    NOT NULL NUMBER        
				OPENHOUR          DATE          
			RPHOTO            VARCHAR2(100) 
				INFO              CLOB          
				TEL               NUMBER        
			LINK              VARCHAR2(100) 
				MAPX              NUMBER        
				MAPY              NUMBER        
				ADDR1             VARCHAR2(100) 
				ADDR2             VARCHAR2(100) 
			RANK              NUMBER        
				PRICE             VARCHAR2(100) 
			MENU              VARCHAR2(100) 
			RESCHECK          VARCHAR2(1)   
			GRADE             NUMBER        

			 */
			getConnection();
			String sql = "INSERT INTO restaurant VALUES((SELECT NVL(MAX(no)+1,1) FROM restaurant),?,?,?,?,?,?,?,?,rNo,id,title,bubble,regdate,content,expdate)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());

			ps.executeQuery();
		} catch (Exception e) {
			System.out.print("Food Detail Info Insert():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}


	public boolean hasFoodVOName(String name) {
		boolean check = true;
		try {
			getConnection();
			String sql = "SELECT COUNT(*) FROM restaurant WHERE name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				check = false;
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("hasFoodName():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return check;
	}

	public int noOfName(String name) throws Exception {
		int no = 0;
		try {
			getConnection();
			String sql = "SELECT no FROM restaurant WHERE name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			no = rs.getInt(1);
			rs.close();
		} finally {
			disConnection();
		}
		return no;
	}
	
	
}
