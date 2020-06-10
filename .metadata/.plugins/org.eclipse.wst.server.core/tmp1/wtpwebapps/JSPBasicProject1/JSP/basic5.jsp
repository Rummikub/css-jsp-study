<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!--  클래스 영역 안쪽에 코딩됨-->  
  <%!
  	// 메소드 제작 - must 선언식
  	public int sum(int a, int b)
  	{
		return a+b;	  
  	}
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>선언식</title>
</head>
<body>
	<% 
			int a =10;
			int b=20; 
			int c=sum(a,b);
			//%안에 %가 들어가면 안됨
	%>
	<%= c %>
	
	
</body>
</html>