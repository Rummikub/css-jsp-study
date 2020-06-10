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
INSERT INTO restaurant(no,rname,openhour) VALUES((SELECT NVL(MAX(no)+1,1)FROM restaurant),'kim','12');
			 */
			getConnection();
			String sql = "INSERT INTO restaurant(no,rname,openhour,info,tel,mapx,mapy,addr1,addr2,price,post) VALUES((SELECT NVL(MAX(no)+1,1)"
					+ "FROM restaurant),?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getRname());
			ps.setString(2, vo.getOpenhour());
			ps.setString(3, vo.getInfo());
			ps.setString(4, vo.getTel());
			ps.setDouble(5, vo.getMapx());
			ps.setDouble(6, vo.getMapy());
			ps.setString(7, vo.getAddr1());
			ps.setString(8, vo.getAddr2());
			ps.setString(9, vo.getPrice());
			ps.setString(10, vo.getPost());
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("Restaurant Info Insert():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}


	public boolean hasFoodVOName(String rname) {
		boolean check = true;
		try {
			getConnection();
			String sql = "SELECT COUNT(*) FROM restaurant WHERE rname = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rname);
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

	public int noOfName(String rname) throws Exception {
		int no = 0;
		try {
			getConnection();
			String sql = "SELECT no FROM restaurant WHERE rname = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rname);
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
