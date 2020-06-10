<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.vo.*, java.util.*"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
    <%
    
    		List<Sawon> list=new ArrayList<Sawon>();  //<%=
    		list.add(new Sawon(1,"홍길동","영업부"));
    		list.add(new Sawon(2,"심청이","기획부"));
    		list.add(new Sawon(3,"춘향이","총무부"));
    		list.add(new Sawon(4,"박문수","개발부"));
    		list.add(new Sawon(5,"김두한","자재부"));
    		
    		request.setAttribute("list", list);  //${}
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자바 코딩</h2>
	<table border=1>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>부서</th>
		 </tr>
		 
		 <%
		 				for(Sawon s:list)
		 				{
		 %>
		 						<tr>
		 								<td><%=s.getSabun() %></td>
		 								<td><%=s.getName() %></td>
		 								<td><%=s.getDept() %></td>
		 						</tr>
		 <% 
		 				}
		 %>
		</table>
		
		
	<h2>JSTL코딩</h2>
	<table border=1>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>부서</th>
		 </tr>
		 <c:forEach var="s" items="${list }" >
		 	<tr> 	
		 	<!--여기 들어가는게 변수가 아님!  vo에 있는 getSabun()메소드를 호출
		 				getXxx()
		 				
		 				
		 				${s.getSabun()) 
		 				${s.setSabun('1') -  메소드의 매개변수가 있을 때는 이렇게 호출
		 				JSP2.1 이하는 이렇게 그냥 호출 못함
		 	 -->
		 	
		 			<td>${s.sabun }-${s.getSabun() }</td>
		 			<td>${s.name }</td> 
		 			<td>${s.dept }</td>
		 	</tr>
		 </c:forEach>
		 </table>
</body>
</html>