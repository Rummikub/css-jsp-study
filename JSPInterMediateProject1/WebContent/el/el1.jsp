<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%-- View
  			EL ; 화면에 출력하는 언어 
  			=> 사용법
  			=> 자바를 한번만 연결하기 위해
  			-- request - 일회성
  			-- session - 여러군데에서 받아올 때
  			
  			
  			request / session에 담긴 값만 넘겨줄 수 있음
  					${출력물}
  					
  					1)<%=일반자바변수%>
  					
  					2)${}
  					=> getParameter("id") => ${param.id}
  					
  					3)${} 
  					=> request.getAttribute("id")
  					=> ${requestScope.id} => ${id}
  					
  					${} => session.getAttribute("name","홍길동")
  							=> ${sessionScope.name}
  					
  					-우선순위가 request임 -> session -> application
  					  즉;
  					  request.getAttribute("id1","admin");
  					  session.getAttribute("id2","hong");
  					  
  					  ${id1} => admin |  ${id2} => hong
  					  ${sessionScope.id} => hong
  					
  					예) 
  							String id="admin";
  							${id} (X)  ==> <%=id%> (O)
  							---------------------------------------
  							request.setAttribute("id",id)
  							${id} (O)  -- key이름을 줌
   --%>
   
   <%
   // 자바에서 request에 값을 실어서 넘겨줘야 함.
   			String name="홍길동";
   			request.setAttribute("name", name);
   			//#자바로 출력하고 싶다면
   			String n=(String)request.getAttribute("name");
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   이름 : ${name } 
   <!-- //# -->
   <%=n %>
</body>
</html>