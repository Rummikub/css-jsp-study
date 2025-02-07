<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%--
 		fmt => format (Number,Date)
 
  --%>
 
<%
		BoardModel model=new BoardModel();
		model.boardListData(request);	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XML이용한MV구조적용</title>

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 
</head>
<body>
		<div class="container">
			<h2 class="text-center">게시판</h2>
			<div class="row">
				<table class="table">
					<tr>
						<td><a href="insert.jsp" class="btn brn-sm btn-primary">새글</a></td>
					</tr>
				</table>		
				
				<table class="table table-hover">
					<tr class="danger">
						<th width=10% class="text-center">번호</th>
						<th width=45% class="text-center">제목</th>
						<th width=15% class="text-center">이름</th>
						<th width=20% class="text-center">작성일</th>
						<th width=10% class="text-center">조회수</th>
					</tr>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td width=10% class="text-center">${vo.no } </td>
							<td width=45% class="text-left">
								<a href="detail.jsp?no=${vo.no}">${vo.subject }</a>
								<c:if test="${vo.dbDay==today }">
									<sup><font color=red>new</font></sup>
								</c:if>
							</td>
							<td width=15% class="text-center">${vo.name} </td>
							<td width=20% class="text-center">
							
							<!-- 날짜/숫자 변환함수를 포함하고 있는 tag library = formatDate  -->
							<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
							<%--SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd") --%>
							
							</td>
							<td width=10% class="text-center">${vo.hit}</td>
						</tr>
					</c:forEach>
						<tr>
							<td class="text-center" colspan="5">
								<a href="list.jsp?page=${curpage>1?curpage-1:curpage }" class="btn btn-xs btn-success">이전</a>
										${curpage } page/ ${totalpage } pages
								<a href="list.jsp?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-xs btn-info">다음</a>
							</td>
						</tr>
				</table>
			</div>
		</div>
</body>
</html>