<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<%
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		
		System.out.println(id+pwd);
		//dao연결
		String res=dao.isLogin(id, pwd);
	
	
		//이동
		
		if(res.equals("NOID"))
		{
%>
					<script>
					alert("아이디 존재하지 않음");
					history.back();
					</script>
<%			
		}else if(res.equals("NOPWD"))
		{
%>
			<script>
			alert("비밀번호 존재하지 않음\n 다시 입력하시오");
			history.back();
			</script>
<%		
		}else
		{
			
	
		//로그인 처리!!!!!!!!!!!!!!!!!!!!!!!!!  --> 화면이 바뀌면 id/pw값을 못 가져오니까 여기서 Session 씀
			session.setAttribute("id",id);
			session.setAttribute("name",res);
			response.sendRedirect("diary.jsp");			
			
		}
%>
