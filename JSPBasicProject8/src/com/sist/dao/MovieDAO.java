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

	//기능(1)영화tab page변경하기
	public List<MovieBean> movieListData(int page)
	{
		List<MovieBean> list=new ArrayList<MovieBean>();
		try {
			getConnection();
			String sql="SELECT mno,title,poster,type,num "
					+ "FROM (SELECT mno,title,poster,type,rownum as num "
					+ "FROM (SELECT mno,title,poster,type "
					+ "FROM movie ORDER BY mno ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				MovieBean vo=new MovieBean();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setType(rs.getInt(4));
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
	
	//(2)전체페이지출력
	public int movieTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM movie";
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
	
	//(3) find 기능 만들기 
	public List<MovieBean> movieFindData(int page)
	{
		List<MovieBean> list=new ArrayList<MovieBean>();
		try {
			getConnection();
			String sql="SELECT mno,title,poster,genre,num "
					+ "FROM (SELECT mno,title,poster,genre,rownum as num "
					+ "FROM (SELECT mno,title,poster,genre "
					+ "FROM movie ORDER BY mno ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			
			int rowSize=50;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				MovieBean vo=new MovieBean();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setGenre(rs.getString(4));
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
	
	//(4) list
	public List<BoardBean> boardListData()
	{
		List<BoardBean> list=new ArrayList<BoardBean>();
		try {
			getConnection();
			String sql="SELECT no,subject,name,regdate,hit "
					+ "FROM board "
					+ "ORDER BY no DESC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BoardBean vo=new BoardBean();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				
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
	
	//(5) insert
	 public void boardInsert(BoardBean vo)
	 {
		 try
		 {
			 getConnection();
			 String sql="INSERT INTO board VALUES("
			 		+ "(SELECT NVL(MAX(no)+1,1) FROM board),?,?,?,?,SYSDATE,0)";
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, vo.getName());
			 ps.setString(2, vo.getSubject());
			 ps.setString(3, vo.getContent());
			 ps.setString(4, vo.getPwd());
			 
			 ps.executeUpdate();
		 }catch(Exception ex)
		 {
			ex.printStackTrace(); 
		 }finally
		 {
			 disConnection();
		 }
		 
	 }
}
	 
