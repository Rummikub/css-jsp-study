<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.temp.*, java.util.*"%>


<%
		SawonManager sm=new SawonManager();
		//SawonManager list를 받아옴
		sm.sawonAllData(request);
		List<Sawon> list=(List<Sawon>)request.getAttribute("list"); //${list}
 %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Attribute</title>
</head>
<body>
		<ul>
			<%
					for(Sawon s:list)
					{
			%>
							<li><%=s.getName() %>-<%= s.getDept() %></li>
			<% 			
					}
			%>
		</ul>
</body>
</html>