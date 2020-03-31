package com.sist.news;
import java.util.*;

import com.sist.model.Model;
import com.sist.news.*;
// 불러오는 부분
import javax.servlet.http.HttpServletRequest;

public class NewsModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {

				try
				{
						request.setCharacterEncoding("UTF-8");
				}catch(Exception ex) {}
				String fd=request.getParameter("fd");
				
				if(fd==null || fd.equals(""))
				{
					fd="맛집";
				}
				NewsManager mgr=new NewsManager();
				List<Item> list=mgr.newsAllData(fd);
				System.out.println(list.size());
				request.setAttribute("list", list);
		return "news/news.jsp";
	}

}
