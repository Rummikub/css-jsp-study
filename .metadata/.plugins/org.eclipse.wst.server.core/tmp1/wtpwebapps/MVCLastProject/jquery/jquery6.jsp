<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 이미지를 클릭하면 상세보기가 다이얼로그로 뜨는 프로그래밍  -->

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
  <script type="text/javascript">
  // JSON파일을 append 함수로 읽어오기
  $( function() {
   	 $.getJSON('weekly.json',function(data){
   		 //	alert(data)
   		 	// for each 구문 ! ["key"]
   		 $.each(data["datas"],function(key,value){
   			 $('#list').append(
   					'<div class="col-md-3">'
   				    +'<div class="thumbnail">'
   				   
   				   +'<img src="'+value.poster+'" alt="Lights" style="width:100%" onclick=detail('+value.no+')>'
   				        +'<div class="caption">'
   				         +'<p id="ppp">'+value.title+'</p>'
   				        +'</div>'

   				    +'</div>'
   				 +'</div>'
   			 )
   		 });
   	 });
   	 // function에서 정의한 id는 메모리 x ! - 함수로 빼야함...
   	 $('#ppp').click(function(){
   		 let p=$(this).text();
   		 alert(p);
   	 })
   	 // X : 실행이 안됨
  });
  
   	 function detail(no)
   	 {
   		$.getJSON('weekly.json',function(data){
      		 //	alert(data)
      		 	// for each 구문 ! ["key"]
      		 $.each(data["datas"],function(key,value){
      			 if(no==value.no)
      			 {
      				 $('#img').attr("src",value.poster);
      				 $('#title').text(value.title);
      				 $('#director').text(value.director);
      				 $('#regdate').text(value.regdate);
      				 $('#grade').text(value.grade);
      				 $('#score').text(value.score);
      				 $('#actor').text(value.actor);
      				 $('#story').text(value.story);
      			
      				 // 함수종료는 return으로 한다.
      				 return true;
      			 }
      			
      		 });
      	 });
      	 
   		// dialog 띄울 때 detail함수를 호출하라
      	 $('#dialog').dialog({
      		width:600,
      		height:500
      	 })
   	 }

  </script>

<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<!--  
				<div class="col-md-4">
			    <div class="thumbnail">
			      <a href="/w3images/lights.jpg">
			        <img src="/w3images/lights.jpg" alt="Lights" style="width:100%">
			        <div class="caption">
			          <p>Lorem ipsum...</p>
			        </div>
			      </a>
			    </div>
			  </div>
-->
	<div class="container">
		<h1 class="text-center">영화목록</h1>
		<div class="row" id="list">
			
		</div>
		<div class="row">
		<div id="dialog" title="영화 상세보기" style="display:none">
 			 <table class="table">
 			 	<tr>
 			 		<td width=30% class="text-center" rowspan="7">
 			 			<img src="" width=100% id="img">
 			 		</td>
 			 		<td colspan="2" id="title"></td>
 			 	</tr>
 			 	<tr>
 			 		<td width=10% class="text-right"></td>
 			 		<td width=60% id="director">감독</td>
 			 	</tr>
 			 	<tr>
 			 		<td width=10% class="text-right"></td>
 			 		<td width=60% id="actor">출연</td>
 			 	</tr>
 			 	<tr>
 			 		<td width=10% class="text-right"></td>
 			 		<td width=60% id="genre">장르</td>
 			 	</tr>
 			 	<tr>
 			 		<td width=10% class="text-right"></td>
 			 		<td width=60% id="grade">등급</td>
 			 	</tr>
 			 	<tr>
 			 		<td width=10% class="text-right"></td>
 			 		<td width=60% id="score">평점</td>
 			 	</tr>
 			 	<tr>
 			 		<td width=10% class="text-right"></td>
 			 		<td width=60% id="regdate">상영일</td>
 			 	</tr>
 			 	<tr >
 			 		<td colspan="3" class="text-left" id="story"></td>
 			 	</tr>
 			 	
 			 </table>
		</div>
		</div>
	</div>
</body>
</html>