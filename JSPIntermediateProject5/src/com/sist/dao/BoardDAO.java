package com.sist.dao;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
//DAO 기능설정
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

	
/*
		자바 
		클래스의 구성요소
		================
		1) 멤버변수
			- 인스턴스 : new를 이용해서 메모리 할당 => 생성
			- 정적변수 (static) : JVM이 클래스를 읽기 시작 => 생성
		2) 메소드
		3) 생성자
		
		========================================
		필요시 멤버변수에 대한 초기화
		=	명시적 방법 ;  값을 바로 넣음
				public class A
				{
					int a=getNumber(): (X)             [ 선언만 가능 구현 (메소드 호출, 제어문,연산자 사용)은 안됨 ]
					static int b=20
					===========> 외부나 메소드를 이용해 초기화 불가능
					public int getNumber()
					{
					 		return 10;
					 }
				}
		
		=  생성자를 이용하는 방식 ; 외부에서 파일 읽기, 네트워크 연결, DB연결, XML파싱 
																	[ 구현 가능 ]
		=  초기화 블록 이용 ;
				-  인스턴스블록 => 변수가 인스턴스일 때
				; public class A
				{
						int a;
						{
								a=getNumber()
						}
				}
				
				-  static 블록 => static일 경우  (메모리 할당 안한다)
				public class A
				{
					static int b;
					
					static
					{
						b=getNumber()
					}
				}
				
				============================== 자동호출
		
		순서
		===
			=> 명시적 방법 => 초기화 블록 => 생성자
 */
public class BoardDAO {
	private static SqlSessionFactory ssf;	
	static
	{
		//파싱 - get/disConnection()
		// ID, SQL 을 Map에저장				
		// ID입력 SQL 실행 결과 요청
		try
		{
			// XML 파일 읽기 (orx.ibatis.io) -> getConnection역할 
			Reader reader=Resources.getResourceAsReader("config.xml");
			// Spring,Mybatis => classpath = src위치에 넣으면 자동인식해줌
			// 파싱 								(xml파서기를 포함하고 있음)
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e)
		{
				System.out.println(e.getMessage());
		}
		
	}
	
	
	//목록 읽기  - static은 메모리 할당을 별도로 요하지 않는 장점
	public static List<BoardVO> boardListData(Map map)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=ssf.openSession();  // connection개념 (세션생성)
		list=session.selectList("boardListData",map); // 데이터를 가져오고
		//									----------------    -----	
		//											id				parameterType
		// seletList(여러개) selectOne(한개호출)
		
		
		//★connection 반환
		session.close();
		
		
		
		return list; // 리턴한다
	}
	
	//상세보기 
	public static  BoardVO boardDetailData(int no)
	{
			BoardVO vo=new BoardVO(); // 객체 생성
			SqlSession session=ssf.openSession(); // 세션생성  
			session.update("hitIncrement",no); 
			
			//★ 조회수가 왜 증가를 안할까 - auto commit 이 안된대....
			session.commit();
			
			
			vo=session.selectOne("boardDetailData",no); // 데이터 가져와서
			//SqlSession 안에는 ~ Generics 타입이 들어간다 (자동으로 형변환해준다)
			
			//★connection 반환
			session.close();
			return vo; // 리턴한다
	}
	
	
	//총페이지
	public static int boardTotalPage()
	{
		int total=0;
		SqlSession session=ssf.openSession();
		total=session.selectOne("boardTotalPage");
		
		//★connection 반환
		session.close();
		return total;
	}
	
	//글쓰기
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);	//★ autocommit
		session.insert("boardInsert",vo);
		session.close();
	}
	
	//수정하기 목록출력
	public static BoardVO  boardUpdateData(int no)
	{
		BoardVO vo=new BoardVO();
		SqlSession session=ssf.openSession(true);

	//	session.commit();
		vo=session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
	
	//수정2 
	public static void boardUpdate(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);
		session.delete("boardUpdate",vo);
		session.close();
	
	}
}
