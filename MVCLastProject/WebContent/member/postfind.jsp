<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {

	margin: 0px auto;
	width: 500px;
}
</style>

<!-- main과 별도이기 때문에 jquery 주소 받아오고(library import개념임) , 기능을 별도로 지정해 주면 된다  -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

/*
 	= getter
   	<input type="text" id="id">  ==> $('#id').val();  - 입력값
   	<td>aaa</td>						===> $('td').text();  - 사이값
 	<td><span>aaa</span></td>	==> $('td').html(); - 태그포함 사이값
 	<a href="aaa"> 					====> $('a').attr("href")
 
 	= setter
 	<input type="text" id="id"> ==> $('#id').val('admin') - 값을 넣어달라구. 
 	$('td').append()	   				==> 값추가
 	*/

$(function(){ 
	$('#findBtn').click(function(){
		var dong=$('#dong').val();    // id="dong"에 입력한 값을 가져와라
		if(dong.trim()=="")
		{
			$('#dong').focus();
			return;
		}
		//alert(dong);     
		$.ajax({
			//.do?dong=신촌	
			type:'POST',
			// ★ ajax값을 넘길때, 창을 새로 만들어야지(postfind_result) 안그러면 내용 상속이 안되겠지...
			//어디로 보낼것인지 , 파일명
			url:'../member/postfind_result.do',
			// 보낼 데이터 , 변수 (json방식으로 보내준다)
			data:{"dong":dong},
			// 정상수행: 서버에서 읽어온 파일을 div에 넣어줘 | 실패
			success:function(result){
				 var div=$('#result').html(result);
			},
			error:function(e)
			{
				alert(e);
			}
		});
	});
});
</script>
</head>
<body>
	<div class="container">
			<div class="row">
				<table class="table">
					<tr>
						<td>
						입력: <input type=text  id="dong" size=15 class="input-sm">
								<input type=button id="findBtn" class="btn btn-sm btn-danger" value="입력">
						</td>
					</tr>
					<tr>
						<td class="text-right">
							<sub style="color:red">※동/읍/면을 입력하세요</sub>
						</td>
					</tr>
				</table>
				<div id="result"></div>
			</div>	
	</div>
</body>
</html>