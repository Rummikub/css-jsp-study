package com.sist.dao;

import java.sql.*;
import java.util.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public FoodDAO()
	{
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
	
	//(1)카테고리 읽기
	public List<CategoryBean> categoryListData()
	{
		List<CategoryBean> list=new ArrayList<CategoryBean>();
		try {
			getConnection();
			String sql="SELECT cateno,title,subject,poster "
					+ "FROM cateory "
					+ "ORDER BY cateno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next())
			{
				CategoryBean vo=new CategoryBean();
				vo.setCateno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setPoster(rs.getString(4));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	//(2)이미지 클릭하면 food.jsp로 넘어가기 - 페이지 넘겨야 되니까 cateno넘어와야해
	public CategoryBean categoryInfoData(int cateno)
	{
		CategoryBean vo=new CategoryBean();
		try {
			getConnection();
			String sql="SELECT title,subject FROM cateory "
					+ "WHERE cateno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cateno);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setTitle(rs.getString(1));
			vo.setSubject(rs.getString(2));
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
			
		}
		return vo;
	}
	
	//(3)10개씩 값을 갖고 들어와야 해  2단계:(메인 -> 카테고리 페이지 -> 상세보기)
	public List<FoodHouseBean> foodCategoryList(int cateno)
	{
		List<FoodHouseBean> list=new ArrayList<FoodHouseBean>();
		try
		{
			getConnection();
			String sql="SELECT no,image,title,score,address,tel "
					+ "FROM foodhouse "
					+ "WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cateno);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodHouseBean vo=new FoodHouseBean();
				vo.setNo(rs.getInt(1));
				
				//대표 이미지 한개만 출력
				String temp=rs.getString(2);
				temp=temp.substring(0,temp.indexOf("^"));
				
				vo.setImage(temp);
				
				vo.setTitle(rs.getString(3));
				vo.setScore(rs.getDouble(4));
				vo.setAddress(rs.getString(5));
				vo.setTel(rs.getString(6));
				list.add(vo);
			}
			rs.close();
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return list;
		
	}
	
	//(4) 3단계에서 상세보기 출력
	public FoodHouseBean foodDetailData(int no)
	{
		FoodHouseBean vo=new FoodHouseBean();
		try {
			getConnection();
			String sql="SELECT title,score,address,tel,type,price,image,good,bad,soso,no "
					+ "FROM foodhouse "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setTitle(rs.getString(1));
			vo.setScore(rs.getDouble(2));
			vo.setAddress(rs.getString(3));
			vo.setTel(rs.getString(4));
			vo.setType(rs.getString(5));
			vo.setPrice(rs.getString(6));
			vo.setImage(rs.getString(7));
			int good=rs.getInt(8);
			int bad=rs.getInt(9);
			int soso=rs.getInt(10);
			vo.setNo(rs.getInt(11));
			rs.close();
			
			/*
			[
	          ['Task', 'Hours per Day'],
	          ['Work',     11],
	          ['Eat',      2],
	          ['Commute',  2],
	          ['Watch TV', 2],
	          ['Sleep',    7]
	        ]
		 */
			String temp="[";
			temp+="['리뷰','현황',],['좋아요',"+good+"],['괜찮다',"+soso+"],['별로',"+bad+"]";
			temp+="]";
			
			vo.setTag(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		
		return vo;
	}
}
