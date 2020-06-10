<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>for</title>
</head>
<body>
	<h2>Java로 코딩</h2>
	<%
				for(int i=1; i<=10; i++)
				{
	%>
					<%=i %>&nbsp;
	<% 
				}
	%>
	<br>
	<h2>JSTL로 코딩(view;JSP에서 태그형으로 프로그램 제작하기)</h2>
	
	<%--
					c:forEach
						var ==> 변수 (request.setAttribute("i", 1) ==> ${i} => i(request.getAttribute("i"))
						begin => 시작값
						end  => 종료값
						step => 증가값  (음수값은 존재 x)
	 --%>
	<c:forEach var="i" begin="1" end="10" step="1">
		${i }&nbsp;
	</c:forEach>
</body>
</html>