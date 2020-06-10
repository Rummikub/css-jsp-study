<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
td{
	font-size: 8pt;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

// Jquery사용하는법
$(function(){
	// hover주는 법
	$('.post_click').hover(function(){   // if
			$(this).css("cursor","pointer").css("background-color","#FC6");    
	},function(){  // else
			$(this).css("cursor","none").css("background-color","white");
	});
	
	$('.post_click').click(function(){
		// 클릭한 td태그 자신
		var zip=$(this).attr('zip');
		var addr=$(this).attr('addr');
		parent.frm.post1.value=zip.substring(0,3);
		parent.frm.post2.value=zip.substring(4,7);
		parent.frm.addr.value=addr;
		parent.Shadowbox.close();
	});
});

/* JS function 사용하는법
function ok(zip,addr)
{
	//=join.jsp (shadowbox에서의 open.)
							//값을 채워라
	parent.frm.post1.value=zip.substring(0,3);
	parent.frm.post2.value=zip.substring(4,7);
	parent.frm.addr.value=addr;
	parent.Shadowbox.close();
}
*/
</script>

</head>
<body>
		<c:if test="${count==0 }">				
			<table class="table">
				<tr>
					<td class="text-center">
						<b>검색할 결과가 없습니다</b>
					</td>
				</tr>
			</table>
		</c:if>
	<!-- 데이터가 있을 경우 -->
		<c:if test="${count!=0 }">				
			<table class="table">
				<tr class="success">
					<th class="text-center">우편번호</th>
					<th class="text-center">주소</th>
				</tr>
				<c:forEach var="vo" items="${list }">
							<!-- HTML에 속성은 사용자 정의 가능함 (zip,addr처럼)  -->
					<tr class="post_click" zip="${vo.zipcode }" addr="${vo.address }">
						<td class="text-center">${vo.zipcode }</td>
						<td>${vo.address}</td>
						<!-- <a href="javascript:ok('${vo.zipcode })','${vo.address}')"> -->
					</tr>
				</c:forEach>
			</table>
		</c:if>
</body>
</html>