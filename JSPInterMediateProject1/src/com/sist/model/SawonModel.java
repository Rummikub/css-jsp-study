package com.sist.model;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
public class SawonModel {
		
		public void sawonListData(HttpServletRequest request)
		{
			List<String>list=new ArrayList<String>();
			list.add("홍길동");
			list.add("홍길동1");
			list.add("홍길동2");
			
			request.setAttribute("list", list);
		}
}
