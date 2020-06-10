<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		//전체삭제 =      session.invalidate();

		session.removeAttribute("name");  // 일부삭제 (장바구니,찜)
		response.sendRedirect("diary.jsp");
		
		
		/*
					Session : 서버에 일부 데이터 저장 => 모든 JSP에서 공유할 목적으로 사용
					주요 기능)
								= setAttribute(key,Object)  : 세션 공간에 저장
								= getAtrribute(key) : 저장된 값을 읽어옴
								= invalidate() : 전체 삭제
								= removeAttribute(key) : 한개씩 삭제
								= setMaxactiveInterval() : 저장 기간 설정
		*/
%>