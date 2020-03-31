package com.sist.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
//★★★★★★★★★★★
import javax.xml.parsers.*;
import com.sist.model.*;
import org.w3c.dom.*;


public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//★★★★★★★★★★★
	private Map clsMap=new HashMap();

	public void init(ServletConfig config) throws ServletException {
		String path=config.getInitParameter("contextConfigLocation"); // init-param의 이름과 같아야 해
		System.out.println(path);
		
		try {
			
			//xml parser (데이터 읽어오는 도구)
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(path));
			// tag =element tag의 모음 =nodelist
			Element beans=doc.getDocumentElement();
			NodeList list=beans.getElementsByTagName("bean");
			System.out.println(beans.getTagName());
											//3
			for(int i=0;i<list.getLength();i++)
			{
				// ★ 속성명 (id/class) 과 태그명 (bean)을 맞춰줘야 매칭된 결과를 읽어올 수 있다.
				Element bean=(Element)list.item(i);
				System.out.println(bean.getTagName());
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				//클래스 이름
				Class clsName=Class.forName(cls);
				//객체생성(메모리할당)
				Object obj=clsName.newInstance();
				
				System.out.println("id="+id);
				System.out.println("model="+obj);
				clsMap.put(id, obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// 		/MVCProject3/category.do?no=1  ( URI는 ?전까지만 읽어올 수 있음!!)
				String cmd=request.getRequestURI();
				cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
				//<====================== 사용자 요청 (category)
				
				//사용자 요청 처리 (by Model ) ===================>
				Model model=(Model)clsMap.get(cmd);
			
				//★어떤 JSP로 보내?	  //요청 처리
				String jsp=model.handlerRequest(request);
				
				/*
				return " redirect : list.do"    |   return "파일명" 
				
				substring(start,end)
										=== end-1에서 끝남 주의 (미포함)
				 */
				//화면이동?sendRedirect | Request전송? forward
				if(jsp.startsWith("redirect"))
				{
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
				}else // request를 JSP에 보냄 
				{
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
				}
	}	

}
