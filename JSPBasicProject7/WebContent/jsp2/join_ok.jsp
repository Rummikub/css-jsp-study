<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.member.*"%>
  <%
	  	try 
	 	{
	  		request.setCharacterEncoding("UTF-8");
	 	 }catch(Exception e) {}
	  
  	//값 받기
	  	String name=request.getParameter("name");  // <input type=text name=name>
	  	String sex=request.getParameter("sex");
	  	String addr=request.getParameter("addr");
	  	String tel=request.getParameter("tel");
	  	String age=request.getParameter("age");
	  
	  	MemberBean mb=new MemberBean();
	  	mb.setName(name);
	  	mb.setSex(sex);
	  	mb.setAddr(addr);
	  	mb.setTel(tel);
	  	mb.setAge(Integer.parseInt(age));
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bean</title>
</head>
<body>
		<h1>전송 데이터 출력</h1>
		이름:<%=mb.getName() %><br>
		성별:<%=mb.getSex() %><br>
		주소:<%=mb.getAddr() %><br>
		전화:<%=mb.getTel() %><br>
		나이:<%=mb.getAge() %>
</body>
</html>