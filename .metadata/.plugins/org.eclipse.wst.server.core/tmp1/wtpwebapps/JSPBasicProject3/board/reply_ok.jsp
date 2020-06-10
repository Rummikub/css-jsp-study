<%@page import="com.sist.dao.BoardVO"%>
<%@page import="com.sist.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <% 
 				
 		// 데이터 받기 1byte > 2byte ; decoding
 		try
		 {
		 				request.setCharacterEncoding("UTF-8");
		 }	catch (Exception e){}
 
 		String name=request.getParameter("name");
 		String subject=request.getParameter("subject");
 		String content=request.getParameter("content");
 		String pwd=request.getParameter("pwd");
 		String pno=request.getParameter("pno");
 		String strPage=request.getParameter("page");
 		
 		BoardVO vo=new BoardVO();
 		vo.setName(name);
 		vo.setSubject(subject);
 		vo.setContent(content);
 		vo.setPwd(pwd);
 		
 		//DAO 연결
 		BoardDAO  dao=new BoardDAO();
 		//**vo값을 넣어주는 것은 아님
 		dao.replyInsert(Integer.parseInt(pno), vo);
 		
 		//**list.jsp ======> 지금 보고 있는!*****
		response.sendRedirect("list.jsp?page="+strPage);
 %>