<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
    					<!-- HOME버튼 누르면 메인으로 와야겠지 
    					어느 폴더로 가든지 한번 밖으로 나와야 되겠지 (in Model) -->
      <li class="active"><a href="${pageContext.request.contextPath }/site/main.do">Home</a></li>
      <li><a href="${pageContext.request.contextPath }/site/member/join.do">회원가입</a></li>
      <li><a href="${pageContext.request.contextPath }/site/board/list.do">게시판</a></li>
      <li><a href="${pageContext.request.contextPath }/site/movie/list.do">영화목록</a></li>
      <li><a href="${pageContext.request.contextPath }/site/movie/reserve/reserve.do">영화예매</a></li>
      <li><a href="${pageContext.request.contextPath }/site/music/music.do">음악목록</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
	<div class="col-md-8">
		
	
 				<!-- 계속 바뀌는 부위  : div가 여러개일때 ( div1 계속 바뀜 ) (div2 고정) -->
 	<jsp:include page="${main_jsp}"></jsp:include>
	
	</div>
	
	<div class="col-md-4">
		<center>
			<h1>${side }</h1>
		</center>
	</div>

</div>
</body>
</html>