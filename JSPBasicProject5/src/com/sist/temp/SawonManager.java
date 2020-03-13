package com.sist.temp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
public class SawonManager {

	// request객체에 data를 추가해서 보내줄 때
		public void sawonAllData(HttpServletRequest request)
		{
			List<Sawon> list=new ArrayList<Sawon>();
			list.add(new Sawon(1, "홍길동", "영업부"));
			list.add(new Sawon(2, "심청이", "개발부"));
			list.add(new Sawon(3, "춘향이", "총무부"));
			list.add(new Sawon(4, "박문수", "기획부"));
			list.add(new Sawon(5, "이순신", "영업부"));
			
			//request 추가  (속성, 값) 을 받는것 + 주소에 값을 실어서 보내줄 수 있음  - call by reference
			request.setAttribute("list", list);
		}
}
