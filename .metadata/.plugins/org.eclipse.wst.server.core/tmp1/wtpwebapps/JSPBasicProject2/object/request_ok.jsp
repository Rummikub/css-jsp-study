<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  		//데이터 받기 (from request.jsp)
  		//한글 변환
  		try 
	    {
	  			request.setCharacterEncoding("UTF-8");
	     }catch(Exception e) {}
  
 		// 값 받기
 		String name=request.getParameter("name");
 		String sex=request.getParameter("sex");
 		String tel1=request.getParameter("tel1");
 		// checkbox는 여러값이 동시에 넘어오기 때문에 []로 받아야함
 		String[] hobby=request.getParameterValues("hobby");
 		String content=request.getParameter("content");
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Request Ok</title>
</head>
<body>
	<h1>전송받은 데이터 정보</h1>
	이름:<%= name %> <br>
	성별:<%= sex %> <br>
	전화:<%= tel1 %> <br>
	소개:<%= content %> <br>
	취미:
	<ul>
	<%
	try
	{
	    	for(int i=0; i<hobby.length; i++)
			{
	%>
			
			<li>	<%= hobby[i] %></li>
	<%
			}
	}catch(Exception ex)
		{
	%> 
		<span stype="color:red">취미가 없다</span>
		<%
		}
		%>
	</ul>
</body>
</html>