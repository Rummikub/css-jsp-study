<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta content="UTF-8">
<title>join</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/shadowbox.css">
<script type="text/javascript" src="js/shadowbox.js"></script>
<script type="text/javascript">
Shadowbox.init({
	players:['iframe']	
});

function postfind()
{
	window.open("postfind.jsp","postfind","width=480,height=350,scrollbars=yes");
}

function idcheck()
{
	window.open("idcheck.jsp","idcheck","width=380,height=230,scrollbars=no");
}

function join()
{
		/*  계층구조
				태그에 접근 ===>( window).document.form.input/select/textarea
							 		window
							 			- document
							 				-form  ==> form 태그에는 언제나 이름 줘야 해 (폼이 여러개일 수 있으니까)
							 					-input, select, textarea
		*/
		//유효성 검사 (if i==null) ; validation => Spring
		//document.frm.name ==> $('#name')
		document.frm.submit(); // 데이터를 전송하라는 의미
}
</script>
<style type="text/css">
 .row {
 	margin: 0px auto;
 	width: 700px;
 }
</style>
</head>

<body>
	<div class="container">
		<h1 class="text-center">회원가입</h1>
		<div class="row">
		<form name="frm" action="join_ok.jsp" method="post">
			<table class="table table-hover">
				<tr>
					<th width=15% class="danger text-right" >ID</th>
					<td width=85%>
						<input type=text name=id size=15 class="input-sm" readonly>
						<input type=button value="중복체크" class="btn btn-sm btn-danger" onclick="idcheck()">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >비밀번호</th>
					<td width=85%>
						<input type=password name=pwd size=15 class="input-sm">&nbsp;
						재입력:<input type=password name=pwd1 size=15 class="input-sm">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >이름</th>
					<td width=85%>
						<input type=text name=name size=15 class="input-sm">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >이메일</th>
					<td width=85%>
						<input type=text name=e-mail size=50 class="input-sm">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >성별</th>
					<td width=85%>
						<input type="radio" name=sex value="남자" checked="checked">남자
						<input type="radio" name=sex value="여자">여자
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >생일</th>
					<td width=85%>
						<input type=date name=birthday size=15 class="input-sm">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >우편번호</th>
					<td width=85%>
						<input type=text name=post1 size=5 class="input-sm" readonly>   - 
						<input type=text name=post2 size=5 class="input-sm" readonly>
						<input type=button class="btn btn-sm btn-primary" value="우편번호검색" onclick="postfind()">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >주소</th>
					<td width=85%>
						<input type=text name=addr1 size=50 class="input-sm" readonly>
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >상세주소</th>
					<td width=85%>
						<input type=text name=addr2 size=50 class="input-sm">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >전화번호</th>
					<td width=85%>
						<select class="input-sm" name="tel1">
							<option>010</option>
							<option>011</option>
							<option>017</option>
						
						</select>
						<input type=text name=tel2 size=5 class="input-sm">  -
						<input type=text name=tel3 size=7 class="input-sm">
					</td>
					
				<tr>
					<th width=15% class="danger text-right" >소개</th>
					<td width=85%>
						<textarea rows="8" cols="60" name="content"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan=2 class="text-center">
						<input type=button value="회원가입" class="btn btn-sm btn-info"
							onclick="join()"
						>
						<input type=button value="취소" class="btn btn-sm btn-success"
							onclick="javascript:history.back()">
					</td>
				</tr>
		</table>
		</form>
		</div>
	</div>
</body>
</html>