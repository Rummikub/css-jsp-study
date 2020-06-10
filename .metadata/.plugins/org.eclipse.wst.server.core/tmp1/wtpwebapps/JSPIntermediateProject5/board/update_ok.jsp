<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.sist.dao.*, com.sist.model.*"%>

<%
	BoardModel model=new BoardModel();
	model.boardUpdate(request, response);
%>
		

 