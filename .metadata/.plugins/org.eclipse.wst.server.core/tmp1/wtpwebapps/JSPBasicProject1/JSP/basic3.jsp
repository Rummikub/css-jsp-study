<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ScripLet_Practice</title>
</head>
<body>
	<%
			for(int i=1; i<=10; i++)
			{
				if(i%2==0)
				{
	%>
					<h1><%=i %>&nbsp;</h1>
	<% 
				}
			}
	%>
</body>
</html>