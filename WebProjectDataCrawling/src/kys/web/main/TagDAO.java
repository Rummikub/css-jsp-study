package kys.web.main;

import java.sql.*;

public class TagDAO {

	private Connection conn;
	private PreparedStatement ps;
	//private ResultSet rs;									//205
	private final String URL="jdbc:oracle:thin:@211.238.142.200:1521:XE";
	
	public TagDAO() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disConnection() 
	{
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*public void tagInsert(TagVO vo) 
	{
		try
		{
			getConnection();
			String sql="INSERT INTO restaurranttag "
					+ "VALUES(?,resttag_tagcode_seq.nextval,0)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getTagname());
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}*/
	
	public void tagInsert(TagVO vo) 
	{
		try
		{
			getConnection();
			String sql="INSERT INTO TAGDATA(rname,tagname,tagcode,count) "
					+ "VALUES(?,?,(SELECT NVL(MAX(TAGCODE)+1,1) FROM TAGDATA),0)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getRname());
			ps.setString(2, vo.getTagname());
			ps.executeUpdate();
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	
	
	//rname으로 no찾아오기..
	public int getLocNoByName(String rname) {
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
		} catch (Exception e) {

		} finally {
			disConnection();
		}
		return no;
	}

}
