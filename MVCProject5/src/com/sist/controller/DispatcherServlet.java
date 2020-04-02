package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xml.sax.helpers.DefaultHandler;

import java.util.*;


@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	List<String> list=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		
		//1) web.xml에 있는 PATH를 읽어온다
		String path=config.getInitParameter("contextConfigLocation");
																				// ★★★★★★
		String defaultpath=config.getInitParameter("defaultPath");
		//2) 패키지명 읽어오기
		
																					// ★★★★★★
		HandlerMapping hm=new HandlerMapping(path,defaultpath);
		list=hm.getList(); //com.sist.Model등등을 list에 담아주겠지
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//1)읽어온 모델(사용자가 보내준 cmd)에 메모리 할당
			String cmd=request.getRequestURI(); 
			System.out.println("전: "+cmd); // ==>/MVCProject5/board/list.do
			//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★//
			cmd=cmd.substring(request.getContextPath().length()+1);
			System.out.println("후:"+cmd);  // ==> board/detail.d
			try
			{
				/*
				 com.sist.model.BoardModel
				 com.sist.model.HomeModel
				 */
				for(String cls:list)
				{
					Class clsName=Class.forName(cls);
					/* @Controller
					 * 	class A 
					 * 
					 *  class B  -- > continue ; (제외해라)
					 *
					 *	@RequestMapping("main/main.do")
					 * 메소드1
					 * @RequestMapping("main/list.do")
					 * 메소드2
					 */
					if(clsName.isAnnotationPresent(Controller.class)==false)
						continue;
					Object obj=clsName.newInstance();
					
			// 2) 결과를 배열에 담아라=클래스 이름안에. 메소드를 찾아서 호출
					Method[] methods=clsName.getDeclaredMethods();
									// m안에 @RequestMaaping을 갖고 있겠다!
					for(Method m:methods)
					{
							RequestMapping rm=m.getAnnotation(RequestMapping.class);
							// requestMapping 이 가진 값 ("main/main.do") --> value()
							if(rm.value().equals(cmd))
							{
								// 1-1 메소드 실행해라
								String jsp=(String)m.invoke(obj, request, response);
								//1-2 forward, sendRedirect구분   
								if(jsp.startsWith("redirect"))
								{
									//return "redirect:list.do"
									response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
								}
								else
								{
									//return "main/list.jsp"
									RequestDispatcher rd=request.getRequestDispatcher(jsp);
									rd.forward(request, response);
								}
							
								
								//★★★★★void로 리턴하니까 for문을 멈출 수 있음 (조건에 맞을떄 만 돌아갈 수 있게)
								return;
							
							
							}
					
					}
				
				}
		
			}catch(Exception ex){}
			
	}
	

}
