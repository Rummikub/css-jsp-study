package com.sist.user.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
//.do가 붙는것은 다 DispatcherServlet이 호출한다는 의미, 주소가 변하는 것은 아님, 화면만 forwarding해서 서블릿을 유지하기 위한 도구
@Controller
public class MainModel {

		@RequestMapping("main/main.do")
		public String main_main(HttpServletRequest request, HttpServletResponse response)
		{
			List<CategoryVO> list=MainDAO.mainImageData();
			
			List<RecipeVO> rlist=MainDAO.mainRecipeData();
			int count=MainDAO.recipeCount();
		
			  
			int no=(int)(Math.random()*119)+127;  // random 으로 추천값 뿌리기
			FoodVO fvo=MainDAO.mainFoodDetailData(no);
			
			
			
			request.setAttribute("main_jsp", "home.jsp");
				
			request.setAttribute("clist", list);
			//data 전송 ( clist = category list)
		
			
			request.setAttribute("rlist", rlist);
			
			request.setAttribute("count", count);
			
			request.setAttribute("fvo", fvo);
			
			return "../main/main.jsp";
		}
	
}
