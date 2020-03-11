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
		
		// 수정 - detail  *************  비밀번호가 맞는지 틀리는지 = boolean
		public boolean boardUpdate(BoardVO vo)
		{
			boolean bCheck=false;
			try {
				getConnection();
				//1)비밀번호 검색 
				String sql="SELECT pwd FROM replyBoard "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
			
				//? 값을 채워서 DB에 보내야 겠지
				ps.setInt(1, vo.getNo());
				ResultSet rs=ps.executeQuery();
				rs.next();
				
				//2)맞는지 여부
				String db_pwd=rs.getString(1);
				rs.close();
				
				if(db_pwd.equals(vo.getPwd()))
				{
				 	bCheck=true;
				 	sql="UPDATE replyBoard SET "
				 			+ "name=?,subject=?,content=? "
				 			+ "WHERE no=?";
				 	ps=conn.prepareStatement(sql);
				 	ps.setString(1,vo.getName());
				 	ps.setString(2, vo.getSubject());
				 	ps.setString(3, vo.getContent());
				 	ps.setInt(4,vo.getNo());
				 	
				 	ps.executeUpdate(); // commit ;
				}
				else
				{
					 bCheck=false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				disConnection();
			}
			return bCheck;
		}
		

		//답변 - list  (일종의 insert)
		public void replyInsert(int pno,BoardVO vo)
		{
			try {
				getConnection();
				
				//*************************************//
				conn.setAutoCommit(false);
				//************************************//
				
				
				//_id, _step, _tab 값을 DB에서 읽어와   (from pno)
				String sql="SELECT group_id,group_step,group_tab "
						+ "FROM replyBoard "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, pno);
				
				
				ResultSet rs=ps.executeQuery();
				rs.next();
				
				int gi=rs.getInt(1);
				int gs=rs.getInt(2);
				int gt=rs.getInt(3);
				
				rs.close();
				
				//★★답변형 게시판 핵심 Query★★★★★★★★★★★★★★★★★★★
				/*			DESC	   ASC
						 		gi 		gs		gt
					AAA		1		0		0 
					->B		1		0+1	1	  
					  ->C		1		1+1	2
					   ->D 	1		2+1	3
					->E		1		0+1	1
					->F		1		1		1   ==> 맨 위로 위치시키고 gs를 +1씩
				 */
				
				sql="UPDATE replyBoard SET "								//값을 수정해줘
					+ "group_step=group_step+1 "							//gs+1
					+ "WHERE group_id=? AND group_step>?";		// gi==입력값  gs>입력값인 조건에서
				ps=conn.prepareStatement(sql);
				ps.setInt(1, gi);
				ps.setInt(2, gs);
				
				
				ps.executeUpdate();
				//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
				
				
				//데이터 추가
				  sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) VALUES("
						     +"rb_no_seq.nextval,?,?,?,?,?,?,?,?)";
				
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2,  vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setString(4, vo.getPwd());
				
				ps.setInt(5, gi);
				ps.setInt(6, gs+1); // STEP
				ps.setInt(7, gt+1); // TAB
				ps.setInt(8, pno); // ROOT
				ps.executeUpdate();
				
				//depth 증가 = 바로 하위에 답변이 몇개 달렸니  (depth=0 ; delete 可)
				sql="UPDATE replyBoard SET "
						+ "depth=depth+1 "
						+ "WHERE no=?";
				
				ps=conn.prepareStatement(sql);
				ps.setInt(1, pno);
				ps.executeUpdate();
				
				
				/*★Transaction Program의 필요성 ; 다~ 취소할 수 있음 ★ 
				-if COMMIT; 이 날아가는데, 위의 sql문이 잘못 됐을 경우를 대비 =  ROLLBACK이 되지 않는 문제 발생
				-try catch에서 롤백 or 정상수행
				-UPDATE, DELTE, INSERT를 한 sql에서 섞어서 쓸 경우에 자주 발생한다
				 */
			
				conn.commit();
				//*************************************//
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
				//***********************************//
				try {
					conn.rollback();
				} catch (Exception e2) {}
				//***********************************//
				
			}finally {
				
				//***********************************//	
				try{
				    conn.setAutoCommit(true);
				} catch (Exception ex) {}
				//***********************************//	
				disConnection();
			}
		}
		//삭제 - list
		public int boardDelete(int no,String pwd)
		{
			int result=0;
			try {
				getConnection();
				String sql="SELECT pwd FROM replyBoard "
						+ "WHERE no=?";
				
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ResultSet rs=ps.executeQuery();
				rs.next();
				String db_pwd=rs.getString(1);
				rs.close();
				
				if(db_pwd.equals(pwd))
				{
					result=1;
					sql="SELECT root,depth FROM replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					rs=ps.executeQuery();
					rs.next();
					
					//DB에서 읽어온 값
					int root=rs.getInt(1);
					int depth=rs.getInt(2);
					rs.close();
					
					if(depth==0)
					{
						sql="DELETE FROM replyBoard "
								+ "WHERE no=?";
						ps=conn.prepareStatement(sql);
						ps.setInt(1, no);
						ps.executeUpdate();
					}
					else
					{
							String msg="관리자가 삭제한 게시물";
							sql="UPDATE replyBoard SET "
									+ "subject=?,content=? "
									+ "WHERE no=?";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
							ps=conn.prepareStatement(sql);
							ps.setString(1, msg);
							ps.setString(2, msg);
							ps.setInt(3, no);
							ps.executeUpdate();
							
							sql="UPDATE replyBoard SET "
									+ "depth=depth-1 "
									+ "WHERE no=?";
							ps=conn.prepareStatement(sql);
							ps.setInt(1, root);
							ps.executeUpdate();
					}
				}
				else
				{
					result=0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disConnection();
			}
			return result;
		}
		
}
