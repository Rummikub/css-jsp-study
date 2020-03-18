<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>

<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<%
		String id=request.getParameter("id");
		int temp=0;
		int count=0;
		//id가 존재한다면
		if(id!=null)
		{
			count=dao.idcheck(id);
			temp=1;
		}
		//"null"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 320px;
}
</style>
<script type="text/javascript">
/* 중복체크나 주소값 넣어주는 것은 자바스크립트에서 처리해줘야함 = request초기화 */
function idcheck()
{
		var id=document.frm.id.value;
		if(id.trim()=="")
		{
				document.frm.id.focus();
				return;
		}
		document.frm.submit();
}
/*join으로 값 보내지는 함수  중복 체크에서 값을 넘기면 회원가입에서 값을 받는다  */
function ok()
{
	opener.frm.id.value=document.frm.id.value;
	self.close();
}
</script>
</head>
<body>
		<div class="container">
			<h3 class="text-center">아이디중복체크</h3>
		<div class="row">
			<table class="table">
				<tr>
				<td>
				<form method=post action="idcheck.jsp" name="frm">
				입력:<input type=text name=id size=15 value="<%=id!=null?id:""%>">
				<input type=button value="검색" class="btn btn-sm btn-primary" onclick="idcheck()">
				</form>
				</td>
				</tr>
				<tr>
				<td class="text-center">
				<%
							if (temp==0)
							{
				%>
							<font color=gray>ID를 입력하세요</font>
				<%			
							}
				%>
				<% 
						if(count==0 && temp==1)
						{
				%>
						 <font color=blue><%=id %> = 사용가능한 아이디입니다</font>
				<%
						}
						else if(count==1 && temp==1)
						{
					%>
							<font color=red><%=id %>= 사용 불가한 아이디입니다</font>
					<%
							
						}
					%>
				</td>
				</tr>
				
				<%
						 if(count==0 && temp==1)
						 {
				%>
				<tr>
					<td class="text-center">
						<input type=button value="확인" class="btn btn-sm btn-danger" onclick="ok()">
					</td>
				</tr>
				<%
						 }
				%>
			</table>			
		</div>
	</div>
</body>
</html>