<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" info="스크립트 요소 처리"%>
    <%-- 
    		1. 스크립트릿   - 지역변수
    			<%
    				일반 자바 코드 (메소드 안에 코딩되는 내용)
    				int s=10;
    				if(s==10)
    				{
    				}
    			%>
    	
    		2. 표현식
    			<%= 출력물 %>			=> out.println(출력물)  ;     출력물만 써야 된다 세미콜록 (;) 쓰면 안됨
    			
    		3. 선언식 ; 메소드 안에 메소드를 생성할 수 없으니까 자바로 치자면 생성자를 따로 빼서 위에 써주는 것과 같은 이치  - 전역변수 ( 사용 권장 X )
    			<%! 
    					public int sum(int a, int b)
    					{
    						return a+b;
    					}
    			 %>
     --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ScriptLet</title>
</head>
<body>
	<%
				int a=100; 
	%>
	<h1><%= a %></h1>
</body>
</html>