<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta  charset="UTF-8">
<title>수정하기</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#pwd2').keyup(function(){
		var k=$(this).val();
		console.log(k)
		var no=$('#no').val();
		$.ajax({ 
			type:'post',
			url:'../reply/password_check.do',
			data:{"pwd":k,"no":no},
			success:function(res){
				var no=res.trim();
				if(no==1)
				{
					$('#result').html('<font color=blue>비밀번호가 맞습니다.수정할 수 있어요.</font>')
					$('#updateBtn').attr('disabled',false)
				}
				else
				{
					$('#result').html("<font color=red>비밀번호가 틀립니다</font>")
					$('#updateBtn').attr('disabled',true)
				}
			}
		})
		
	})
})
</script>
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
	<div class="container">
		<h2>수정하기</h2>
		<div class="row">
		
			<form method=post  action="../reply/update_ok.do">
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-right  success">이름</th>
					<td width=80%>
						<input type="text" name=name size=15 value=${vo.name } required  >
						
						<!-- ★★데이터 숨기기 ; 화면상에는 보이지 않지만 데이터는 보내짐★★-->
							<input type=hidden name=no id=id value=${vo.no }> 
						
					
					
						</td>
				</tr>
				
				<tr>
					<th width=20% class="text-right  success">제목</th>
					<td width=80%>
						<input type="text" name=subject size=50 value=${vo.subject } required  >  
						<!-- value는 따옴표써주는게 좋아 b/c 공백오류 -->
						</td>
				</tr>
				
				<tr>
					<th width=20% class="text-right  success">내용</th>
					<td width=80%>
						<textarea rows="8" cols="55" name=content required>${vo.content }</textarea>
						</td>
				</tr>
				
				<tr>
					<th width=20% class="text-right  success">비밀번호</th>
					<td width=80%>										<!--pwd가 아이디로 안먹는대★★-->				
						<input type="password" name=pwd size=10 required id=pwd2>
						<div id="result"></div>
						</td>
				</tr>
				
				<tr>
					<td class="text-center" colspan=2>						<!-- ★★수정하기를 못하게 막아놔★★ -->								
						<input type="submit" value="수정" class="btn btn-sm btn-primary" id="updateBtn" disabled>
						<input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
						</td>
				</tr>
			
			</table>
				</form>
		</div>
	</div>
</body>
</html>