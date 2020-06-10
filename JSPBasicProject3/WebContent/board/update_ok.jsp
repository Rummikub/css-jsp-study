<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.sist.dao.*"%>

<%
		/* Decoding  */
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception e){}
	
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		// 추가 : hidden
		String no=request.getParameter("no");
		String strPage=request.getParameter("page");
		
		BoardVO vo=new BoardVO();
		//***************************************************
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);

		BoardDAO dao=new BoardDAO();
		//pwd 맞았나 or 틀렸다
		boolean bCheck=dao.boardUpdate(vo);
		
		if(bCheck==true)
		{
			response.sendRedirect("detail.jsp?no="+no+"&page="+strPage);
		}
		else
		{
%>
			<script type="text/javascript">
				alert("비밀번호가 틀렸습니다");
				history.back();
			</script>
<%
		}
%>
		

 