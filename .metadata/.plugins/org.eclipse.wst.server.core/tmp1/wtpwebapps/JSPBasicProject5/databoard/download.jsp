<%@ page language="java" contentType="text/html; charset=UTF-8"
    
    
    pageEncoding="UTF-8" import="java.io.*, java.net.*"%>

    
   <%
  			/* GET방식은 톰캣안에서 처리 
  			setcharacterencoding= POST방식할때만 해줘야 한다*/  		
  			
  			String fn=request.getParameter("fn");
   
   			// header(저장할까요)+data(다운하기) = 파일 다운로드
   					// Content-Disposition : 다운로드 창 띄움
   			response.setHeader("Content-Disposition", "attachment;filename=" 
   											+URLEncoder.encode(fn,"UTF-8"));
   			
   			//파일명
   			File file=new File("c:\\upload\\"+fn);
   			//파일크기
   			response.setContentLength((int)file.length());
 
   			//data넘기기 = Copy + Paste	
   			try
   			{
   				BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
   				BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
   				
   				//data를 쪼개서 보내면 속도 향상됨  =data크기를 주는이유
   				int i=0; 
   						//	(적잘한 속도  |  TCP ; 1024 byte UDP ; 512byte)	
   				byte[] buffer=new byte[1024];
   				
   						while((i=bis.read(buffer, 0, 1024))!=-1)
   						{
   							bos.write(buffer,0,i);
   						}
   						
   						
   						// *********** out객체를 이미 쓰고 있어서 초기화 시키는 메소드
   						out.clear();
   						// *********** 다시 원상복귀
   						out=pageContext.pushBody();
   						//************************************************************
   				bis.close();
   				bos.close();
   			}catch (Exception ex) {}
   %>