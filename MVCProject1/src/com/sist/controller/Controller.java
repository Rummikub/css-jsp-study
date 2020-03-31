package com.sist.controller;
// list.jsp?no=
// Controller을 거쳐가야 되니까 위의 코딩은 x => Model => Controller => JSP

// 앞으로의 호출법

//Controller?cmd=list
//Controller?cmd=detail
//Controller?cmd=insert

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sist.model.*;


//Servlet호출하는 방법: "/Controller"
@WebServlet("*.do")
//.do로 끝나는 확장자 모두 끌어오기 위해 -> 파일화
//list.do  /   insert.do  /  detail.do

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {

	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//	String cmd=request.getParameter("cmd");
		//요청받기 => 처리 => Model	
			String uri=request.getRequestURI();
			//uri = /MVCProject1/ list.do     ***************** 이 방법으로 바꾸자
																	//MVCProject1					/ 					==> list
			//String cmd=uri.substring(request.getContextPath().length()+1,uri.lastIndexOf("."));
		
			String cmd=uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf("."));		
			
			// ★   file만 불러오고 싶어 <--  (매번 폴더 + 이름 줄 수 없으니까)	
			String jsp=uri.substring(request.getContextPath().length(),uri.lastIndexOf("."));
			System.out.println(jsp);
			
			jsp=jsp+".jsp";
		
			
			
			/*  if문이 너무 많아 ==> 
			 * Map에 주소를 저장했다가 호출
			 *  map.put("list", new ListModel())
			 *  map.put("insert", new InsertModel())
			 *  map.put("detail", new DetailModel())
			 */
	
			
			
			//ROOT=webContent (organized by Folder)  ~ 
			if(cmd.equals("list"))
			{
					//요청값 받기
					ListModel model=new ListModel();
					model.execute(request);					
					//이동
					//jsp="member/list.jsp";
					
			}
			else if(cmd.equals("detail"))
			{
					DetailModel model=new DetailModel();
					model.execute(request);
					//jsp="member/detail.jsp";
			}
			
			else if(cmd.equals("insert"))
			{
					InsertModel model=new InsertModel();
					model.execute(request);
					//jsp="member/insert.jsp";
			}
			
			else if(cmd.equals("update"))
			{
					UpdateModel model=new UpdateModel();
					model.execute(request);
					//jsp="board/update.jsp";
			}
			
			// 이동하기
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
	}

}
