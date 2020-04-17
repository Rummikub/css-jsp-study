<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper row2">
		<div id="services" class="clear" style="width:800px">
		  <div class="row text-center">
  	  			<img src="../freeboard/jsp_board2.png" style="width:900px; height:200px;">
  	  	</div>
  	  			<div class="row">
			<form method=post  action="../freeboard/insert_ok.do">
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-right  success">이름</th>
					<td width=80%>
						<input type="text" name=name size=15 required>
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
						<input type="submit" value="글쓰기" class="btn btn-sm btn-primary">
						<input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
						</td>
				</tr>
			
			</table>
				</form>
		</div>
	</div>
</div>
</body>
</html>