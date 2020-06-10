<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%--
 		${연산자}
 		==> 산술연산자
  				+, -, *, /(div), %(mod) ==> ${5/2} ${ 5 div 2}
  				단) 
  				(<%= 10/3 %> :정수 )  (${10/3} :실수) 

				1) 덧셈연산자  
				+ : 1) 산술 연산 2) 문자열 결합
				java>		"Hello"+10; Hello10
				EL> 	"Hello"+10 ==> Error
				+ : 순수하게 산술만 가능 
				
				2) 문자열 결합
				java> <%="100"+"10"%> => 10010
				EL > ${"100"+"10"} =110      ; ""은 문자열을 나타내는 것이 아니야
				
				3) null
				null 값은 0으로 계산된다 
				자바 "" + "" : EL +=
				
				${sessionScope.id} =null (可)  ; 예매,댓글
				
		==> 비교연산자  ( == != < > <= >=)   ===========> true/false 
				== | eq : (문자열,숫자) 비교 가능 *(java는 equals()) 
				!= | ne 
				< 	| lt
				> 	| gt
				<=| le
				>=| ge
				
		==> 논리연산자   ====> true/ false
				&& | and
				|| | or
				! | not
		
		
		String id; ==> null
		String id=""; ==> ""
		
		==> empty 연산자 
				${empty list} : 배열에 값이 있나 확인 (비어있나)
  				자바 함수 호출 X : ${list.size() >1}
  				
  		==> 삼항연산자 
  				${조건?값1:값2}
  				
  		==> 문자열 결합 : +=
  		
  		==> JSP안에서 <% %> 더이상 쓰지말자
  		
  		 <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %> - 선언문 
  		 prefix (접두사)는 내가 변경해 줄 수 있음
  		
  --%>		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= "Hello" %> <br>
	${"Hello"}<br>
	<%=10 %><br>
	${10 } <br>
	${10+10 }<br>
	${10%3 }<br>
	
	<%= 10/3 %><br>
	${10/3} <br>
	
	<%-- <%=null+10 %><br> --%>
	<%-- ${ "Hello"+10} --%>
	<!-- Error  -->
	
	 <%="100"+"10"%> <br>
	${"100"+"10"}<br>
	
	${"Hello" += 10 }
	${null+10 }
</body>
</html>