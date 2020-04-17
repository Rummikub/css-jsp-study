package com.sist.dao;

import java.util.*;

import com.sist.vo.BoardVO;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class FreeBoardDAO {
	private Connection conn;
	private CallableStatement cs; // procedure 호출
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public FreeBoardDAO()
	{
		try {
			//드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// SqlSession session=ssf.openSession();
	//연결
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {}
	}
	
	//session.close();
	//해제
	public void disConnection()
	{
		try {
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	
	/*
	 CREATE OR REPLACE PROCEDURE boardListdata(
    	pStart NUMBER,
    	PEND   NUMBER,
    	PRESULT OUT SYS_REFCURSOR
	 */
	
	//session.selectList();
	public List<BoardVO> freeboardListData(int page)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		try 
		{
			getConnection();
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			String sql="{CALL boardListData(?,?,?)}";
			
			cs=conn.prepareCall(sql); // 프로시저 호출
			
			cs.setInt(1, start);
			cs.setInt(2, end);
			// OUT변수에 값채우는 함수는 regiesterOutParameter
			cs.registerOutParameter(3, OracleTypes.CURSOR);
		
			//실행요청 UPDATE다 무조건.
			cs.executeUpdate(); 
			
			//Object로 CURSOR를 받아옴
			//결과값  ::::::::::::::::::::CURSOR -> ResultSet
			ResultSet rs=(ResultSet)cs.getObject(3);
			
			while(rs.next())  // CURSOR에 만든 select문 변수 순서대로
			{
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	
	
	//총페이지구하기
	/*
	 CREATE OR REPLACE PROCEDURE BOARDTOTALPAGE
	 		(PTOTAL OUT NUMBER
	 */
	public int freeboardTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="{CALL BOARDTOTALPAGE(?)}";
			cs=conn.prepareCall(sql);
			
			cs.registerOutParameter(1, OracleTypes.INTEGER); //정수 저장공간 (메모리 생성)
			
			//실행
			cs.executeUpdate();
			//메모리에서 결과값 가져오기
			total=cs.getInt(1);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			disConnection();
		}
		return total;
	}
	/*
	 CREATE OR REPLACE PROCEDURE boardInsert(
    PNAME BOARD.NAME%TYPE,
    PSUBJECT BOARD.SUBJECT%TYPE,
    PCONTENT BOARD.CONTENT%TYPE,
    PPWD    BOARD.PWD%TYPE
	 */
	public void freeboardInsert(BoardVO vo)
	{
		try {
			getConnection();
			String sql="{CALL boardInsert(?,?,?,?)}";
			cs=conn.prepareCall(sql);
			
			cs.setString(1, vo.getName());
			cs.setString(2, vo.getSubject());
			cs.setString(3, vo.getContent());
			cs.setString(4, vo.getPwd());
		
			cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	} 
		
	/*
	CREATE OR REPLACE PROCEDURE BOARDINFODATA(
   		PNO BOARD.NO%TYPE,
   		PTYPE NUMBER,
   		PRESULT OUT SYS_REFCURSOR  
	 */
	
	// 상세보기랑 수정하기를 동시에!!
	public BoardVO freeboardInfoData(int no,int type)
	{
		BoardVO vo=new BoardVO();
		try {
			getConnection();
			String sql="{CALL boardInfoData(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setInt(2,type);

			//*********OUT-registeroutparameter --- 공간을 CURSOR크기만큼 만들어라
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			cs.executeUpdate();
			
			ResultSet rs=(ResultSet)cs.getObject(3);
			//글 한개를 상세보기할것! 
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			disConnection();
		}
		return vo;
	}
	/*
	CREATE OR REPLACE PROCEDURE BOARDUPDATE(
    PNO BOARD.NO%TYPE,
    PNAME BOARD.NAME%TYPE,
    PSUBJECT BOARD.SUBJECT%TYPE,
    PCONTENT BOARD.CONTENT%TYPE,
    PPWD    BOARD.PWD%TYPE
    PRESULT OUT VARCHAR2
	 */
	public boolean freeboardUpdate(BoardVO vo)
	{
		boolean bCheck=false;
		try {
			getConnection();
			String sql="{CALL boardUpdate(?,?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, vo.getNo());
			cs.setString(2, vo.getName());
			cs.setString(3, vo.getSubject());
			cs.setString(4, vo.getContent());
			cs.setString(5, vo.getPwd());
			cs.registerOutParameter(6, OracleTypes.VARCHAR);
			cs.executeUpdate();
			//&*******************************
			String result=cs.getString(6);
			bCheck=Boolean.parseBoolean(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return bCheck; 
	}
	/*
CREATE OR REPLACE PROCEDURE BOARDDELETE(
    PNO BOARD.NO%TYPE,
    PPWD BOARD.PWD%TYPE,
    PRESULT OUT VARCHAR2
)
	 */
	
	public boolean freeboardDelete(int no, String pwd)
	{
		boolean bCheck=false;
		try {
			getConnection();
			String sql="{CALL boarddelete(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setString(2, pwd);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeUpdate();
			//&*******************************
			String result=cs.getString(3);
			bCheck=Boolean.parseBoolean(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return bCheck; 
	}
}
