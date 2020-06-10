<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%--
 			String name=request.getParameter("name"); ★ 이걸 더 사용함
 			String name=pageContext.getRequest.getParameter("name");
 			
 =>PageContext
 	 화면 조립, 화면 이동하면서 동시에 request를 유지 *( 원래는 일회성)
 	 include			forward
			
			화면 이동 방법
			=html  			: <a> | <form>
			=javascript 	: location.href("") =>특히 ajax 사용시
			=java				: sendRedirect() | forward()  :  초기화 | 유지됨 (request)    =>MVC 에서 사용
  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
.container{
	border: 1px solid black;
	width: 960px;
	height: 760px;
}
.header{
	border: 1px solid red;
	width: 960px;
	height: 120px;
}
.footer{
	border: 1px solid blue;
	width: 960px;
	height: 120px;
}
.content{
	border: 1px solid green;
	width: 960px;
	height: 520px;
}
.col-sm-3{
	border: 1px solid yellow;
}
.col-sm-9{
	border: 1px solid magenta;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row header">
			<% 
				pageContext.include("header.jsp");
//<jsp:include page="header.jsp"> ==> request를 공유함  pageContext.jsp?id=admin(O)  header.jsp?id=admin(X) 항상 조립하는 main에서 호출.
			%>
		</div>
		<div class="row content">
			<div class="col-sm-3">
		    <% 
				pageContext.include("side.jsp");
			%>
			</div>
			<div class="col-sm-9">
		<% 
				pageContext.include("content.jsp");
			%>
			</div>	
		</div>
		<div class="row footer">
		<% 
				pageContext.include("footer.jsp");
			%>
		</div>
	</div>
</body>
</html>