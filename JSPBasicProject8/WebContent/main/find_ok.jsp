<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 /* 	try 
	 {
	 		request.setCharacterEncoding("UTF-8");
	 }catch (Exception e) {} */
  // ====>이곳이 아니라 main의 리퀘스트에서 한글변환을 해줘야 됨 
	 
  	String ss=request.getParameter("ss");
	 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="row">
			<h1><%=ss %></h1>
		</div>
</body>
</html>