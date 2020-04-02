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
			String sql="SELECT mno,title,poster,num "
					+ "FROM (SELECT mno,title,poster,rownum as num "
					+ "FROM (SELECT mno,title,poster "
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
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));

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
	
	public MovieBean movieDetailData(int mno)
	{
		MovieBean vo=new MovieBean();
		try
		{
			getConnection();
			String sql="SELECT mno,title,poster,score,genre,regdate,time,grade,director,actor,story "
					+ "FROM movie "
					+ "WHERE mno=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, mno);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setScore(rs.getDouble(4));
			vo.setGenre(rs.getString(5));
			vo.setRegdate(rs.getString(6));
			vo.setTime(rs.getString(7));
			vo.setGrade(rs.getString(8));
			vo.setDirector(rs.getString(9));
			vo.setActor(rs.getString(10));
			vo.setStory(rs.getString(11));
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			disConnection();
		}
		return vo;
	}
	
}