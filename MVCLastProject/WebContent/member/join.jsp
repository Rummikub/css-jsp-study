<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta content="UTF-8">
<title>join</title>
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<style type="text/css">
.row{ 
	margin: 0px auto;
	width: 1000px;
	
}
input, select {
	display: inline-block;
}
.table, td {
	 background-color: white;
}
1
</style>
<script type="text/javascript">

/* 창을 html형식으로 띄운다 */
Shadowbox.init({
 	players:['iframe']
});


// jquery  ; window.onload ~ main()
// $(document).ready(function()){})   ==> 앞을 생략해서 아래의 결과가 나온다 (처리함수)
// var id=document.getElementById('id') : js <=====<input type=text id=id size=10>====> lib : $('#id')
/* 
		JavaScript / Jquery  == 태그를 제어하는 DOM 프로그램
		selector
		====			
			1) 태그 => $('태그명') 
			2) ID   => $('#id')
			3) CLASS => $('.클래스명')
			4) 가상  => 자신, 내장객체 => $(this), $(window), $(document)

*/
var i=0;
var p=0;
$(function(){
				// onClick="함수명"
				/*
					태그로 제어 : CSS제어, 이벤트 발생
				*/
	
	$('#postBtn').click(function(){
			// 다음의 창을 띄워라
			Shadowbox.open({
				 content:'../member/postfind.do',     // 별도의 창이니까 postfind.jsp에서 처리함 (join아님)
				 title:'우편번호검색',
				 // html - iframe (include의미)
				 player:'iframe',
				 width:530,
				 height:400
			});
			p=1;
		});
	$('#idcheckBtn').click(function(){
		Shadowbox.open({
			content:'../member/idcheck.do',
			title:'아이디 중복체크',
			player:'iframe',
			width:390,
			height:200
		})
		i=1;
	})
	$('#sendBtn').click(function(){
		if(i==0)
		{
			alert("아이디 중복체크를 하세요");
		}
		else
		{
			if($('#pwd').val()!=$('#pwd1').val())
			{
				alert("비밀번호가 틀립니다!");
			}
			else if (p==0)
			{
				alert("우편번호를 입력하세요");
			}
		}
	})
});
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
		<form name="frm" action="../member/join_ok.do" method="post" id="frm">
			<table class="table table-hover">
				<tr>
					<th width=15% class="danger text-right" >ID</th>
					<td width=85%>
						<input type=text name=id size=15 class="input-sm" readonly id="id"> <!-- 자바는 name을 읽고 jquery는 id읽음 -->
						<input type=button value="중복체크" class="btn btn-sm btn-danger" id="idcheckBtn">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >비밀번호</th>
					<td width=85%>
						<input type=password name=pwd size=15 class="input-sm" id="pwd" required>&nbsp;
						재입력:<input type=password name=pwd1 size=15 class="input-sm" id="pwd1" required>
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >이름</th>
					<td width=85%>
						<input type=text name=name size=15 class="input-sm" required>
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
						<input type=date name=birthday size=15 class="input-sm" required>
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >우편번호</th>
					<td width=85%>
						<input type=text name=post1 size=5 class="input-sm" readonly required>   - 
						<input type=text name=post2 size=5 class="input-sm" readonly required>
						<input type=button class="btn btn-sm btn-primary" value="우편번호검색" id="postBtn">
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right" >주소</th>
					<td width=85%>
						<input type=text name=addr size=50 class="input-sm" readonly required>
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
						<input type=submit value="회원가입" class="btn btn-sm btn-info"
						id="sendBtn">
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