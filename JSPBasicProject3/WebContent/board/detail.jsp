<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
    
   <%
   		String no=request.getParameter("no");
  
   		//Detail.jsp에서 값을 받아야 됨
   		String strPage=request.getParameter("page");
   		
   		// no=>DAO=> VO=> HTML
   		BoardDAO dao=new BoardDAO();
   		BoardVO vo=dao.boardDetailData(Integer.parseInt(no), 0); 
   		
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail</title>
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


<!-- JQuery!  ; JavaScript Library = onload 상태에서 태그를 제어한다 -->

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
					/*
					 자바스크립트 코딩
					 window.onload=function()
					 {}
					 
					 자바 코딩
					 function delClick() {}
					 
					 자바 스크립트 코딩
					 document.getElementById("delBtn");
					 
					 CSS
					 [Selector]
					 - tag  	td{}
					 - ID	  	#id{}
					 - Class	.class{}
					 
					 ; <a>bbb</a>  									$('a').text() 		=>setter 	    :~bbb
					 ; <a></a>											$('a').text("bbb")  => getter		: bbb입력
					 ; <a href="kkk" id="k"> 						$('#k').attr("href")  					: ~kkk
					 ; <input type=text id="p" value="kkk"> 	$('#p').val()							:  kkk		 
																				$('#p').val("ttt")						: ttt입력	
					; <td><span>ooo</span></td>			$('td').text() 							: ooo
																				$('td>span').text() 					: ooo
																				$('td').html() 							: <span>ooo</span>
					 */
 // 전역변수
 var i=0;
					 
//Jquery  = DOM (태그 제어 프로그램) 
$(function(){
		
	//보여주기
	$('#delBtn').click(function(){
		if(i==0)
		{
			$('#delBtn').val("취소");
			//비밀번호 창 보여주기
			$('#del').show();
			// 다음엔 else로 들어가게끔
			i=1;
		}
		else
		{
			$('#delBtn').val("삭제");
			//비밀번호 창 감추기
			$('#del').hide();e
			// 다음엔 if문으로 들어가게끔
			i=0; 
		}
	});
	
	//실제 처리 --- Ajax ; id중복체크 / 화면 안 깜빡거리고 바로 값을 주고, 받고 할 수 있는 코딩 / 화면이 바뀌지 않아도 처리할 수 있음
	$('#sendBtn').click(function(){
		var pwd=$('#pwd').val();
		var no=$('#no').val();
		var page=$('#page').val();
		if(pwd=="")
		{
			$('#pwd').focus();
			return;
		}
		
		//Ajax , JSON방식
		$.ajax({
		
			//delete.jsp?no=1&pwd=1234  => 404(경로), 500(소스), 200(정상)
			type:'POST',
			url:'delete.jsp',
			data:{"no":no,"pwd":pwd},
			
			//200이면
			success:function(res)
			{
				//alert(res);
				//delete.jsp에서 넘어오는 변수 : res (0|1)
				var result=res.trim();
				if(result==0)
				{
					alert("비밀번호가 틀립니다");
					$('#pwd').val(''); // 공백화
					$('#pwd').focus();
				}
				else
				{
					//★화면이동 =sendRedirect
					location.href="list.jsp?page="+page;
				}
			}
		});
	});
});
</script>


</head>
<body>
	<div class="container">
			<h2>내용보기</h2>
			<table class="table table-hover" >
				<tr>
					<th width=20% class="text-center success">번호</th>
					<td width=30% class="text-center "><%= vo.getNo() %> </td>		
					<th width=20% class="text-center success">작성일</th>
					<td width=30% class="text-center "><%= vo.getRegdate() %> </td>
				</tr>
				
				<tr>
					<th width=20% class="text-center success">이름</th>
					<td width=30% class="text-center "><%= vo.getName() %></td>		
					<th width=20% class="text-center success">조회수</th>
					<td width=30% class="text-center "><%= vo.getHit() %> </td>
				</tr>
				
				<tr>
		
					<th width=20% class="text-center success">제목</th>
					<td colspan="3" class="text-left "><%=vo.getSubject() %> </td>
				</tr>
				
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space: pre-wrap;  background-color: white; border:none "><%= vo.getContent() %></pre>
					</td>
					
					<tr>
						<td colspan="4" class="text-right">
							<a href="reply.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-success">답변</a>
							<a href="update.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-primary">수정</a>
							<input type=button class="btn btn-xs btn-danger" id="delBtn" value="삭제">
							<a href="list.jsp?page=<%=strPage %>" class="btn btn-xs btn-info">목록</a> <!--  back을 하면 조회수에 문제가 생김 -->
				</tr>
		
		
				<!-- 비밀번호 창 숨기기 -->
				<tr id="del" style="display:none">
						<td colspan="4" class="text-right">
							비밀번호:	<input type="password" id="pwd" size=10 class="input-sm">
											<input type=hidden id="no" value="<%= no %>">
											<input type=hidden id="page" value="<%= strPage%>">
											<input type="button" value="삭제" class="btn btn-sm btn-danger" id="sendBtn">
						</td>
				</tr>
			</table>
	</div>
</body>
</html>