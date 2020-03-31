<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
  
<!-- 앞으로 이 담당은 컨트롤러가 할 것  -->
 <%
 		BoardModel model=new BoardModel();
 		model.boardInsert(request, response);
 %>