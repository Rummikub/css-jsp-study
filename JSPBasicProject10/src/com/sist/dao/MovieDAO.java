package com.sist.dao;

import java.sql.*;
import java.util.*;

public class MovieDAO {

	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";

	public  MovieDAO(){
		try {
			Class.forName("oracle.jdbc.driver.Oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {	}
	}

	public void disConnection() {
		try {
			if (ps != null)	ps.close();
			if (conn != null)	conn.close();
		} catch (Exception e){}
	}

	//전체페이지출력
	public int movieTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/9.0) FROM movie";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex) 
		{ 
			ex.printStackTrace();
		}finally
		{
			disConnection();
		}
		return total;
	}
	// 목록출력
	public List<MovieBean> movieListData(int page)
	{
		List<MovieBean> list=new ArrayList<MovieBean>();
		try {
			getConnection();
			String sql="SELECT title,poster,num "
					+ "FROM (SELECT title,poster,rownum as num "
					+ "FROM (SELECT title,poster "
					+ "FROM movie ORDER BY mno ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			
			int rowSize=9;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				MovieBean vo=new MovieBean();
				vo.setTitle(rs.getString(1));
				vo.setPoster(rs.getString(2));

				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	
}
