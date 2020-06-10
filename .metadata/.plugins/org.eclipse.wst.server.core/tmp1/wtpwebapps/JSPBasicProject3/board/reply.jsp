<!-- insert.jsp와 동일 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
		String no=request.getParameter("no");
		String strPage=request.getParameter("page");
%>


<!DOCTYPE html>
<html>
<head>
<meta  charset="UTF-8">
<title>답변하기</title>
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
	<div	 class="container">
		<h2>답변하기</h2>
		<div class="row">
		
		
			<!--  insert_ok로 넘어가는 이유 : java가 먼저 수행된다 (즉, null값이 default) 
			=ALSO=> not null 걸려있는 항목때문에 같은 화면 insert.jsp에서 처리할 수 없다-->
		
			<form method=post  action="reply_ok.jsp">
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-right  success">이름</th>
					<td width=80%>
						<input type="text" name=name size=15 required>
						
				<!-- 공개되면 안되는 정보들 숨기기  (답변 번호 =!  게시번호) 즉, 상위번호 pno: parent no. (group_id,tab,step) -->
						<input type=hidden name=pno value="<%=no %>">
						<input type=hidden name=page value="<%=strPage %>">
						
						</td>
				</tr>
				
				<tr>
					<th width=20% class="text-right  success">제목</th>
					<td width=80%>
						<input type="text" name=subject size=50 required>
						</td>
				</tr>
				
				<tr>
					<th width=20% class="text-right  success">내용</th>
					<td width=80%>
						<textarea rows="8" cols="55" name=content required></textarea>
						</td>
				</tr>
				
				<tr>
					<th width=20% class="text-right  success">비밀번호</th>
					<td width=80%>
						<input type="password" name=pwd size=10 required>
						</td>
				</tr>
				
				<tr>
					<td class="text-center" colspan=2>
						<input type="submit" value="답변" class="btn btn-sm btn-primary">
						<input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
						</td>
				</tr>
			
			</table>
				</form>
		</div>
	</div>
</body>
</html>