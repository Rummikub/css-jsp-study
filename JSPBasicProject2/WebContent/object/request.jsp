<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	내장객체 (기본객체)  = 9개
	   ; jsp가 자동으로 미리 생성해둔 객체들
		HttpServletRequest request
		HttpServletResponse response		
		=========================== 오늘 배울 부분	   - 답글형 게시판
		PageContext pageContext;
		HttpSession session = null;    ------------------------------------ 댓글형 게시판
		ServletContext application;
		ServletConfig config;
		JspWriter out = null;
		Object page = this;
		JspWriter _jspx_out = null;
		
		try절 안 부분 = 코딩


		1) Request
			= 사용자 요청 정보
				★(1) String getParameter()  ->단일값    	ex)list.jsp?page=1&no=10
																					getParameter("page")
																					getParameter("no") 
				★(2) String [] getParameterValues() ->다중값	ex) list.jsp?no=1&no=2&no=3
																					getParameterValues("no");  ==> checkbox,select (multi)
				★(3) setCharacterEncoding() -> 한글 변환    (byte > 한글로 디코딩)
브라우저에 자바 검색 ; oq=+%EC%9E%90%EB%B0%94 => 자바 라는 byte표현임. 이걸 바꿔주는게 인코딩 <-> 디코딩			
			= 사용자 브라우저 정보
				★(1) getRemoteAddr() = 사용자 IP
				(2) getContentType() = text/html, tex/xml
				(3) getProtocol() = 사용자가 이용한 프로토콜 = http, https, ws (websocket)
				(4) getMethod() = GET/POST
				★(5) getRequestURI() = 
				★(6) getContextPath() =
				http://localhost/JSPBasicProject2/object/request.jsp  -> URL ,  getRequestURL 
				/JSPBasicProject2/object/request.jsp -> URI, getRequestURI
				/JSPBasicProject2 -> default , getContextPath
				
			= 서버 정보 
				(1) getServerName() -> localhost
				(2) getServerPort() -> 80
				
			= 데이터 추가 정보
				(1) setAttribute() = 데이터 추가     ( Oracle의 DB에서 VO를 request해올 때 필요)
				(2) getAttribute() = 추가된 데이터 읽기
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Request</title>
<!-- 자바스크립트로 유효성 검사
		1) 데이터형 
			var : 자동 지정 변수
					var a=1 => a(int)
					var a=10.5 => a(double)
					var a='admin' => String
					var a="admin" => String
					var a=[]  => Array  ===> [10,10.5,"",{}]
					var a={}  => Object
					================================= var短 (사용범위 불명확)
		2) 연산자 처리 : 산술(+-*/) 비교(== != < > <= >=)  논리( && ||) 대입 (+= = -=)
	3) 제어문  : if , for , if ~else if ~ else, do ~while ...
		4) 제공하는 함수 
			function func_name (addr)            ===> 매개변수 var 쓰지 않음
			{
			}
			var func=function(){
			}
			★람다식 (권장)
			var func=()=> {
			}
		5) 객체 구조
			 window: 브라우저 => open
			 document : 출력화면
			 form: 입력창
			 location: 화면 이동
			 history: 이전 => back(), forward() => go()
		6) 사용자 정의 함수
 -->
 
 <!-- 유효성 검사 ; 이름/ 소개를 안썼을 경우의 처리  = 강제 입력-->
 
                       
<!-- <script type="text/javascript">
function send()
{	
	var name=document.frm.name.value;
	if(name.trim()=="")
		{
			alert("이름을 입력하시오");
			document.frm.name.focus(); // 이름 입력하게 커서를 띄워라
			return;
		}
	
	var content=document.frm.content.value;
	if(content.trim()=="")
		{
			alert("내용을 입력하시오");
			document.frm.content.focus(); // 이름 입력하게 커서를 띄워라
			return;
		}
	
	document.frm.submit();
}
</script> -->
<!-- Jquery 라이브러리를 사용해 보자 -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
//window.onload (이미 브라우저가 띄워져 있는 상태에서 실행이 되어야 한다)
$(function() {
	$('#btn').click(function(){
			var name=$('#name').val();
			if(name.trim()=="")
			{
				alert("이름을 입력하시오");
				$('#name').focus(); // 이름 입력하게 커서를 띄워라
				return;
			}
		
		var content=$('#content').val();
		if(content.trim()=="")
			{
				alert("내용을 입력하시오");
				$('#content').focus(); // 이름 입력하게 커서를 띄워라
				return;
			}
		
		$('#frm').submit();
		
	});
});
</script>
</head>
<body>
		<!--  데이터 전송 ; request   / 값 넘겨줄 때 value의 name 꼭 줘야 함  / form 태그 ( 값을 넘겨줄 때 사용)  / jsp의 값은 HTML아닌  jsp만 받을 수 있다-->
		<center>
			<h1>개인 정보</h1>
			<form method=post  action="request_ok.jsp"  name=frm  id=frm> 
				<table border=1 width=450>
					<tr>
						<th width=20%>이름</th>
						<td width=80%><input type=text name=name size=15  id=name></td>
					</tr>
					<tr>
						<th width=20%>성별</th>
						<td width=80%>
							<input type=radio name=sex value="남자" checked>남자
							<input type=radio name=sex value="여자" >여자
					</tr>
					<tr>
						<th width=20%>전화</th>
						<td width=80%>
							<select name=tel1>
								<option>02</option>
								<option>031</option>
								<option>041</option>
								<option>051</option>
							</select>
					</tr>
					<tr>
						<th width=20%>취미</th>
						<td width=80%>
							<input type="checkbox" name=hobby  value=운동>운동
							<input type="checkbox" name=hobby  value=게임>게임
							<input type="checkbox" name=hobby  value=낚시>낚시
							<input type="checkbox" name=hobby  value=독서>독서
							<input type="checkbox" name=hobby  value=등산>등산
						</td>
					</tr>
					<tr>
						<th width=20%>소개</th>
						<td width=80%>
							<textarea rows="5" cols="35" name=content  id=content></textarea>
					</tr>
					<tr>
						<td colspan="2"  align="center">
						 	<input type=button  value=전송 id=btn>   
						</td>
					</tr>
				</table>
				</form>
		</center>
</body>
</html>