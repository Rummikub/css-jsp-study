package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
/*
 	프로그램 == 공통모듈 : 중복제거  / 핵심모듈 : 코딩이 가능하게 만든다 (Spring)
 	=======================================================AOP
 */
import java.util.*;
import com.sist.vo.*;

public class ReplyBoardDAO {
	private static SqlSessionFactory ssf;
	static 
	{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<BoardVO> replyListData(Map map)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession(); //=> DriverManager.getConnection();
			list=session.selectList("replyListData",map);
			
			/*
			 		1) SQL
			 		2) PreparedStatement  ps.setString(1,vo.get~)
			 		3) VO, ResultSet
			 */
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}finally
		{
			if(session!=null)
				session.close();  //=> disConnection();
			// session 안에는 Connection, PreparedStatement  
			// if(ps!=null) ps.close();
			// if(conn!=null) conn.close();
		}
		return list;
		
	}
	
	public static int replyTotalPage()
	{
		int total=0;
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			total=session.selectOne("replyTotalPage");
		}catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}finally
		{
			if(session!=null)
				session.close(); //★★반환 ! connection() 8개밖에 없으니까 꼭 반환해줘야된다 ★★★
		}
		return total;
		
	}
	
	public static BoardVO replyDetailData(int no)
	{
		BoardVO vo=new BoardVO(); 
		SqlSession session=ssf.openSession();
		try
		{
		session.update("hitIncrement",no); 
		session.commit();
		vo=session.selectOne("replyDetailData",no); 
		
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}finally
		{
			if(session!=null)
				session.close();
		}
		return vo; // 리턴한다
	}
	
	//글쓰기
	public static void replyInsert(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);	//★ autocommit
		try
		{
			session.insert("replyInsert",vo);
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public static BoardVO  boardUpdateData(int no)
	{
		BoardVO vo=new BoardVO();
		SqlSession session=ssf.openSession(true);
		try{
			session.commit();
			vo=session.selectOne("replyDetailData",no);

		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}finally
		{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	 
	public static void boardUpdate(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);
		try{
		
			session.update("boardUpdate",vo);

		
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}finally
		{
			if(session!=null)
				session.close();
		
		}
	
	}
	
	
	 
	public static String getPwd(int no)
	{
		String pwd="";
		SqlSession session=ssf.openSession(true);
		try{
		
			pwd=session.selectOne("getPwd",no);

		
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}finally
		{
			if(session!=null)
				session.close();
		}
		return pwd;
	
	}
	
}
