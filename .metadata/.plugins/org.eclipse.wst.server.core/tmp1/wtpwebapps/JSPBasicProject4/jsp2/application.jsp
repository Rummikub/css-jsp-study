<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    	application 객체 -> ServletContext
    	=> Server+let => web => because of compile - JSP
    		 MIDlet, Applet
    
    	JAVA
    	SE  ; application 
    	EE  ; 기업용
    	ME ; 모바일
    	
    	브라우저에서 사용자가 데이터를 보내주면 받을 수 있는 것은 Servlet, JSP 뿐. 
    	자바에서 바로 request 못함, 웹서버인 톰캣이 받아오기 때문
    	
    	Data-Servlet-Java-JSP ============MVC구조
    	~~유지보수 (1) 재사용에 용이한가 (2) 확장성이 좋은가	
    
    	예) String strPage=request.getParameter("page");
    				JSP --> DAO(.java)     
    		 List<BoardVO> list=dao.boardListData(curpage); || (request,curpage);
    		 
    APPLICATION
    	서버관련 
    		1) 서버정보
    			= getServerInfo() ==> 3.1 ver   1.7 (major)  1.8_01 (minor)
    			= 버전정보
    				getMajorVersion()
    				getMinorVersion()
    			   
    		2) 자원정보
    			= getRealPath()
    			
    		3) web.xml 처리 ~ log파일
      			= getInitParameter()
      --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	<%= application.getRealPath("/jsp2/application.jsp") %>
 실행결과 : C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\JSPBasicProject4\jsp2\application.jsp 
--%>
	서버이름:<%=application.getServerInfo() %> <br>
	버전:<%=application.getMajorVersion() %> <br>
	<%=application.getMinorVersion() %>
	<%
			String driver=application.getInitParameter("driver");
			String url=application.getInitParameter("url");
			String username=application.getInitParameter("username");
			String pwd=application.getInitParameter("password");
			
			application.log("Driver:"+driver);
			application.log("URL:"+url);
			application.log("userName:"+username);
			application.log("PWD:"+pwd);
	%>
</body>
</html>