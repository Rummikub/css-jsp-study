<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
			//data가 넘어올 때 어떻게 처리
			String[] subject={"자바","오라클","JSP","Spring","Kotlin"};	
								

			// request 에 담긴게 아니기에 JSTL은 쓸 수 없음 ( 자바 일반 변수임)	
			request.setAttribute("subjects", subject);

%>
<%--<c:set var="subjects" value="<%=subjects %>"/>  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Java코딩</h2>
	<ul>
		<%
				int i=1;
				for(String sub:subject)
				{
		%>
							<li><%=i%>.<%=sub %></li>
		<%								
						i++;
				}
		%>
	</ul>
	<h2>JSTL코딩</h2>
	<ul>
		<c:forEach var="sub" items="${ subjects}" varStatus="s">
		<li>${s.index+1}.${sub }</li>
		</c:forEach>
	</ul>
</body>
</html>