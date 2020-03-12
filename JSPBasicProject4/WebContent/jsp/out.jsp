<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<%-- 
	Implicit Object - 기본객체 / 내장객체
		★	1) request		: 	★사용자 정보, 요청 정보 =>session / cookie 생성
				HttpServletRequest
		★	2) response	:	응답 정보 , header정보 ,★ 이동 정보 (페이지) (sendRedirect())
					* header  ~ 파일 다운로드	( 파일명, 파일크기를 서버에 전송 후 파일을 전송함)	
					데이터 전송 = header + data
				HttpServletResponse
			3) out : HTML 출력 메모리 (출력버퍼)
				JspWriter
				자동생성 
				 1)out.write("<html>") ; 
				 2)out.print (vo.getRegdate());		== <%=vo.getRegdate()%> 
		[JSP 실행과정]	 
		a.jsp (유저) -> a_jsp.java (톰캣; 최초 한번수행) -> a_jsp.class (;컴파일) -> 실행 (; 출력버퍼에 HTML을 출력) ->전송; (유저)
						
						(1) 메모리에 출력 => print(), println(), getBufferSize() => 8kb, getRemaining() ; 사용 후 남아있는 버퍼 크기 
							= 개발자 보다 관리자가 주로 사용함
 			4) application : 서버정보, 버전, ★ 로그파일관리 (log()) , web.xml 제어
 						getServerInfo()	3.1		
 						
 		★	5) session ( 추후에 다시 공부할 예정 ) 
 			6) pageContext : JSP 관리 => include(), forward()
 			7) config => web.xml
 			8) page= this
 			9) exception => try ~catch
 			
 			* cookie는 사용자가 생성하는 것, 내장객체가 아님
 
 => OUT은 메모리(임시 메모리 저장공간 : 출력버퍼)를 관리
 		= 메모리 출력 out.print() | out.println () | <%= %>
 		= 메모리 크기 확인 getBufferSize()
 		= 남은 메모리 확인 getRemaining()
 		= 버퍼 지우기 flush() | clear() ==> 잘 안씀 (b/c JSP의 autoFlush())
 
 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Out</title>
</head>
<body>
		현재 메모리 크기:<%= out.getBufferSize() %><br>
		남은 메모리 크기:<%= out.getRemaining() %><br>
		사용중인 메모리 크기: <%= out.getBufferSize() - out.getRemaining() %> <br>
		
		현재 메모리 크기:<% out.println( out.getBufferSize()); %><br>
		남은 메모리 크기:<% out.println(out.getRemaining()); %><br>
		사용중인 메모리 크기: <% out.println(out.getBufferSize() - out.getRemaining()); %> <br>
</body>
</html>