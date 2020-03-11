<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
    
   <%
   		String no=request.getParameter("no");
   		// no=>DAO=> VO=> HTML
   		BoardDAO dao=new BoardDAO();
   		BoardVO vo=dao.boardDetailData(Integer.parseInt(no), 0); 
   		
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
.row {
	margin: 0px auto;
	width: 800px;
}
h2 {
	text-align: center;
}
</style>
</head>
<body>

</body>
</html>