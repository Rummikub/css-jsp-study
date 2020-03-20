<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <%--				△ 태그를 선언해야 해당 함수를 쓸 수 있음 = import
    
    	★☆ <%%>의 태그는 사라지고 앞으로는 tag 사용해야 한다!	
    	-- 컴파일 과정에서 자동으로 java화 됨
    
    			JSTL의 Tag 
    			
    					<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    					=/core
    					------> 변수선언
    								<c:set > ==> request.setAttribute("id","admin")
    								<c:set var="id" value="admin">
    								=> ${id}  ; request에 값 담음
 빈도 낮음 						*** <c:out value="test"> => out.println()
    	높음							-------------------------------- ${} 
    					------> 제어문
    								<c:if> ====================> else존재X
    								if(조건문)
    								{
    										실행문장
    								}
    							=> <c:if test="조건문">
    										 		실행문장
    									</c:if>
    								
    								<c:forEach>
    								for(int i=1; i<=10; i++)
    								{
    								}
    							=> <c:forEach var="i" begin="1" end="10" step="1">  
    								 											    -> =포함	-> step은 1일 경우엔 생략 가능
    								 											    *** 단점은 감소 x  (step= -1 (X))
    								 </c:forEach>
    								for(MovieVO vo: list)        ==> 태그는 형변환 안되니까 var이 전체 타입형을 받는다
    								{
    								}
    							=> <c:forEach var="vo" items="list">
    								 </c:forEach> 
    								<c:choose> : 다중조건문, 선택문
    								
    								<c:choose>
    									<c:when test="조건문">실행문장</c:when>		if()
    									<c:when test="조건문">실행문장</c:when>		else if()
    									<c:when test="조건문">실행문장</c:when>		else if()
    									<c:when test="조건문">실행문장</c:when>		else if()
    									<c:otherwise></c:otherwise>--------> default	else
    								</c:choose>
    								JSTL => XML로 제작
    											-------
    										-	열고닫기, 지원하는 속성외의 다른 속성을 이용하면 에러 (속성: var,items ...)
												즉; 사용자 정의 속성 X
											-  속성값은 반드시 "" 사용해야 한다
											<c:set var=i/> -->ERROR	
											
											** <c:forTokens var="s" value="red,blue,green" delimt=",">
													StringTokenizer()
    					------> URL
    							<c:redirect url=""> : 화면이동 (response.sendRedirect())
    					
    					<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt"%>
    					=/fmt
    					
    					<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/functions"%>
    					=/fn -----> String, Collection
    									${fn:title.substring()}
    					
    					--------
    					=/sql --------->DAO
    					=/xml ---------> Parser(JAXP/JAXB) 
    					===== 사용하지 않음
    
     --%>
     <% 		//변수 설정
     			int sex=1;
     			
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL</title>
</head>
<body>
	<h2>Java로 코딩을 할 경우</h2>
	<%
			if(sex==1)
			{
	%>	
			남자
	<% 
			}
			else
			{
	%>
			여자
	<%			
			}
	%>
	
	
	
	<br>
	
	
	
	<c:set var="sex" value="2"/>
	<%--
			// 위의 태그와 동일한 코딩임
			request.setAttribute("sex",2); 
	
			c:set 
			----- var(key), value(값)   			
	--%>
	<h2>JSTL을 이용할 경우</h2>
	<c:if test="${sex==1 }">
	<%--  
			c:if
			test - 조건문 작성하는 위치
	--%>
	남자입니다
	</c:if>
	<c:if test="${sex==2 }">
	여자입니다
	</c:if>
	
	
</body>
</html>