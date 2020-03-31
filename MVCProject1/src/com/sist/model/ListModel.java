package com.sist.model;
// 여긴  MODEL = java에서 요청을 처리
import java.util.ArrayList;
import java.util.List;

/*
 		Controller -> 요청을 받는다   |   처리 결과값을 JSP로 전송
 		Model -> 요청을 처리 
 		View -> 결과값 출력
 					request								request											request								request   ; 동일
 		사용자 ============ Controller  =================== Model ========== Controller ====>  JSP
 							요청 				ListModel model=new ListModel()		request.addAttribute()					forward(request)	
 												model.execute(request);																										
 */


import javax.servlet.http.HttpServletRequest;

public class ListModel {
																// Controller의 리퀘스트
	public void execute(HttpServletRequest request)
	{
		List<String> list=new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("박문수");

		/*
			 	class A
			 	{
			 				A a=new A();
			 				A b=a; 
			 	}
			    a의 주소 = 100
			    b의 주소? 100   -------------> 동일한 주소에 값만 채워서 넘김 : call by reference
		 */
		
		request.setAttribute("list", list);
		//controller에 전송  --> JSP로 전송
	}
}
