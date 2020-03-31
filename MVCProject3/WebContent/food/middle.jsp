<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">


<title>2단계</title>
</head>
<body>
	<div class="container">
				<div class="row">
					<div class="jumbotron">
						<h1 class="text-center">${vo.title }</h1>
						<h3 class="text-center">${vo.subject }</h3>				
					</div>
				</div>
				<div class="row">
					<table class="table">
						<tr>
							<td>
								<c:forEach var="vo" items="${list }">
												<table class="table table-hover">
													<tr>
														<td width=30% class="text-center" rowspan="3">			
															<a href="detail.do?no=${vo.no }>"><img src="${vo.image }>" width=350 height=180 class="img-rounded"></a>
														</td>
														<td width=70%>
															<h3><a href="detail.do?no=${vo.no}">${vo.title }</a>&nbsp;<span style="color:#FC6">${vo.score}</span></h3>
														</td>									
													</tr>
													
													<tr>
														<td width=70%>
															${vo.address }
														</td>
													</tr>
													<tr>
														<td width=70%>${vo.tel }</td>
													</tr>
												</table>
								</c:forEach>
							</td>
						</tr>
					</table>	
					</div>
					<div class="row">
						<table class="table">
							<tr>
								<td class="text-right">
								<a href="category.jsp" class="btn btn-lg btn-primary">목록</a>
							</tr>
						</table>
				</div>
			</div>
</body>
</html>