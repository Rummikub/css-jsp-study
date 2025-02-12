package com.sist.model;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.sist.dao.*;

public class MovieModel {

	
	// request에 실어서 JSP와 분리
	public void movieListData(HttpServletRequest request)
	{
		String page=request.getParameter("page");
		if(page==null)
				page="1";
		int curpage=Integer.parseInt(page);
		MovieDAO dao=new MovieDAO();
		List<MovieBean> list=dao.movieListData(curpage);
		int totalpage=dao.movieTotalPage();
	
		/* ******************************************
			JSP가 필요로하는  '변수'들  request로 보내줌
			**************************************** */
		
			// request > session 을 우선한다
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		 
		HttpSession session=request.getSession();
		
		session.setAttribute("list", list);
		session.setAttribute("curpage", curpage);
		session.setAttribute("totalpage", totalpage);
	}
	
	
	public void movieDetailData(HttpServletRequest request)
	{
		String mno=request.getParameter("mno");
		MovieDAO dao=new MovieDAO();
		MovieBean vo=dao.movieDetailData(Integer.parseInt(mno));
		
		request.setAttribute("vo", vo);
	}
	
	
}
