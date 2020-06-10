<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {

	margin: 0px auto;
	width: 350px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#id').focus();
	$('#checkBtn').click(function(){
		let id=$('#id').val(); // 입력한 결과를 가져옴
		if(id.trim()=="")
		{
		 $('#id').focus();
		 return;
		}
		//MemberModel==> id
	
			/*
			success ; 200
			error ; 404,500
			시스템에 의해 자동으로 호출 --> callback
			
			1) AJAX
				XMLHttpRequest request-> 얻어온다 (각 브라우저에 내장)
			2) 연결
				request.open('POST/GET','file경로명 ', true)   ==> true : 비동기화 / 동기화
			3) 결과값을 가져오는 자동 호출함수
				request.onreadystatechange=sendMessage
				
			4) 전송할 데이터 설정
				request.send(id=admin&pwd=1234)
				
			function sendMessage()
			{
				0=>open전=연결중
				1=>open후 =연결완료
				2=>send 준비
				3=>send 완료
				4=>정상수행, send 완료확인
				if(request.readyState==4)
				{
					if(request.status==200)
					{
						
					}
					else
					{
						400,404,500
					}
				}
			}
			
			SELECT COUNT(*) FROM MEMBER
			WHERE ID='HONG';
			0 = 사용가능      1 = 불가능
			*/
			//data:{"id":"admin"}=> idcheck_result.do?id=admin&pwd=1234
		$.ajax({
			type:'POST',
			url:'../member/idcheck_result.do',
			data:{"id":id}, // type JSON
			success:function(result){ // HTML, JSON, XML 이 모두 result가 될 수 있다는 점.
				let count=result.trim(); // result를 정수로 변환한거래 : Number | parseInt
				if(count==0)
				{
					let msg='<font color=blue><b>'+id+'는(은)사용 가능합니다</b></font>';
					$('#result').html(msg);
					$('#ok').html(
						'<input type=button value=확인 class="btn btn-sm btn-success" onclick="ok()">'		
					);
				}
				else
				{
					let msg='<font color=red><b>'+id+'는(은)이미 사용중입니다</b></font>'
					$('#result').html(msg);
				}
			},error:function(e)  // 콜백함수임 (자동호출)
			{
				alert(e);
			}
		});
		//결과읽어서 처리
	});
});
function ok()
{
	let id=$('#id').val();
	parent.frm.id.value=id; // join,.jsp에 값채워라
	parent.Shadowbox.close(); //shadowbox를 갖고있는게 누군지 확인! (join.jsp)
}

</script>
</head>
<body>
 <div class="container">
 
 	<div class="row">
 	 <table class="table">
 	 	<tr>
 	 		<td>
 	 		입력: <input type=text id=id  class="input-sm" size= 15>
 	 			<input type=button value="아이디체크" class="btn btn-sm btn-primary" id="checkBtn">
 	 		</td>
 	 	</tr>
 	 	<tr>
 	 	 <td class="text-center">
 	 	 	<span id="result"></span>
 	 	 </td>
 	 	</tr>
 	 	<tr>
 	 		<td class="text-center" id="ok"></td>
 	 	</tr>
 	 </table>
 	</div>
 </div>
</body>
</html>