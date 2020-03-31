package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import java.util.*;
import com.sist.dao.FoodDAO;

public class MiddleModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		String cno=request.getParameter("cno");
													//key값이 넘어옴
		List<FoodVO> list=FoodDAO.middleListData(Integer.parseInt(cno));
	
		request.setAttribute("list", list);

		
		return "food/middle.jsp";
	}

}
