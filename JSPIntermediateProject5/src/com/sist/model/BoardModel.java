package com.sist.model;
// Java ; JSP <%%>부분에 들어갔던 코딩을 모아 놓은 것	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;

public class BoardModel {
	
		// 목록보기
		public void boardListData(HttpServletRequest request)
		{
			String page=request.getParameter("page");
			if(page==null)
					page="1";
			int curpage=Integer.parseInt(page);
			int rowSize=10;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			
			Map map=new HashMap();
			map.put("start", start);
			map.put("end", end);
			
			List<BoardVO> list=BoardDAO.boardListData(map);
			int totalpage=BoardDAO.boardTotalPage();
			/*
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			 Date date=new Date();
			 String today=sdf.format(date)
			 */
			//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
			String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			

			//JSP로 결과값 전송
			request.setAttribute("list", list);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("today", today);
		}
		
		// detail -> 값출력 req실어서 값을 보내주는 용도
		public void boardDetailData(HttpServletRequest request)
		{
			String no=request.getParameter("no");
			BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
			
			//detail.jsp로 전송
			request.setAttribute("vo", vo);
			
		}
		
		// insert -> _ok 화면이동  (model = 비즈니스로직 , 모든 기능 코딩은 자바에 있음)
		public void boardInsert(HttpServletRequest request, HttpServletResponse response)
		{    
			  try
			  {
				request.setCharacterEncoding("UTF-8");
				String name=request.getParameter("name");
				String subject=request.getParameter("subject");
				String content=request.getParameter("content");
				String pwd=request.getParameter("pwd");
				
				BoardVO vo=new BoardVO();
				vo.setName(name);
				vo.setSubject(subject);
				vo.setContent(content);
				vo.setPwd(pwd);
				
				BoardDAO.boardInsert(vo);
				response.sendRedirect("list.jsp");
			  } catch (Exception ex) {}

		}
		//update list 
		  public void boardUpdateData(HttpServletRequest request)
		   {
			   String no=request.getParameter("no");
			   BoardVO vo=BoardDAO.boardUpdateData(Integer.parseInt(no));
			   request.setAttribute("vo", vo);
		   }
		  
		  
		// update 
		public void boardUpdate(HttpServletRequest request, HttpServletResponse response)
		{    
			  try
			  {
				request.setCharacterEncoding("UTF-8");
				
				String no=request.getParameter("no");
				String name=request.getParameter("name");
				String subject=request.getParameter("subject");
				String content=request.getParameter("content");
				String pwd=request.getParameter("pwd");
				
				BoardVO vo=new BoardVO();
				vo.setNo(Integer.parseInt(no))	;
				vo.setName(name);
				vo.setSubject(subject);
				vo.setContent(content);
				vo.setPwd(pwd);
				
				BoardDAO.boardUpdate(vo);
				response.sendRedirect("list.jsp?no="+no);
			  } catch (Exception ex) {}

		}
}
