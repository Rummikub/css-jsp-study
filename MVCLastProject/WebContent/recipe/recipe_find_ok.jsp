<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <c:forEach var="vo" items="${list }">
    <div class="col-md-4">
       <div class="thumbnail">
          <a href="../recipe/recipe_detail.do?no=${vo.no }"> 
          <img src="${vo.poster }" alt="Lights" style="width: 100%">
             <div class="caption">
                <p>${vo.title }</p>
                <p><sub style="color:gray">${vo.chef }</sub></p>
             </div>	
          </a>
       </div>
    </div>
 </c:forEach>
</body>
</html>