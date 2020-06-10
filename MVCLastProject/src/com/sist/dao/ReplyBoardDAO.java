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
	
	
	 
	public static String replyGetPwd(int no)
	{
		String pwd="";
		SqlSession session=ssf.openSession(true);
		try{
		
			pwd=session.selectOne("replyGetPwd",no);

		
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
	//★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 트랜젝션 프로그램을 짜본다★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 
	public static void replyReplyInsert(int pno,BoardVO vo)
	{
		SqlSession session=null; //Connection
		try {
			session=ssf.openSession();
			BoardVO pvo=session.selectOne("replyParentInfoData",pno);
			session.update("replyGroupStepIncrement",pvo);
			
			//ReplyInsert하기에 앞서서 group_id/step/tab 넣어줘야
			vo.setGroup_id(pvo.getGroup_id());
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			vo.setRoot(pno);
			
			//메소드 호출
			session.insert("replyReplyInsert",vo);
			session.update("replyDepthIncrement",pno);
			
			//Commit
			session.commit();
		}catch (Exception ex)
		{
			 System.out.println(ex.getMessage());
			 session.rollback(); //★★★★★★★★★★★★★★★★★★★★★★★★★★★★ transaction *(하나라도 오류나면 롤백시켜라)
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	//true; 삭제 ★★★★★★★★★★★★★★★update/delete/insert여러개★★★★★★★★★★ transaction *(실패하면 롤백시켜라)
	public static boolean replyDelete(int no,String pwd)
	{
		boolean bCheck=false;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			
			String db_pwd=session.selectOne("replyGetPwd",no);
			if(db_pwd.equals(pwd))
			{
				bCheck=true;
				BoardVO vo=session.selectOne("replyDeleteInfoData",no);
				if(vo.getDepth()==0)
				{
					//대댓글이 없다는 얘기지
					session.delete("replyDelete",no);
				}else
				{
					// 대댓이 있으면 처리 별도로 해줘야됨 replySubjectUpdate 의 매개변수 3개를 vo로 잡은거니까.
					vo.setSubject("관리자가 삭제한 게시물입니다.");
					vo.setContent("관리자가 삭제한 게시물입니다");
					vo.setNo(no);
					session.update("replySubjectUpdate",vo);
				}
				//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ROOT★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
				session.update("replyDepthDecrement",vo.getRoot()); // 상위 번호의 depth를 감소시키는 메소드를 호출했어
				
			}else
			{
				bCheck=false;
			}
			
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		} finally {
			if(session!=null)
				session.close();
		}
		return bCheck;
	}
}
