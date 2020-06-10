package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;
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
		
		//1) web.xml�뿉 �엳�뒗 PATH瑜� �씫�뼱�삩�떎
		String path=config.getInitParameter("contextConfigLocation");
																				// �쁾�쁾�쁾�쁾�쁾�쁾
		String defaultpath=config.getInitParameter("defaultPath");
		//2) �뙣�궎吏�紐� �씫�뼱�삤湲�
		
																					// �쁾�쁾�쁾�쁾�쁾�쁾
		HandlerMapping hm=new HandlerMapping(path,defaultpath);
		list=hm.getList(); //com.sist.Model�벑�벑�쓣 list�뿉 �떞�븘二쇨쿋吏�
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//1)�씫�뼱�삩 紐⑤뜽(�궗�슜�옄媛� 蹂대궡以� cmd)�뿉 硫붾え由� �븷�떦
			String cmd=request.getRequestURI(); 
			System.out.println("�쟾: "+cmd); // ==>/MVCProject5/board/list.do
			//�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾�쁾//
			cmd=cmd.substring(request.getContextPath().length()+1);
			System.out.println("�썑:"+cmd);  // ==> board/detail.d
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
					 *  class B  -- > continue ; (�젣�쇅�빐�씪)
					 *
					 *	@RequestMapping("main/main.do")
					 * 硫붿냼�뱶1
					 * @RequestMapping("main/list.do")
					 * 硫붿냼�뱶2
					 */
					if(clsName.isAnnotationPresent(Controller.class)==false)
						continue;
					Object obj=clsName.newInstance();
					
			// 2) 寃곌낵瑜� 諛곗뿴�뿉 �떞�븘�씪=�겢�옒�뒪 �씠由꾩븞�뿉. 硫붿냼�뱶瑜� 李얠븘�꽌 �샇異�
					Method[] methods=clsName.getDeclaredMethods();
									// m�븞�뿉 @RequestMaaping�쓣 媛뽮퀬 �엳寃좊떎!
					for(Method m:methods)
					{
							RequestMapping rm=m.getAnnotation(RequestMapping.class);
							// requestMapping �씠 媛�吏� 媛� ("main/main.do") --> value()
							if(cmd.equals(rm.value()))
							{
								// 1-1 硫붿냼�뱶 �떎�뻾�빐�씪
								String jsp=(String)m.invoke(obj, request, response);
								//1-2 forward, sendRedirect援щ텇   
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
							
								
								//�쁾�쁾�쁾�쁾�쁾void濡� 由ы꽩�븯�땲源� for臾몄쓣 硫덉텧 �닔 �엳�쓬 (議곌굔�뿉 留욎쓣�뻹 留� �룎�븘媛� �닔 �엳寃�)
								return;
							
							
							}
					
					}
				
				}
		
			}catch(Exception ex){}
			
	}
	

}
