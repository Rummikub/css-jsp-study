package com.sist.dao;
import java.util.*;
import java.sql.*;

public class BoardDAO {

		private Connection conn;
		private PreparedStatement ps;
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		
		public BoardDAO()
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
		
		public List<BoardVO> boardListData(int page)
		{
			//☆이제는  arraylist X -> list O   즉, 상위클래스로 받아라 ☆    List(ArrayList, LinkedList, Vector)
			List<BoardVO> list=new ArrayList<BoardVO>();
			try {
				getConnection();
				String sql="SELECT no,subject,name,regdate,hit,group_tab,num "
						+ "FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
						+ "FROM (SELECT no,subject,name,regdate,hit,group_tab "
						+ "FROM replyBoard ORDER BY group_id DESC, group_step ASC)) "
						+ "WHERE num BETWEEN ? AND ?";
				
				//Top-N  ::::: rownum은 중간부터 자를 수 없음 => num을 inline으로 주는 이유!  (rownum 번호가 1부터)
				int rowSize=10;
				int start=(rowSize*page)-(rowSize-1);
				int end=rowSize*page;
				
				//전송 + paging
				ps=conn.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					BoardVO vo=new BoardVO();
					vo.setNo(rs.getInt(1));
					vo.setSubject(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					vo.setHit(rs.getInt(5));
					vo.setGroup_tab(rs.getInt(6));
					
					list.add(vo);
				}
				
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally  {
				disConnection();
			}
			return list;
		}
		
		public int boardRowCount()
		{
			int count=0;
			try {
				getConnection();
				String sql="SELECT COUNT(*) FROM replyBoard";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				count=rs.getInt(1);
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disConnection();
			}
			return count;
		}
		
		//새글
		public void boardInsert(BoardVO vo)
		{
			try
			{
				getConnection();
				String sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id) VALUES("
						+ "rb_no_seq.nextval,?,?,?,?,(SELECT NVL(MAX(group_id)+1,1) FROM replyBoard))";
				
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setString(4, vo.getPwd());
				
				ps.executeUpdate();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally
			{
				disConnection();
			}
		}
		
		//내용보기( 조회수 증가) type=0  => 수정하기 (데이터 읽기) type=1
		public BoardVO  boardDetailData(int no, int type) 
		{
			BoardVO vo=new BoardVO();
			try {
				getConnection();
				String sql="";
				
				if(type==0) // 조회수 증가
				{
					sql="UPDATE replyBoard SET "
						 + "hit=hit+1 "
						 + "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();  
				}
				
				// 상세보기, 수정하기
				sql="SELECT no,name,subject,content,regdate,hit "
						+ "FROM replyBoard "
						+ "WHERE no=?"; //한개의 데이터를 가져오려면 PK가져와야 한다
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				//  데이터 읽기 시작
				ResultSet rs=ps.executeQuery();
				rs.next();
				
				// vo에 저장
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getDate(5));
				vo.setHit(rs.getInt(6));
				
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disConnection();
			}
			return vo;
		}
		// 수정
		
		//삭제
		
		//답변
}
