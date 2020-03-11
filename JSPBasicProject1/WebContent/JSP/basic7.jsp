<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
    
    <%
    	List<MusicVO> list=MovieDAO.musicAllData();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>music</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<!--   
	list.jsp?page=1
	detail.jsp?page=1&no=10
				===== =
				============> request 
				request.setAttribute("page",1)
				request.setAttribute("no",10) ==> request.getParameter("page")  = 1
																 request.getParameter("no") = 10
-->
         <div class="container">
         	<h1 class="text-center">top 100</h1>
         	<div class="row">
         		<%
         			for(MusicVO vo:list)
         			{
         				//너무 긴 제목 자르기
         				String title=vo.getTitle();
         				String title2="";
         				if(title.length()>30)
         				{
         					title2=title.substring(0,30)+"...";
         				}else
         				{
         					title2=title;
         				}
         		%>
         			<div class="col-md-4">
    				  <div class="thumbnail">
        				<a href="#" target="_blank">
          					<img src="<%=vo.getPoster() %>" alt="Nature" style="width:100%">
          			<div class="caption">
           					 <p><%= title2%></p>
         					 </div>
        					 </a>
          				</div>
          				</div>
         		<% 
         			}
         		%>
         	</div>
         </div>
</body>
</html>