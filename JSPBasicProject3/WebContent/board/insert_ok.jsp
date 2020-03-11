<!-- 앞으로 _ok.jsp는 화면 출력용이 아니라 DB랑 연결하는 용도  -->

<%@page import="com.sist.dao.BoardVO"%>
<%@page import="com.sist.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <% 
 				
 		// 데이터 받기
 		try
		 {
		 				request.setCharacterEncoding("UTF-8");
		 }	catch (Exception e){}
 
 		String name=request.getParameter("name");
 		String subject=request.getParameter("subject");
 		String content=request.getParameter("content");
 		String pwd=request.getParameter("pwd");
 		
 		BoardVO vo=new BoardVO();
 		vo.setName(name);
 		vo.setSubject(subject);
 		vo.setContent(content);
 		vo.setPwd(pwd);
 		
 		//DAO 연결
 		BoardDAO  dao=new BoardDAO();
 		dao.boardInsert(vo);
 		
 		//이동 -> list.jsp
		response.sendRedirect("list.jsp");
 %>