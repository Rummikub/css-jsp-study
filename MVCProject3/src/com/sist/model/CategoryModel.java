package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import java.util.*;
public class CategoryModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		List<CategoryVO> list=FoodDAO.categoryListData();
		
		//★request에 list담아서 food 폴더의 category.jsp에 보내줘
		request.setAttribute("list", list);
		return "food/cateogry.jsp";
	}

}
