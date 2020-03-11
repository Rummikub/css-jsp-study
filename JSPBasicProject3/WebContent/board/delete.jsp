<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
		//Ajax에서는 아래의 자바 코드가 안 넘어가고 결과값만 넘어감 
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");

		//DAO연동 (실제로 값을 지워줘야 하니까 DB에 있는 pwd랑 비교해야 됨)
		BoardDAO dao=new BoardDAO();
		BoardVO vo=new BoardVO();
		int res=dao.boardDelete(Integer.parseInt(no), pwd);
%>
<%=res %>

