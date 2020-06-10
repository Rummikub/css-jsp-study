<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
    
 <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <% // ******Call by ref : class 통째로 한 주소에 실어서 넘어감 (request)
 		SawonModel sm=new SawonModel();
 		sm.sawonListData(request);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- EL / JSTL  -->
		<center>
				<h1>사원 목록</h1>
				<core:forEach var="name" items="${list}" >
				${name }<br> 
				</core:forEach>
		</center>
</body>
</html>