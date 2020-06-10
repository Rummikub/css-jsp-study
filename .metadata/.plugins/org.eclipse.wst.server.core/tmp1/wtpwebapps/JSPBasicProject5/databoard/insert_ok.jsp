<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="com.sist.dao.*,com.oreilly.servlet.*, com.oreilly.servlet.multipart.*"%>
<%@page import="java.io.*" %>
<%
	try
	{
		request.setCharacterEncoding("UTF-8");	
	}catch (Exception ex) {}

	//파일 저장할 공간 지정
	String path="c:\\upload";
	
	//100MB지정
	int maxSize=100*1024*1024;
	
	//한글파일명
	String enctype="UTF-8";
	
	//[업로드]COS library사용 ★ 매개변수 주의															--> 동일한 파일명 넘버링 
	MultipartRequest mr=new MultipartRequest(request,path,maxSize,enctype,new DefaultFileRenamePolicy());
	
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	
	
	//파일 이름 읽어오기 original file name -원제 
	String filename=mr.getOriginalFileName("upload");
	
	//file system name -  복사본 (실제 파일 이름)
	String fn=mr.getFilesystemName("upload");
	
	FileBoardVO vo=new FileBoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	if(filename==null)
	{
		vo.setFilename("");
		vo.setFilesize(0);
	}else
	{
		File file=new File(path+"\\"+fn);
		vo.setFilename(fn);
		vo.setFilesize((int)file.length());
	}
	
	FileBoardDAO dao=new FileBoardDAO();
	//insert
	dao.boardInsert(vo);
	//이동
	response.sendRedirect("list.jsp");
	
%>