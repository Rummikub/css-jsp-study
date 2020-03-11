<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"  buffer="16kb" autoFlush="true"  session="true"
 	info="게시판의 목록출력(개발자 이름)"
    %>
    <!-- 사용자 전송값이 UTF-8 =>서버는 EUC-KR (X), UTF-8만 사용할 수 있음
    	   파일업로드, Ajax, React,Vue => UTF-8 써야 함
    	   	==> 즉, UTF-8로 설정해야 여러모로 좋단 소리  -->
 <%--
 				page 지시자 : JSP 파일에 대한 정보를 갖고 있음         속성값 ="변수값" 
 				변환 코드 
 						contentType ="text/html; charset=UTF-8"
 						
 						=> response.setContentType("text/html; charset=UTF-8")
 						=> default :  contentType="text/html; charset=ISO-8859-1"
 						=> 변환 형식 
 								HTML => text/html
 								XML  => text/xml
 								
 				errorPage  : 에러가 났을 때 이동하는 지정된 파일
 				
 				import : 라이브러리를 읽어 올 경우, 사용자 정의 클래스를 의미
 				--------- default; java.lang, javax.servlet.* 
 				********** 기본은 page 지시자의 속성 , 지시자 속성은 한 파일에서 한번만 사용함 errorPage="a.jsp" errorPage="b.jsp" (X)
 				<%@ page import="java.io.*, java.util.*"%>
 				<%@ page import="java.io.*%>
 				<%@ page import="java.util.*%>
 				
 				buffer: 출력 (HTML을 출력하는 메모리 공간)  ;   크기는 8kb가 기본값
 				info: 이 페이지의 목적을 기록
  --%>
    
 <!-- html주석 태그 -->
 <%-- jsp 주석 태그 --%>
 <%--  
 		<%	jsp 안 = java  
 				int a=10;
 				//int b=20;      -- java주석
 				/*int c=30;*/   -- java주석
 		%>
 		
 		JSP ; Java Server Page 서버에서 실행되는 자바 파일을 일컬음
 									-------web에서 파일 아니고 페이지라고 말함 ( 보통 75pg )
 									
 		JSP에서 배울 내용 정리 
 			1) 실행과정 a.jsp => a_jsp.java => a_jsp.class => 실행 (html) 
 			2) 지시자
 				= page : JSP 파일에 대한 정보를 갖고 있음
 					<%@ page 속성=값 속성=값.......... %>
 				= include :  JSP 안에 다른 JSP를 첨부 (조립)
 					<%@ include file=""%>
 				= 태그 라이브러리
 					<%@ taglib prefix="" uri=""%>
 					<c:if>
 					<c:forEach>
 					<c:choose>
 					<c:set>
 					<c:out>
 			3) 자바 코딩
 				<% %> ; 스크립트 릿 				==> 일반 자바 코딩
 				<%! %> ; 선언식  					==> 멤버 변수 생성, 메소드 생성
 				<%= %> ; 표현식			 			==> 화면 출력    = out.println()
 				3.1  자바 파일 							==> DB 연동, VO(자바빈)
 			4) 내장 객체 :  미리 생성된 객체 즉, 자동으로 생성된다
 					*request : 사용자 요청값, 사용자 정보 (IP,PORT)
 					*response : 서버에서 응답
 					*session : 서버에 사용자 정보, 기타 내용 저장
 					application : 서버 정보
 					out : 출력 버퍼에 대한 정보 (메모리)  ==> <%= %>, ${}
 					*pageContext : JSP와 JSP를 연결 => include, forward 
 					page : this 
 					config : 환경설정 => web.xml에서 설정
 					exception : 예외처리 => try ~ catch 
 			5) 에러페이지  = 404 error가 떴을 때 이동하는 페이지
 				= 한번에 처리 		=> page 지시자에 존재 (errorPage="이동할 파일명")
 				= 분류해서 처리 	=> 톰캣이 알고 있어야 함 (web.xml)
 			6) 액션태그
 				<jsp:include>  ; 별도로 변수를 조정해 줄 필요가 없음 ( html만 합쳐주는 것 ) 
 				<jsp:forward> ;  forward는 전체 안에서만 바뀜 ( 주소 동일 )   즉, request가 유지되게끔 (Spring MVC 기법)
 				sendRedirect 는 new객체를 생성해줘야 함 ( 화면 바뀜, 주소도 바뀜 ) = 短 request값의 초기화를 의미	
 				<jsp:useBean> :  메모리 할당 (new는 더이상 사용하지 않는다)
 				<jsp:setProperty> : 요청값을 받아 처리
 			Model
 			------------------------------------------------------------------------	
 			MVC구조
 			7) EL, JSTL
 			8) MVC 
 --%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>basic 1</title>
</head>
<body>
	<h1> 안녕 JSP</h1>
			<%
				int a=10/0;
			%>
			<%= a %>
</body>
</html>