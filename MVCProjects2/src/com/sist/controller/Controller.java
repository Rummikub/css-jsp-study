package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sist.model.*;
import java.util.*;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ★ MVCProject1의 Controller기능을 모아준다 (비교)   -- command / class/ map
	//<bean id="list" class="com.sist.model.ListModel"/>  == spring < 키 , 클래스이름 >  == > 메모리 할당 & 저장 
			private String[] strCmd={"list","insert","insert_ok","detail","update","update_ok","movie"};
			private String[] strCls={
					"com.sist.model.ListModel",
					"com.sist.model.InsertModel",
					"com.sist.model.InsertOkModel",
					"com.sist.model.DetailModel",
					"com.sist.model.UpdateModel",
					"com.sist.model.UpdateOkModel",
					"com.sist.model.MovieModel"
			};
			private Map clsMap=new HashMap();
	// SINGLETON  ★
	
	
	/*
	 서블릿 호출 순서 :  메모리 할당 
	 							Controller con=new Controller();
	 							con.init(){  초기화 (생성자)}
	 							con.service(){  						}
	 							con.destroy(){ system.gc(); }
	 							
	 */
	
	
	//1)클래스 이름만 등록하면 메모리 할당 할 수 있다
	public void init(ServletConfig config) throws ServletException {

		try {
			for(int i=0;i<strCls.length;i++)
			{
				
				// 패키지명까지 포함한 클래스명을 하나씩 읽어와라
				Class clsName=Class.forName(strCls[i]);
				
				// 메모리 할당
				Object obj=clsName.newInstance();
				System.out.println(obj);
				clsMap.put(strCmd[i], obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	//2)기능 등록  -- MVCProject1의 service()함수를 6줄로 요약할 수 있음
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					String cmd=request.getRequestURI();
					//list.do
					cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
					
					//★
					Model model=(Model)clsMap.get(cmd);
					//System.out.println(model);
					String jsp=model.execute(request);	
					//System.out.println(jsp);
					
					
					//★ 화면전환 나누기 sendredirect | forward 
					if(jsp.startsWith("redirect"))
					{															//redirect:list.do
						response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
					}
					else
					{
						//forward사용하기 위한 객체 생성
						RequestDispatcher rd=request.getRequestDispatcher(jsp);
						rd.forward(request, response);
					}
					
	}

}
