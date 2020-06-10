package com.sist.dao;
import java.sql.ResultSet;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.BoardReplyVO;
public class FreeBoardReplyDAO {
	private static SqlSessionFactory ssf;
	static{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<BoardReplyVO> replyListData(Map map)
	{
		List<BoardReplyVO> list=new ArrayList<BoardReplyVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyListData2",map);
			
			//?????????????????MyBatis??????????????????//
			list=(ArrayList<BoardReplyVO>)map.get("pResult");
			//ArrayList cannot be cast to java.sql.ResultSet
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static void replyInsert(Map map)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyInsert2",map);  //프로시저 호출
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	
// Model => DAO (매개변수 전송 ) = 매개변수가 클래스면 => 값 변경, 추가가 가능함 	
	public static int replyTotalPage(Map map) //<( call by ref.)
	{
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyTotalPage2",map);  //< 같은 map ( call by ref.)
			//OUT변수가 이미 
			total=(int)map.get("pTotal");
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static void replyUpdate(Map map)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyUpdate2",map);  //프로시저 호출
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static void replyReplyInsert(Map map)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyReplyInsert2",map);  //프로시저 호출
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	
	public static void replyDelete(Map map)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyDelete2",map);  //프로시저 호출
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
	}
}
