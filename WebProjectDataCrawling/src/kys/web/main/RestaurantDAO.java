
package kys.web.main;

import java.sql.*;




public class RestaurantDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.200:1521:XE";

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
			String sql = "INSERT INTO restaurant (no,rname,openhour,info,tel,mapx,mapy,addr1,addr2,price,grade) VALUES("
					+ "(SELECT NVL(MAX(no)+1,1),?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getRname());
			ps.setString(2, vo.getOpenhour());
			ps.setString(3,vo.getInfo());
			ps.setInt(4, vo.getTel());
			ps.setInt(5, vo.getMapx());
			ps.setInt(6, vo.getMapy());
			ps.setString(7, vo.getAddr1());
			ps.setString(8, vo.getAddr2());
			ps.setString(9, vo.getPrice());
			ps.setInt(10, vo.getGrade());

			ps.executeUpdate();

			
			
			
		} catch (Exception e) {
			System.out.print("Restaurant Info Insert():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}







	
	//Review  	id,grade,regdate,title,content,expdate
	public void reviewInsert(ReviewVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO review VALUES (reviewno,id,grade,regdate,title,content,expdate) VALUES("
					+ "(SELECT NVL(MAX(no)+1,1),?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.get);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("reviewInsert():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

*/
